package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListingItemFragment : Fragment() {

    private lateinit var mShoe: Shoe

    fun newInstance (shoe: Shoe) : ShoeListingItemFragment {
        val fragment = ShoeListingItemFragment()
        fragment.mShoe = shoe
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentShoeListingItemBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_listing_item, container, false)
        binding.shoe = mShoe
        return binding.root
    }

}