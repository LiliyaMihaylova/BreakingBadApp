package com.volasoftware.breakingbadapp.di.modules

import com.volasoftware.breakingbadapp.ui.fragments.CharacterDetailsFragment
import com.volasoftware.breakingbadapp.ui.fragments.CharactersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun bindCharactersListFragment(): CharactersListFragment

    @ContributesAndroidInjector
    abstract fun bindCharacterDetailsFragment(): CharacterDetailsFragment
}