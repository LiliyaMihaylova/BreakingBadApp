package com.volasoftware.breakingbadapp.di.modules

import com.volasoftware.breakingbadapp.networking.BreakingBadWrapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun provideBreakingBadWrapper(): BreakingBadWrapper {
        return BreakingBadWrapper()
    }
}