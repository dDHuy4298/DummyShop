package com.sun.dummyshop.data.source.local

import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.source.ProductDataSource
import com.sun.dummyshop.data.source.local.dao.ProductDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ProductLocalDataSource(
    private val productDao: ProductDao
) : ProductDataSource.Local {

    override fun getFavoriteProducts(): Observable<List<Product>> =
        productDao.getFavoriteProducts()

    override fun insertProduct(product: Product): Completable =
        productDao.insertProduct(product)

    override fun deleteFavoriteProduct(product: Product): Completable =
        productDao.deleteFavoriteProduct(product)

    override fun updateProduct(product: Product): Completable =
        productDao.updateProduct(product)

    override fun isFavoriteProduct(id: String): Single<Boolean> =
        productDao.getFavoriteProductById(id).map { it.isNotEmpty() }

    override fun isAddedToCart(id: String): Single<Boolean> =
        productDao.getProductAddedToCart(id).map { it.isNotEmpty() }

    override fun getProductById(id: String): Single<Product> = productDao.getProductById(id)

    override fun removeFromCart(product: Product): Single<Int> = productDao.removeFromCart(product)

    override fun getAddedToCartProducts(): Single<List<Product>> = productDao.getAddedToCartProducts()

    override fun getItemInCartQuantity(): Single<Int> =
        productDao.getItemInCartQuantity().map { it.sum() }
}
