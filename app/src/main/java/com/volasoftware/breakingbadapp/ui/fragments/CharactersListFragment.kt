package com.volasoftware.breakingbadapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.volasoftware.breakingbadapp.R
import com.volasoftware.breakingbadapp.core.viewmodels.CharactersListViewModel
import com.volasoftware.breakingbadapp.databinding.FragmentCharactersListBinding
import com.volasoftware.breakingbadapp.networking.models.Character
import com.volasoftware.breakingbadapp.ui.adapters.characters.CharactersAdapter
import com.volasoftware.breakingbadapp.ui.adapters.characters.OnItemSelectListener
import com.volasoftware.breakingbadapp.utils.PixelConverter
import javax.inject.Inject


class CharactersListFragment :
    BaseFragment<FragmentCharactersListBinding, CharactersListFragment.CharactersListFragmentListener>(),
    OnItemSelectListener {

    private lateinit var adapter: CharactersAdapter
    private var characters = mutableListOf<Character>()
    private var seasons = mutableListOf<Pair<Int, CheckBox>>()
    private var searchQuery: String = ""

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
            generateSeasonCheckBoxes()
        }
    }

    override fun setupToolbar() {
        setupSearchView()
        setupFilterView()
    }

    private fun setupSearchView() {
        binding.searchToolbar.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                searchQuery = query
                filteringList()
                return false
            }
        })

        binding.searchToolbar.searchView.setOnSearchClickListener {
            binding.searchToolbar.txtSearchToolbarTitle.visibility = View.GONE
        }

        binding.searchToolbar.searchView.setOnCloseListener {
            binding.searchToolbar.txtSearchToolbarTitle.visibility = View.VISIBLE
            false
        }
    }

    private fun setupFilterView() {
        binding.searchToolbar.btnFilterList.setOnClickListener {
            if (binding.checkBoxesContainer.isVisible) {
                binding.checkBoxesContainer.visibility = View.GONE
            } else {
                binding.checkBoxesContainer.visibility = View.VISIBLE
            }
        }
    }

    private fun generateSeasonCheckBoxes() {
        val character = characters.maxByOrNull { it.appearances?.size ?: 0 }
        val seasonsSize: Int = character?.appearances?.size ?: 0
        for (i in 0 until seasonsSize) {
            val checkBox = CheckBox(context)
            checkBox.text = character?.appearances?.get(i).toString()
            checkBox.id = i

            checkBox.setOnCheckedChangeListener { view, isChecked ->
                seasons[view.id] = view.id to checkBox
                filteringList()
            }
            checkBox.setPadding(
                0,
                PixelConverter.dpToPx(16),
                PixelConverter.dpToPx(16),
                PixelConverter.dpToPx(16)
            )
            seasons.add(i to checkBox)
            binding.checkBoxesContainer.addView(checkBox)
        }
    }

    private fun filteringList() {
        viewModel.filteringList(seasons, characters, searchQuery).observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onItemSelected(item: Character) {
        navigateTo(
            action = CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailsFragment(
                item
            )
        )
    }

    interface CharactersListFragmentListener
}
