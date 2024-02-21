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
import com.udacity.shoestore.viewmodels.ShoeListingViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentShoeDetailBinding

    private val mViewModel: ShoeListingViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        val shoeDetailFragmentArgs by navArgs<ShoeDetailFragmentArgs>()

        mViewModel.initializeSelectedShoe(shoeDetailFragmentArgs.shoe)
        mBinding.shoeListingViewModel = mViewModel

        setShoeImage()
        setOnClickListeners()
        return mBinding.root
    }

    private fun setOnClickListeners() {
        mBinding.btnShoeDetailSave.setOnClickListener { view: View ->
            mViewModel.addOrUpdateSelectedShoe()
            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
        }
        mBinding.btnShoeDetailCancel.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListingFragment())
        }
    }

    private fun setShoeImage() {
        if (mViewModel.selectedShoeLiveData.value?.images?.isNotEmpty() == true) {
            val imageUri = mViewModel.selectedShoeLiveData.value?.images?.first()
            val imageResource = resources.getIdentifier(imageUri, null, activity?.packageName)
            mBinding.ivShoeDetail.setImageDrawable(ResourcesCompat.getDrawable(resources, imageResource, null))
        }
    }

}