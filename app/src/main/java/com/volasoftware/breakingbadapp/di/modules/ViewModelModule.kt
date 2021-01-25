package com.volasoftware.breakingbadapp.di.modules

import android.app.Application
import com.volasoftware.breakingbadapp.ui.viewmodels.CharacterDetailsViewModel
import com.volasoftware.breakingbadapp.ui.viewmodels.CharactersListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideCharactersListViewModel(application: Application): CharactersListViewModel {
        return CharactersListViewModel(application)
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsViewModel(application: Application): CharacterDetailsViewModel {
        return CharacterDetailsViewModel(application)
    }
}