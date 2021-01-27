package com.volasoftware.breakingbadapp.core.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.volasoftware.breakingbadapp.networking.models.Character
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val liveData: MutableLiveData<String> = MutableLiveData()

    fun getOccupationsAsString(character: Character): MutableLiveData<String> {
        var occupationText = ""
        character.occupations?.forEachIndexed { index, occupation ->
            occupationText += if (index != character.occupations.size - 1) {
                ("$occupation,\n")
            } else {
                occupation
            }
        }
        liveData.postValue(occupationText)
        return liveData
    }
}