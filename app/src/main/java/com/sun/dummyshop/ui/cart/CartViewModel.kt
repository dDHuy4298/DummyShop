package com.sun.dummyshop.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.dummyshop.base.BaseViewModel
import com.sun.dummyshop.data.model.Bill
import com.sun.dummyshop.data.model.CartProduct
import com.sun.dummyshop.data.model.CartBody
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.repository.BillRepository
import com.sun.dummyshop.data.repository.ProductRepository
import com.sun.dummyshop.utils.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class CartViewModel(
    private val productRepository: ProductRepository,
    private val billRepository: BillRepository
) : BaseViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _bill = MutableLiveData<Bill>()
    val bill: LiveData<Bill>
        get() = _bill

    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int>
        get() = _total

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    private fun saveBill(bill: Bill) {
        billRepository.insertBill(bill)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    private fun calculateAmount() {
        products.value?.let { list ->
            if (list.isNotEmpty())
                _total.value = list.map { it.price.toInt() * it.quantityInCart }
                    .reduce { sum, element -> sum + element }
            else
                _total.value = Constants.CART_DEFAULT_AMOUNT
        }
    }

    private fun removeAllFromCart() {
        products.value?.let { list ->
            list.forEach {
                removeFromCart(it)
            }
        }
    }

    fun getProductsAddedToCart() {
        productRepository.getAddedToCartProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _products.value = it
                _isEmpty.value = it.isEmpty()
                calculateAmount()
            }, {
                _isEmpty.value = true
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun checkout() {
        _isLoading.value = true
        productRepository.getAddedToCartProducts()
            .map {
                it.map { product ->
                    CartProduct(product.id, product.quantityInCart.toString())
                }
            }
            .flatMap {
                val checkoutBody = CartBody(it)
                billRepository.checkout(checkoutBody)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isLoading.value = false
                _bill.postValue(it)
                removeAllFromCart()
                saveBill(it)
            }, {
                _isLoading.value = false
                _error.postValue(it.message.toString())
            })
            .addTo(disposable)
    }

    fun removeFromCart(product: Product) {
        productRepository.removeFromCart(product.copy(quantityInCart = Constants.PRODUCT_DEFAULT_QUANTITY))
            .flatMap {
                productRepository.getAddedToCartProducts()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _products.value = it
                calculateAmount()
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun addQuantity(product: Product) {
        productRepository.updateProduct(product.copy(quantityInCart = product.quantityInCart + Constants.PRODUCT_QUANTITY_UNIT))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getProductsAddedToCart()
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun minusQuantity(product: Product) {
        productRepository.updateProduct(product.copy(quantityInCart = product.quantityInCart - Constants.PRODUCT_QUANTITY_UNIT))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getProductsAddedToCart()
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }
}
