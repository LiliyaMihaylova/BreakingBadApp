package com.volasoftware.breakingbadapp.core.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.volasoftware.breakingbadapp.networking.BreakingBadWrapper
import com.volasoftware.breakingbadapp.networking.models.Character
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val apiWrapper: BreakingBadWrapper) {


    private val charactersLiveData: MutableLiveData<List<Character>> = MutableLiveData()

    fun getCharacters(): LiveData<List<Character>> {
        apiWrapper.getCharacters(object : BreakingBadWrapper.DataListener<List<Character>> {
            override fun onDataReceived(data: List<Character>) {
                charactersLiveData.postValue(data)
            }

            override fun onDataFailed() {}
        })

        return charactersLiveData
    }
}