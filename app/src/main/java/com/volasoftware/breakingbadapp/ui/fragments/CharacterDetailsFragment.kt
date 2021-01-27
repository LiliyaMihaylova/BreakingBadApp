package com.volasoftware.breakingbadapp.ui.fragments

import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import com.volasoftware.breakingbadapp.R
import com.volasoftware.breakingbadapp.core.viewmodels.CharacterDetailsViewModel
import com.volasoftware.breakingbadapp.databinding.FragmentCharacterDetailsBinding
import com.volasoftware.breakingbadapp.networking.models.Character
import javax.inject.Inject

class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterDetailsBinding, CharacterDetailsFragment.CharacterDetailsFragmentListener>() {

    private lateinit var character: Character

    @Inject
    lateinit var viewModel: CharacterDetailsViewModel

    override fun onFragmentViewCreated(view: View, savedInstanceState: Bundle?) {
        character = CharacterDetailsFragmentArgs.fromBundle(requireArguments()).character
        setupView()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_character_details
    }

    override fun setupToolbar() {}

    private fun setupView() {
        loadImage(character.img)
        viewModel.getOccupationsAsString(character).observe(viewLifecycleOwner) {
            binding.txtOccupation.text = it
        }

        binding.txtName.text = character.name
        binding.txtStatus.text = character.status
        binding.txtNickname.text = character.nickname
        binding.txtSeasons.text = character.appearances?.joinToString()
    }

    private fun loadImage(url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_person_placeholder)
            .error(R.drawable.ic_person_placeholder)
            .into(binding.imgCharacterDetails)
    }

    interface CharacterDetailsFragmentListener {}
}