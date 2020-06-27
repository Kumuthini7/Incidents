package com.example.myapplication.test.dagger

import com.example.myapplication.test.repo.MainRepository
import com.example.myapplication.test.viewModel.MainViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Kumuthini.N on 28-06-2020
 */
@Module
class ViewModelModule {

    @Provides
    fun providesMainViewModelFactory(mainRepository: MainRepository): MainViewModelFactory {
        return MainViewModelFactory(mainRepository)
    }
}

