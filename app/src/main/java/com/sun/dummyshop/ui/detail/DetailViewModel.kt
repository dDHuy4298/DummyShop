package com.sun.dummyshop.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.dummyshop.base.BaseViewModel
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.repository.ProductRepository
import com.sun.dummyshop.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailViewModel(
    private val repository: ProductRepository
) : BaseViewModel() {

    private val _similarProducts = MutableLiveData<List<Product>>()
    val similarProducts: LiveData<List<Product>>
        get() = _similarProducts

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    private val _itemInCartQuantity = MutableLiveData<Int>()
    val itemInCartQuantity: LiveData<Int>
        get() = _itemInCartQuantity

    val currentProduct = MutableLiveData<Product>()
    private var isSaved = false
    private lateinit var savedProduct: Product

    private fun addProductToCart() {
        currentProduct.value?.let { product ->
            val newProduct = product.copy(quantityInCart = Constants.PRODUCT_QUANTITY_UNIT)
            repository.insertProduct(newProduct)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    savedProduct = newProduct
                    isSaved = true
                    getCartItemQuantity()
                }, {
                    _error.value = it.message.toString()
                })
                .addTo(disposable)
        }
    }

    private fun updateQuantity() {
        val newProduct =
            savedProduct.copy(quantityInCart = savedProduct.quantityInCart + Constants.PRODUCT_QUANTITY_UNIT)
        repository.updateProduct(newProduct)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                savedProduct = newProduct
                getCartItemQuantity()
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    private fun addToFavorite() {
        val newProduct = savedProduct.copy(isFavorite = Constants.PRODUCT_IS_FAVORITE)
        repository.insertProduct(newProduct)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isFavorite.value = true
                isSaved = true
                savedProduct = newProduct
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    private fun deleteFromFavorite() {
        val newProduct = savedProduct.copy(isFavorite = Constants.PRODUCT_NOT_FAVORITE)
        repository.updateProduct(newProduct)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isFavorite.value = false
                savedProduct = newProduct
            }, {
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

    fun getSimilar() {
        currentProduct.value?.let { product ->
            repository.getSimilarProducts(product.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _similarProducts.value = it.take(Constants.ITEM_QUANTITY)
                }, {
                    _error.value = it.message.toString()
                })
                .addTo(disposable)
        }
    }

    fun getProductById() {
        currentProduct.value?.let { product ->
            repository.getProductById(product.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isSaved = true
                    savedProduct = it
                }, {
                    if (it.message.toString() == Constants.EXCEPTION_EMPTY_RESULT) {
                        isSaved = false
                        savedProduct = product
                    } else {
                        _error.value = it.message.toString()
                    }
                })
                .addTo(disposable)
        }
    }

    fun addToCart() {
        if (isSaved) {
            updateQuantity()
        } else {
            addProductToCart()
        }
    }

    fun checkFavorite() {
        currentProduct.value?.let { product ->
            repository.isFavoriteProduct(product.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _isFavorite.value = it
                }, {
                    _error.value = it.message.toString()
                })
                .addTo(disposable)
        }
    }

    fun setFavorite() {
        if (isFavorite.value == true) {
            deleteFromFavorite()
        } else {
            addToFavorite()
        }
    }
}
