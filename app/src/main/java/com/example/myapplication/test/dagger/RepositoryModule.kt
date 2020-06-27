package com.example.myapplication.test.dagger

import com.example.myapplication.test.api.Api
import com.example.myapplication.test.repo.MainRepository
import com.example.myapplication.test.repo.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Kumuthini.N on 28-06-2020
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(api: Api): MainRepository {
        return MainRepositoryImpl(api)
    }
}

