package com.sun.dummyshop.data.repository

import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.source.ProductDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ProductRepositoryImpl(
    private val local: ProductDataSource.Local,
    private val remote: ProductDataSource.Remote
) : ProductRepository {

    override fun getFavoriteProducts(): Observable<List<Product>> =
        local.getFavoriteProducts()

    override fun insertProduct(product: Product): Completable =
        local.insertProduct(product)

    override fun deleteFavoriteProduct(product: Product): Completable =
        local.deleteFavoriteProduct(product)

    override fun updateProduct(product: Product): Completable =
        local.updateProduct(product)

    override fun isFavoriteProduct(id: String): Single<Boolean> =
        local.isFavoriteProduct(id)

    override fun isAddedToCart(id: String): Single<Boolean> =
        local.isAddedToCart(id)

    override fun getProductById(id: String): Single<Product> = local.getProductById(id)

    override fun removeFromCart(product: Product): Single<Int> = local.removeFromCart(product)

    override fun getAddedToCartProducts(): Single<List<Product>> = local.getAddedToCartProducts()

    override fun getItemInCartQuantity(): Single<Int> = local.getItemInCartQuantity()

    override fun getTopRatingProducts(): Observable<List<Product>> =
        remote.getTopRatingProducts().map { it.data }

    override fun getTopSellingProducts(): Observable<List<Product>> =
        remote.getTopSellingProducts().map { it.data }

    override fun getProductsOfCategory(id: String): Observable<List<Product>> =
        remote.getProductsOfCategory(id).map { it.data }

    override fun getSimilarProducts(id: String): Observable<List<Product>> =
        remote.getSimilarProducts(id).map { it.data.items }

    override fun searchProductsWithFilters(
        keyword: String,
        priceFilter: String,
        ratingFilter: String
    ): Observable<List<Product>> =
        remote.searchProductsWithFilters(keyword, priceFilter, ratingFilter).map { it.data }
}
