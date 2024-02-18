package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainActivityViewModel : ViewModel() {

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

}

// Update LiveData with its own value to notify observers when it's value changes
fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}