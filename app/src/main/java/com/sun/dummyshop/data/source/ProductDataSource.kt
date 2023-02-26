package com.sun.dummyshop.data.source

import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.data.model.ProductResponse
import com.sun.dummyshop.data.model.SimilarProductResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProductDataSource {
    interface Local {
        fun getFavoriteProducts(): Observable<List<Product>>
        fun insertProduct(product: Product): Completable
        fun deleteFavoriteProduct(product: Product): Completable
        fun updateProduct(product: Product): Completable
        fun isFavoriteProduct(id: String): Single<Boolean>
        fun isAddedToCart(id: String): Single<Boolean>
        fun getProductById(id: String): Single<Product>
        fun removeFromCart(product: Product): Single<Int>
        fun getAddedToCartProducts(): Single<List<Product>>
        fun getItemInCartQuantity(): Single<Int>
    }

    interface Remote {
        fun getTopRatingProducts(): Observable<ProductResponse>
        fun getTopSellingProducts(): Observable<ProductResponse>
        fun getProductsOfCategory(id: String): Observable<ProductResponse>
        fun getSimilarProducts(id: String): Observable<SimilarProductResponse>
        fun searchProductsWithFilters(
            keyword: String,
            priceFilter: String,
            ratingFilter: String,
        ): Observable<ProductResponse>
    }
}
