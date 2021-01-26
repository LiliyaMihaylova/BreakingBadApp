package com.volasoftware.breakingbadapp.core.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.volasoftware.breakingbadapp.core.repositories.CharactersRepository
import com.volasoftware.breakingbadapp.networking.models.Character
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(
    application: Application, private val repository: CharactersRepository
) :
    AndroidViewModel(application) {

    fun getCharacters(): LiveData<List<Character>> {
        return repository.getCharacters()
    }

}