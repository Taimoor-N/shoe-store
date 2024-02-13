package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding

class ShoeListingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentShoeListingBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_listing, container, false)
        binding.fabShoeListingAddShoe.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
        }
        return binding.root
    }

}