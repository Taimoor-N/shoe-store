package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.MainActivityViewModel

class ShoeListingFragment : Fragment() {

    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var mBinding: FragmentShoeListingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing,
            container, false)
        mViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        mBinding.lifecycleOwner = this

        mBinding.fabShoeListingAddShoe.setOnClickListener { view: View ->
            view.findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
        }

        // Observe shoesLiveData and recreate the shoe list upon any changes
        mViewModel.shoesLiveData.observe(viewLifecycleOwner) { shoes ->
            mBinding.llShoeListing.removeAllViews()
            for (shoe in shoes) {
                createAndAddShoeListingItemFragment(shoe)
            }
        }

        addMenuItems()
        return mBinding.root
    }

    private fun addMenuItems() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.logout_menu, menu)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                // Handle the menu selection
                return when (item.itemId) {
                    R.id.menu_logout -> {
                        requireView().findNavController().navigate(R.id.loginFragment)
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun createAndAddShoeListingItemFragment(shoe: Shoe) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        val shoeListingItemFragment = ShoeListingItemFragment().newInstance(shoe)
        fragmentTransaction.add(mBinding.llShoeListing.id, shoeListingItemFragment, "ShoeListingItemFragment")
        fragmentTransaction.commit()
    }

}