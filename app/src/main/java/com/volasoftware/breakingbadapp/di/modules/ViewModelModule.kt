package com.volasoftware.breakingbadapp.di.modules

import android.app.Application
import com.volasoftware.breakingbadapp.core.repositories.CharactersRepository
import com.volasoftware.breakingbadapp.core.viewmodels.CharacterDetailsViewModel
import com.volasoftware.breakingbadapp.core.viewmodels.CharactersListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideCharactersListViewModel(
        application: Application, repository: CharactersRepository
    ): CharactersListViewModel {
        return CharactersListViewModel(application, repository)
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsViewModel(application: Application): CharacterDetailsViewModel {
        return CharacterDetailsViewModel(
            application
        )
    }
}