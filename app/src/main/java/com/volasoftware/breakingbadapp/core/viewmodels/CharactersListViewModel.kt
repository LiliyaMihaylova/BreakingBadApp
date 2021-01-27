package com.volasoftware.breakingbadapp.core.viewmodels

import android.app.Application
import android.widget.CheckBox
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.volasoftware.breakingbadapp.core.repositories.CharactersRepository
import com.volasoftware.breakingbadapp.networking.models.Character
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(
    application: Application, private val repository: CharactersRepository
) :
    AndroidViewModel(application) {

    private val filteredCharactersLiveData: MutableLiveData<List<Character>> = MutableLiveData()

    fun getCharacters(): LiveData<List<Character>> {
        return repository.getCharacters()
    }

    fun filteringList(
        seasons: List<Pair<Int, CheckBox>>, characters: List<Character>, searchQuery: String
    ): MutableLiveData<List<Character>> {
        val checkedItems = seasons.filter { season -> season.second.isChecked }.map { it.first }
        val notNullCharacters = characters.filter { it.appearances != null }
        filteredCharactersLiveData.postValue(
            notNullCharacters.filter { character ->
                checkedItems.all {
                    character.appearances!!.contains(it + 1)
                } && character.name.contains(searchQuery, true)
            })

        return filteredCharactersLiveData
    }

}