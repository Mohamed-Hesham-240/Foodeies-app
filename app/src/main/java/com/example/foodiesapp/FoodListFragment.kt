package com.example.foodiesapp

import Product
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodiesapp.login.userInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodListFragment : Fragment() {
    lateinit var products :ArrayList<Product>
    lateinit var imageList:ArrayList<Bitmap>
    lateinit var productsActivity: ProductsActivity
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_food_list, container, false)
        //
        //products = fitchProductsList()
        imageList = createImageList(products)
        //
        val layoutManager = LinearLayoutManager(context)
        val foodRv = view.findViewById<RecyclerView>(R.id.food_list_rv)
        val adapter = FoodAdapter(imageList)
        foodRv.adapter = adapter
        foodRv.layoutManager = layoutManager
        adapter.setOnItemClickListener(object :FoodAdapter.OnItemCLickListener{
            override fun onItemCLick(position: Int) {
                productsActivity.updateDescriptionFragment(position)
                productsActivity.currentItem = position
            }

        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productsActivity = activity as ProductsActivity
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //private fun fitchProductsList(): ArrayList<Product>{}
    private fun makeRequest()
    {

        val api = Retrofit.Builder()
            .baseUrl("https://murmuring-temple-54993.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(userInterface::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getallProducts(MainActivity.token)

                for (product in response.products) {
                    Log.d("MainActivity", "Result + $product")
                   // addToList(article.title, article.description, article.image, article.url)
                }

                withContext(Dispatchers.Main) {
                   // setUpRecyclerView()
                }
            } catch (e: Exception) {
                Log.d("MainActivity", e.toString())

            }
        }

    }
   // private fun addToList(product :Product) {}
    private fun createImageList(products: ArrayList<Product>): ArrayList<Bitmap>{
        val images: ArrayList<Bitmap> = ArrayList(0)
        for (product in products){
            images.add(product.image)
        }
        return images
    }
}