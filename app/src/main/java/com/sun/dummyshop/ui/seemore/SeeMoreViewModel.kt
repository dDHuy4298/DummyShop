package com.sun.dummyshop.ui.seemore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.dummyshop.base.BaseViewModel
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.repository.ProductRepository
import com.sun.dummyshop.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class SeeMoreViewModel(
    private val repository: ProductRepository
) : BaseViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _itemInCartQuantity = MutableLiveData<Int>()
    val itemInCartQuantity: LiveData<Int>
        get() = _itemInCartQuantity

    private fun getTopSelling() {
        _isLoading.value = true
        repository.getTopSellingProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _products.value = it
                _title.value = Constants.TITLE_TOP_SELLING
                _isLoading.value = false
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    private fun getTopRating() {
        _isLoading.value = true
        repository.getTopRatingProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _products.value = it
                _title.value = Constants.TITLE_TOP_RATING
                _isLoading.value = false
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    private fun getSimilarProducts(id: String) {
        _isLoading.value = true
        repository.getSimilarProducts(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _products.value = it
                _title.value = Constants.TITLE_SIMILAR
                _isLoading.value = false
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun getCartItemQuantity() {
        repository.getItemInCartQuantity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _itemInCartQuantity.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun getProducts(type: String) {
        when (type) {
            Constants.BUNDLE_TOP_RATING -> getTopRating()
            Constants.BUNDLE_TOP_SELLING -> getTopSelling()
            else -> getSimilarProducts(type)
        }
    }
}
