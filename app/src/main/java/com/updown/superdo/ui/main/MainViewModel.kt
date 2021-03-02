package com.updown.superdo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.updown.superdo.data.FeedDao
import com.updown.superdo.data.ProductInfo
import com.updown.superdo.network.SocketUpdate
import com.updown.superdo.network.WebServicesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val interactor: MainInteractor, private val filter: FeedFilter, private val db: FeedDao
) :
    ViewModel() {

    private var collectedData = listOf<ProductInfo>()
    val collectedDataLiveData = MutableLiveData<List<ProductInfo>>()
    private var searchKeyword: String? = ""
    private var streamStatus = StreamingStatus.Streaming

    @ExperimentalCoroutinesApi
    fun subscribeToSocketEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                interactor.startSocket().consumeEach {
                    if (it.exception == null) {
                        it.text?.let { data ->
                            val product = Gson().fromJson(data, ProductInfo::class.java)
                            when (streamStatus) {
                                StreamingStatus.Stopped -> {
                                    if(filter.filter(product,searchKeyword))
                                        db.saveProduct(product)
                                }
                                StreamingStatus.Resuming -> {
                                    collectedData = collectedData.plus(
                                        db.loadData().plus(
                                            product
                                        )
                                    )
                                    db.deleteAll()
                                    streamStatus = StreamingStatus.Streaming
                                }
                                StreamingStatus.Streaming -> {
                                    collectedData = collectedData.plus(
                                        product
                                    )
                                    collectedDataLiveData.postValue(
                                        if (!searchKeyword.isNullOrEmpty())
                                            collectedData.filter { productInfo ->
                                                filter.filter(productInfo, searchKeyword)
                                            }.asReversed()
                                        else collectedData.asReversed()
                                    )
                                }
                            }
                        }
                    } else {
                        onSocketError(it.exception)
                    }
                }
            } catch (ex: java.lang.Exception) {
                onSocketError(ex)
            }
        }
    }

    fun filterFeed(keyword: String?) {
        searchKeyword = keyword
        viewModelScope.launch {
            collectedDataLiveData.postValue(collectedData.filter {
                filter.filter(
                    it,
                    keyword
                )
            }.asReversed())
        }
    }

    fun stop() {
        streamStatus = StreamingStatus.Stopped
    }

    fun start() {
        if (streamStatus != StreamingStatus.Stopped)
            return
        streamStatus = StreamingStatus.Resuming
    }

    private fun onSocketError(ex: Throwable) {
        println("Error occurred : ${ex.message}")
    }

    override fun onCleared() {
        interactor.stopSocket()
        super.onCleared()
    }
}

enum class StreamingStatus {
    Stopped,
    Resuming,
    Streaming
}

class MainInteractor constructor(private val repository: MainRepository) {

    @ExperimentalCoroutinesApi
    fun stopSocket() {
        repository.closeSocket()
    }

    @ExperimentalCoroutinesApi
    fun startSocket(): Channel<SocketUpdate> = repository.startSocket()

}

class MainRepository constructor(private val webServicesProvider: WebServicesProvider) {

    @ExperimentalCoroutinesApi
    fun startSocket(): Channel<SocketUpdate> =
        webServicesProvider.startSocket()

    @ExperimentalCoroutinesApi
    fun closeSocket() {
        webServicesProvider.stopSocket()
    }
}