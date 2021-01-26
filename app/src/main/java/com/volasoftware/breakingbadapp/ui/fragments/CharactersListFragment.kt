package com.volasoftware.breakingbadapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
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
    private var characters = mutableListOf<Character>()

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
            characters = it as MutableList<Character>
            adapter.setItems(it)
        }
    }

    override fun setupToolbar() {
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchToolbar.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                adapter.setItems(characters.filter { it.name.contains(query, true) })
                return false
            }
        })

        binding.searchToolbar.searchView.setOnSearchClickListener {
            binding.searchToolbar.txtToolbarTitle.visibility = View.GONE
        }

        binding.searchToolbar.searchView.setOnCloseListener {
            binding.searchToolbar.txtToolbarTitle.visibility = View.VISIBLE
            false
        }
    }

    override fun onItemSelected(item: Character) {}

    interface CharactersListFragmentListener {}
}