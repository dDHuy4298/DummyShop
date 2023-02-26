package com.sun.dummyshop.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.dummyshop.base.BaseViewModel
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.repository.ProductRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class FavoriteViewModel(
    private val repository: ProductRepository
) : BaseViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _itemInCartQuantity = MutableLiveData<Int>()
    val itemInCartQuantity: LiveData<Int>
        get() = _itemInCartQuantity

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    init {
        getFavoriteProducts()
    }

    private fun getFavoriteProducts() {
        repository.getFavoriteProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _products.value = it
                _isEmpty.value = it.isEmpty()
            }, {
                _error.value = it.message.toString()
                _isEmpty.value = true
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
}
