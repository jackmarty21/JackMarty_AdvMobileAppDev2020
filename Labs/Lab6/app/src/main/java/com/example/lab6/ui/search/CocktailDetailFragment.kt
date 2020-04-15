package com.example.lab6.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.lab6.IMAGE_BASE_URL
import com.example.lab6.R

import kotlinx.android.synthetic.main.fragment_cocktail_detail.*

class CocktailDetailFragment : Fragment(){

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //hide the bottom nav since we've moved down in the view hierarchy
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = android.view.View.GONE

        val root = inflater.inflate(R.layout.fragment_cocktail_detail, container, false)

        //references to the necessary views
        val ingredientListView = root.findViewById<RecyclerView>(R.id.ingredientsListView)
        val recipeTitleTextView = root.findViewById<TextView>(R.id.recipeTitleTextView)
        val instructionsTextView = root.findViewById<TextView>(R.id.instructionsTextView)
        val imageView = root.findViewById<ImageView>(R.id.recipeImageView)

        searchViewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        //we'll populate the details in this Observer as soon as we get them
        searchViewModel.selectedCocktail.observe(viewLifecycleOwner, Observer {
            Log.i("cocktailLogging", "Selected recipe instructions: ${it.strDrink}")

            //get ingredient names
            val ingredientList = mutableListOf<String>()

            it.strIngredient1?.let { it1 -> ingredientList.add(it1 + "......" + it.strMeasure1) }
            it.strIngredient2?.let { it1 -> ingredientList.add(it1 + "......" + it.strMeasure2) }
            it.strIngredient3?.let { it1 -> ingredientList.add(it1 + "......" + it.strMeasure3) }
            it.strIngredient4?.let { it1 -> ingredientList.add(it1 + "......" + it.strMeasure4) }

            //add instantiate and use adapter for recyclerview
            val adapter = IngredientsRecycleAdapter(requireContext(), ingredientList)
            ingredientListView.adapter = adapter

            //set instructions textview
            instructionsTextView.text = it.strInstructions
        })

        //we'll use this Observer to set the titles and load the image as soon as we know what recipe we'll be displaying
        searchViewModel.selectedCocktail.observe(viewLifecycleOwner, Observer{
            //set the title in the action bar
            (activity as AppCompatActivity?)?.supportActionBar?.title = it.strDrink
            //set the title textview
            recipeTitleTextView.text = it.strDrink

            //images can be fetched using the following pattern: https://spoonacular.com/recipeImages/{ID}-{SIZE}.{TYPE}
//            Glide.with(this)
//                .load("${IMAGE_BASE_URL}/${it.idDrink}-556x370.jpg")
//                .into(imageView)
        })

        return root
    }

}