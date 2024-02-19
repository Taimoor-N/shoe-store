package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListingItemFragment : Fragment() {

    private lateinit var mShoe: Shoe
    private lateinit var mBinding: FragmentShoeListingItemBinding

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing_item, container, false)
        mBinding.shoe = mShoe
        setShoeImage()
        setOnClickListeners()
        return mBinding.root
    }

    private fun setShoeImage() {
        val shoe = mBinding.shoe
        if (shoe != null && shoe.images.isNotEmpty()) {
            val imageUri = shoe.images.first()
            val imageResource = resources.getIdentifier(imageUri, null, activity?.packageName)
            mBinding.ivShoeListingItem.setImageDrawable(ResourcesCompat.getDrawable(resources, imageResource, null))
        }
    }

    private fun setOnClickListeners() {
        mBinding.layoutShoeListingItem.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment(mShoe))
        }
    }

}