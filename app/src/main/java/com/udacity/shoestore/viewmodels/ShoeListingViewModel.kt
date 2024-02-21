package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListingViewModel : ViewModel() {

    private var mSelectedShoeIndex = -1

    private val mSelectedShoeLiveData = MutableLiveData<Shoe>()
    val selectedShoeLiveData: LiveData<Shoe>
        get() = mSelectedShoeLiveData

    private val mShoesLiveData = MutableLiveData<ArrayList<Shoe>>()
    val shoesLiveData: LiveData<ArrayList<Shoe>>
        get() = mShoesLiveData

    init {
        createInitialListOfShoes()
    }

    fun addShoe(shoe: Shoe) {
        if (mShoesLiveData.value == null) {
            mShoesLiveData.value = ArrayList()
        }
        mShoesLiveData.value?.add(shoe)
        mShoesLiveData.notifyObserver()
    }

    fun addOrUpdateSelectedShoe() {
        if (mSelectedShoeIndex > -1) {
            mSelectedShoeLiveData.value?.let { mShoesLiveData.value?.set(mSelectedShoeIndex, it) }
            resetSelectedShoeIndex()
        } else {
            mSelectedShoeLiveData.value?.let { addShoe(it) }
        }
    }

    fun initializeSelectedShoe(shoe: Shoe?) {
        if (shoe == null) {
            mSelectedShoeLiveData.value = Shoe("", 0.0, "", "", listOf(getRandomShoeImg()))
            resetSelectedShoeIndex()
        } else {
            mSelectedShoeLiveData.value = shoe.copy()
            // Check if shoe exists in mShoesLiveData
            val index = mShoesLiveData.value?.indexOf(shoe)
            if (index != null && index > -1) {
                mSelectedShoeIndex = index
            }
        }
    }

    private fun createInitialListOfShoes() {
        addShoe(Shoe("Revolution X", 11.0, "Nike", "Next Nature Running Shoes with 100% recycled laces", listOf(getRandomShoeImg())))
        addShoe(Shoe("Revolution Y", 12.0, "Nike", "Next Nature Running Shoes with 100% recycled laces", listOf(getRandomShoeImg())))
        addShoe(Shoe("Revolution Z", 13.0, "Nike", "Next Nature Running Shoes with 100% recycled laces", listOf(getRandomShoeImg())))
    }

    private fun getRandomShoeImg() : String {
        val images = mutableListOf(
            "drawable/img_shoe_blue",
            "drawable/img_shoe_brown",
            "drawable/img_shoe_green",
            "drawable/img_shoe_pink",
            "drawable/img_shoe_purple",
            "drawable/img_shoe_red",
            "drawable/img_shoe_white",
            "drawable/img_shoe_yellow"
        )
        return images.random()
    }

    private fun resetSelectedShoeIndex() {
        mSelectedShoeIndex = -1
    }

}

// Update LiveData with its own value to notify observers when it's value changes
fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}