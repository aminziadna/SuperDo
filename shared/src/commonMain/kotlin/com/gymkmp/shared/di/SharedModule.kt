package com.gymkmp.shared.di

import com.gymkmp.shared.data.repository.FakeUserRepository
import com.gymkmp.shared.domain.repository.UserRepository
import com.gymkmp.shared.domain.usecase.BookClassUseCase
import org.koin.dsl.module

val sharedModule = module {
    // Repositories
    single<UserRepository> { FakeUserRepository() }
    
    // Use Cases
    single { BookClassUseCase(get(), get()) }
}