package com.example.foodiesapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        products = fitchProductsList()
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

    private fun fitchProductsList(): ArrayList<Product>{
        //get it from the database
        val img1 : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dish1)
        val img2 : Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dish3)
        return arrayListOf(
            Product(0,"asfafg  nvosindpvnsnsdvsdbsdb",1.0,img1, "abc", false,0),
            Product(1,"b",2.0, img2,"sdf",false,0),
            Product(2,"c",3.0,img1,"sdg",false,0),
            Product(3,"d",4.0,img2,"gfj",false,0),
            Product(4,"e",5.0,img1,"dfh", false,0),
            Product(5,"f",6.0,img2,"fgn", false,0),
            Product(6,"g",7.0,img1,"fhmt", false,0)
        )
    }

    private fun createImageList(products: ArrayList<Product>): ArrayList<Bitmap>{
        val images: ArrayList<Bitmap> = ArrayList(0)
        for (product in products){
            images.add(product.image)
        }
        return images
    }
}