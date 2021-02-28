package com.updown.superdo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.updown.superdo.data.ProductInfo
import com.updown.superdo.network.SocketUpdate
import com.updown.superdo.network.WebServicesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val interactor: MainInteractor):
    ViewModel() {

    private var collectedData = listOf<ProductInfo>()
    val collectedDataLiveData = MutableLiveData<List<ProductInfo>>()

    @ExperimentalCoroutinesApi
    fun subscribeToSocketEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                interactor.startSocket().consumeEach {
                    if (it.exception == null) {
                        it.text?.let {data->
                            collectedData = collectedData.plus(Gson().fromJson(data, ProductInfo::class.java))
                            collectedDataLiveData.postValue(collectedData.asReversed())
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

    private fun onSocketError(ex: Throwable) {
        println("Error occurred : ${ex.message}")
    }

    override fun onCleared() {
        interactor.stopSocket()
        super.onCleared()
    }

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