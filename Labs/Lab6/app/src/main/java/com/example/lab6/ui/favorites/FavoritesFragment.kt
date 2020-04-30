package com.example.lab6.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.LOG_TAG
import com.example.lab6.R
import com.example.lab6.data.Cocktail
import com.example.lab6.data.CocktailRepository
import com.example.lab6.ui.adapters.CocktailRecyclerAdapter


class FavoritesFragment : Fragment(), CocktailRecyclerAdapter.CocktailItemListener {
    private lateinit var favoritesViewModel: SharedFavoritesViewModel

    private lateinit var favRecyclerView: RecyclerView
    private lateinit var adapter: CocktailRecyclerAdapter

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        favoritesViewModel =
            ViewModelProvider(this).get(SharedFavoritesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)

        favRecyclerView = root.findViewById(R.id.favoritesRecyclerView)
        adapter = CocktailRecyclerAdapter(requireContext(), emptyList(),this)
        favRecyclerView.adapter = adapter

        favoritesViewModel.favoriteCocktailList.observe(viewLifecycleOwner, Observer {
            adapter.cocktailList = it
            adapter.notifyDataSetChanged()
        })

        return root
    }

    override fun onCocktailItemClick(cocktail: Cocktail) {
        favoritesViewModel.favSelected(cocktail)
        navController.navigate(R.id.action_navigation_favorites_to_cocktailDetailFragment)
    }
}