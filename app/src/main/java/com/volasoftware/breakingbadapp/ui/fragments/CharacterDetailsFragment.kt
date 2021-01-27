package com.volasoftware.breakingbadapp.ui.fragments

import android.os.Bundle
import android.view.View
import com.volasoftware.breakingbadapp.R
import com.volasoftware.breakingbadapp.databinding.FragmentCharacterDetailsBinding

class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterDetailsBinding, CharacterDetailsFragment.CharacterDetailsFragmentListener>() {


    override fun onFragmentViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_character_details
    }

    override fun setupToolbar() {

    }

    interface CharacterDetailsFragmentListener {}
}