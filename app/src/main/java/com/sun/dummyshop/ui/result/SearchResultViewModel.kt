package com.sun.dummyshop.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.dummyshop.base.BaseViewModel
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.repository.ProductRepository
import com.sun.dummyshop.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchResultViewModel(
    private val repository: ProductRepository
) : BaseViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    var minPrice = MutableLiveData<String>()
    var maxPrice = MutableLiveData<String>()

    fun searchProductsWithFilters(keyword: String, priceFilter: String, ratingFilter: String) {
        _isLoading.value = true
        repository.searchProductsWithFilters(keyword, priceFilter, ratingFilter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _products.value = it
                _isEmpty.value = false
            }, {
                _isLoading.value = false
                if (it.message.toString() == Constants.EXCEPTION_EMPTY_SEARCH_RESULT) {
                    _isEmpty.value = true
                    _products.value = emptyList()
                } else {
                    _error.value = it.message.toString()
                }
            })
            .addTo(disposable)
    }
}
