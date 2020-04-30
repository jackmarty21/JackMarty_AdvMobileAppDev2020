package com.example.lab6.ui.details

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.lab6.R
import com.example.lab6.data.Cocktail
import com.example.lab6.ui.favorites.SharedFavoritesViewModel
import com.example.lab6.ui.search.SearchViewModel

class CocktailDetailFragment : Fragment(){

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var favoritesVM: SharedFavoritesViewModel
    private lateinit var currentCocktail: Cocktail
    private lateinit var ingredientListView: RecyclerView
    private lateinit var recipeTitleTextView: TextView
    private lateinit var instructionsTextView: TextView
    private lateinit var imageView :ImageView

    private val updateViewWithDetails = Observer<Cocktail> {
        currentCocktail = it

        //set the title in the action bar
        (activity as AppCompatActivity?)?.supportActionBar?.title = it.strDrink
        //set the title textview
        recipeTitleTextView.text = it.strDrink

        //images can be fetched using the following pattern: https://spoonacular.com/recipeImages/{ID}-{SIZE}.{TYPE}
        Glide.with(this)
            .load("${it.strDrinkThumb}")
            .into(imageView)

        //get ingredient names
        val ingredientList = mutableListOf<String>()

        it.strIngredient1?.let { it1 -> ingredientList.add(it1 + "......" + it.strMeasure1) }
        it.strIngredient2?.let { it1 -> ingredientList.add(it1 + "......" + it.strMeasure2) }
        it.strIngredient3?.let { it1 -> ingredientList.add(it1 + "......" + it.strMeasure3) }
        it.strIngredient4?.let { it1 -> ingredientList.add(it1 + "......" + it.strMeasure4) }

        //add instantiate and use adapter for recyclerview
        val adapter =
            IngredientsRecycleAdapter(
                requireContext(),
                ingredientList
            )
        ingredientListView.adapter = adapter

        //set instructions textview
        instructionsTextView.text = it.strInstructions
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        favoritesVM = ViewModelProvider(requireActivity()).get(SharedFavoritesViewModel::class.java)

        //hide the bottom nav since we've moved down in the view hierarchy
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = android.view.View.GONE

        val root = inflater.inflate(R.layout.fragment_cocktail_detail, container, false)

        //references to the necessary views
        ingredientListView = root.findViewById<RecyclerView>(R.id.ingredientsListView)
        recipeTitleTextView = root.findViewById<TextView>(R.id.recipeTitleTextView)
        instructionsTextView = root.findViewById<TextView>(R.id.instructionsTextView)
        imageView = root.findViewById<ImageView>(R.id.recipeImageView)

        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        //we'll populate the details in this Observer as soon as we get them
        searchViewModel.selectedCocktail.observe(viewLifecycleOwner, updateViewWithDetails)
        favoritesVM.favList.observe(viewLifecycleOwner, updateViewWithDetails)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.favoriteRecipe) {
            if(item.title == getString(R.string.save)) {
                item.icon = ResourcesCompat.getDrawable(resources, android.R.drawable.btn_star_big_on, null)
                item.title = getString(R.string.remove)
                favoritesVM.addFavorite(currentCocktail)
            } else {
                item.icon = ResourcesCompat.getDrawable(resources, android.R.drawable.btn_star_big_off, null)
                item.title = getString(R.string.save)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}