package com.volasoftware.breakingbadapp.ui.fragments

import android.os.Bundle
import android.view.View
import com.volasoftware.breakingbadapp.R
import com.volasoftware.breakingbadapp.databinding.FragmentCharactersListBinding

class CharactersListFragment :
    BaseFragment<FragmentCharactersListBinding, CharactersListFragment.CharactersListFragmentListener>() {


    override fun onFragmentViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_characters_list
    }

    interface CharactersListFragmentListener {}
}