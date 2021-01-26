package com.volasoftware.breakingbadapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.volasoftware.breakingbadapp.R
import com.volasoftware.breakingbadapp.core.viewmodels.CharactersListViewModel
import com.volasoftware.breakingbadapp.databinding.FragmentCharactersListBinding
import com.volasoftware.breakingbadapp.networking.models.Character
import com.volasoftware.breakingbadapp.ui.adapters.characters.CharactersAdapter
import com.volasoftware.breakingbadapp.ui.adapters.characters.OnItemSelectListener
import javax.inject.Inject

class CharactersListFragment :
    BaseFragment<FragmentCharactersListBinding, CharactersListFragment.CharactersListFragmentListener>(),
    OnItemSelectListener {

    private lateinit var adapter: CharactersAdapter

    @Inject
    lateinit var viewModel: CharactersListViewModel

    override fun onFragmentViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        loadCharacters()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_characters_list
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter(this)
        binding.rvCharacters.layoutManager = LinearLayoutManager(context)
        binding.rvCharacters.adapter = adapter
    }

    private fun loadCharacters() {
        viewModel.getCharacters().observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onItemSelected(item: Character) {}

    interface CharactersListFragmentListener {}
}