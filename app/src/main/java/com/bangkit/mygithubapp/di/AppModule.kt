package com.bangkit.mygithubapp.di

import com.bangkit.core.domain.usecase.UserInteractor
import com.bangkit.core.domain.usecase.UserUseCase
import com.bangkit.mygithubapp.detail.DetailViewModel
import com.bangkit.mygithubapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}