package com.udacity.shoestore.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.MainActivityViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentShoeDetailBinding
    private lateinit var mShoe: Shoe

    private val mViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        val shoeDetailFragmentArgs by navArgs<ShoeDetailFragmentArgs>()

        mShoe = mViewModel.initializeSelectedShoe(shoeDetailFragmentArgs.shoe)
        mBinding.shoe = mShoe

        setShoeImage()
        setOnClickListeners()
        return mBinding.root
    }

    private fun setOnClickListeners() {
        mBinding.btnShoeDetailSave.setOnClickListener { view: View ->
            setShoeFromBinding()
            mViewModel.addOrUpdateSelectedShoe(mShoe)
            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
        }
        mBinding.btnShoeDetailCancel.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
        }
    }

    private fun setShoeImage() {
        if (mShoe.images.isNotEmpty()) {
            val imageUri = mShoe.images.first()
            val imageResource = resources.getIdentifier(imageUri, null, activity?.packageName)
            mBinding.ivShoeDetail.setImageDrawable(ResourcesCompat.getDrawable(resources, imageResource, null))
        }
    }

    private fun setShoeFromBinding() {
        mShoe.name = mBinding.etShoeDetailName.text.toString()
        mShoe.company = mBinding.etShoeDetailCompany.text.toString()
        val shoeSizeString = mBinding.etShoeDetailSize.text.toString()
        if (shoeSizeString.isNotEmpty()) {
            mShoe.size = shoeSizeString.toDouble()
        } else {
            mShoe.size = 0.0
        }
        mShoe.description = mBinding.etShoeDetailDescription.text.toString()
    }

}