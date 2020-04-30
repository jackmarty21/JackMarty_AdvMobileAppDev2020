package com.example.lab6.ui.search.results

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.LOG_TAG
import com.example.lab6.R
import com.example.lab6.data.Cocktail
import com.example.lab6.ui.adapters.CocktailRecyclerAdapter
import com.example.lab6.ui.search.SearchViewModel

/**
 * A simple [Fragment] subclass.
 */
class SearchResultsFragment : Fragment(), CocktailRecyclerAdapter.CocktailItemListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //instantiate nav controller reference using its id from the xml of the main activity layout
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

//        get shared instance of view model
        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_search_results, container, false)

        //find the recyclerview
        recyclerView = root.findViewById(R.id.recyclerView)

        //subscribe to data changes in the repository class via the ViewModel
        searchViewModel.cocktailData.observe(viewLifecycleOwner, Observer {
            //instantiate adapter
            val adapter =
                CocktailRecyclerAdapter(
                    requireContext(),
                    it,
                    this
                )
            //set the adapter to the recyclerview
            recyclerView.adapter = adapter
        })

        return root
    }

    override fun onCocktailItemClick(cocktail: Cocktail) {
        Log.i(LOG_TAG, cocktail.toString())
        searchViewModel.selectedCocktail.value = cocktail
        navController.navigate(R.id.action_searchResultsFragment_to_cocktailDetailFragment)
    }

}