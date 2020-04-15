package com.example.lab6.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.R
import com.example.lab6.data.Cocktail

class SearchRecyclerAdapter(val context: Context, val cocktailList: List<Cocktail>, val itemListener: CocktailItemListener) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText = itemView.findViewById<TextView>(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cocktail_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = cocktailList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curCocktail = cocktailList[position]

        holder.titleText.text = curCocktail.strDrink

        //pass the data item to the fragment click listener
        holder.itemView.setOnClickListener {
            itemListener.onCocktailItemClick(curCocktail)
        }
    }

    interface CocktailItemListener {
        fun onCocktailItemClick(cocktail: Cocktail)
    }
}