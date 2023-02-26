package com.sun.dummyshop.ui.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.dummyshop.base.BaseViewModel
import com.sun.dummyshop.data.model.Bill
import com.sun.dummyshop.data.repository.BillRepository
import com.sun.dummyshop.data.repository.ProductRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryViewModel(
    private val repository: BillRepository,
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val _bills = MutableLiveData<List<Bill>>()
    val bills: LiveData<List<Bill>>
        get() = _bills

    private val _itemInCartQuantity = MutableLiveData<Int>()
    val itemInCartQuantity: LiveData<Int>
        get() = _itemInCartQuantity

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    init {
        getBills()
    }

    private fun getBills() {
        repository.getBills()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _bills.value = it
                _isEmpty.value = it.isEmpty()
            }, {
                _error.value = it.message.toString()
                _isEmpty.value = true
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
