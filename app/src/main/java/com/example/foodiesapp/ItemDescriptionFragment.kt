package com.example.foodiesapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemDescriptionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemDescriptionFragment : Fragment() {
    private lateinit var productsActivity: ProductsActivity
    lateinit var descriptionTextView : TextView
    lateinit var quantityTextView: TextView
    lateinit var image :ImageView
    lateinit var nameTextView : TextView
    lateinit var priceTextView: TextView
    lateinit var minusTextView: TextView
    lateinit var plusTextView: TextView
    lateinit var addToCartButton : Button
    lateinit var goToCartButton: Button
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productsActivity = activity as ProductsActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_item_description, container, false)
        descriptionTextView = view.findViewById(R.id.dish_description)
        quantityTextView = view.findViewById(R.id.quantity_tv)
        image= view.findViewById(R.id.dish_image_from_description)
        nameTextView = view.findViewById(R.id.dish_name_from_description)
        priceTextView = view.findViewById(R.id.dish_price_from_description)

        addToCartButton = view.findViewById(R.id.add_to_cart_btn)
        addToCartButton.setOnClickListener {
            productsActivity.products[productsActivity.currentItem].bought_items_count =
                quantityTextView.text.toString().toInt()
        }

        goToCartButton = view.findViewById(R.id.go_to_cart_btn)
        goToCartButton.setOnClickListener {
         productsActivity.goToCart()
        }

        plusTextView = view.findViewById(R.id.add_tv)
        plusTextView.setOnClickListener {
            var newValue : Int = quantityTextView.text.toString().toInt() + 1
            quantityTextView.text = "" + newValue
        }

        minusTextView = view.findViewById(R.id.minus_tv)
        minusTextView.setOnClickListener {
            var oldValue: Int = quantityTextView.text.toString().toInt()
            if (oldValue > 0 )
                --oldValue
            quantityTextView.text = "" + oldValue
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ItemDescriptionFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ItemDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}