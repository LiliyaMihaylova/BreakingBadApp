package com.volasoftware.breakingbadapp.di.modules

import com.volasoftware.breakingbadapp.core.repositories.CharactersRepository
import com.volasoftware.breakingbadapp.networking.BreakingBadWrapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(apiWrapper: BreakingBadWrapper): CharactersRepository {
        return CharactersRepository(apiWrapper)
    }
}