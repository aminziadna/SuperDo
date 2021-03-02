package com.updown.superdo.koin

import com.updown.superdo.data.AppDatabase
import com.updown.superdo.network.KdsWebSocketListener
import com.updown.superdo.network.WebServicesProvider
import com.updown.superdo.ui.main.MainInteractor
import com.updown.superdo.ui.main.MainRepository
import com.updown.superdo.ui.main.MainViewModel
import com.updown.superdo.ui.main.WeightFeedFilter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val appModules = module {
    single { KdsWebSocketListener() }
    single { WebServicesProvider() }
    single { MainRepository(get()) }
    single { MainInteractor(get()) }
    single { AppDatabase.getDatabase(get()) }
    single { get<AppDatabase>().feedDao() }
}

val viewModelModules = module {
    viewModel { MainViewModel(get(), WeightFeedFilter(), get()) }
}