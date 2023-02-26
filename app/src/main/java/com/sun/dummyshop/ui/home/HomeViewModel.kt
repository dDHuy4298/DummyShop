package com.sun.dummyshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.dummyshop.base.BaseViewModel
import com.sun.dummyshop.data.model.Category
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.repository.CategoryRepository
import com.sun.dummyshop.data.repository.ProductRepository
import com.sun.dummyshop.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    private val _topRatingProducts = MutableLiveData<List<Product>>()
    val topRatingProducts: LiveData<List<Product>>
        get() = _topRatingProducts

    private val _topSellingProducts = MutableLiveData<List<Product>>()
    val topSellingProducts: LiveData<List<Product>>
        get() = _topSellingProducts

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _itemInCartQuantity = MutableLiveData<Int>()
    val itemInCartQuantity: LiveData<Int>
        get() = _itemInCartQuantity

    init {
        getCategories()
        getTopRatingProducts()
        getTopSellingProducts()
    }

    private fun getTopRatingProducts() {
        _isLoading.value = true
        productRepository.getTopRatingProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _topRatingProducts.value = it.take(Constants.ITEM_QUANTITY)
                _isLoading.value = false
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    private fun getTopSellingProducts() {
        _isLoading.value = true
        productRepository.getTopSellingProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _topSellingProducts.value = it.take(Constants.ITEM_QUANTITY)
                _isLoading.value = false
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    private fun getCategories() {
        _isLoading.value = true
        categoryRepository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _categories.value = it
                _isLoading.value = false
            }, {
                _isLoading.value = false
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun getCartItemQuantity() {
        productRepository.getItemInCartQuantity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _itemInCartQuantity.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }
}
