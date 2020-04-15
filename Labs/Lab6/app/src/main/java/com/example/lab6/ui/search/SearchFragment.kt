package com.example.lab6.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lab6.R

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var navController: NavController

    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //instance of ViewModel
        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        //instantiate nav controller reference using its id from the xml of the main activity layout
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val root = inflater.inflate(R.layout.fragment_search, container, false)

        searchButton = root.findViewById(R.id.searchButton)
        searchEditText = root.findViewById(R.id.searchInput)

        searchButton.setOnClickListener {
            searchRecipes()
        }

        return root
    }

    private fun searchRecipes() {
        val searchTerm = searchEditText.text.toString()
        if(searchTerm != null && searchTerm != "") {
            searchViewModel.searchUserInput.value = searchTerm

            navController.navigate(R.id.action_navigation_search_to_searchResultsFragment)
        }
    }
}