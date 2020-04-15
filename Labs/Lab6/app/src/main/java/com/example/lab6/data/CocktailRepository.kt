package com.example.lab6.data

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.lab6.BASE_URL
import com.example.lab6.LOG_TAG
import com.example.lab6.utils.NetworkHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CocktailRepository(val app: Application) {

    //parameterized type property for Moshi
    private val listType = Types.newParameterizedType(List::class.java, Cocktail::class.java)

    //properties for retrofit
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private var service: CocktailDbService

    //fetch the data when the class is instantiated
    init {
        //init the service instance
        service = retrofit.create(CocktailDbService::class.java)
    }

    //    Observer for when the user enters text and presses search in the SearchFragment
    val searchTermEntered =  Observer<String> {
        CoroutineScope(Dispatchers.IO).launch {
            getCocktailList(it)
        }
    }

    //LiveData object consisting of our recipe data
    //we will publish from this class and subscribe from our fragment
    val cocktailData = MutableLiveData<List<Cocktail>>()

    //search the API for recipes based on a searchTerm
    @WorkerThread
    private suspend fun getCocktailList(searchTerm: String) {
        Log.i(LOG_TAG, searchTerm)
        if(NetworkHelper.networkConnected(app)) {
            val response = service.searchCocktails(searchTerm).execute()
            Log.d("debug", "here")
            if(response.body() != null) {
                //successful request
                val responseBody = response.body()
                cocktailData.postValue(responseBody?.drinks?.toList())
            } else {
                //there was an error with the request (or server)
                Log.e(LOG_TAG, "Could not search recipes. Error code: ${response.code()}")
            }
        }
    }

    //    This portion of the class is dedicated to fetching detail for a specific recipe and updating the LiveData object
    val cocktailSelectedObserver =  Observer<Cocktail> {
        CoroutineScope(Dispatchers.IO).launch {
            getCocktailDetails(it)
        }
    }

    //LiveData for the recipe details
    val cocktailDetails = MutableLiveData<List<Cocktail>>()

    //get the raw text from our sample recipe details json
    @WorkerThread
    private suspend fun getCocktailDetails(forCocktail: Cocktail) {
        if(NetworkHelper.networkConnected(app)) {
            val response = service.cocktailDetails(forCocktail.idDrink).execute()
            if(response.body() != null) {
                val responseBody = response.body()
                cocktailDetails.postValue(responseBody?.drinks?.toList())
            } else {
                Log.e(LOG_TAG, "Could not find details for ${forCocktail.strDrink}. Error code ${response.code()}")
            }
        }
    }

}