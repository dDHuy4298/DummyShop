package com.sun.dummyshop.data.source.remote

import com.sun.dummyshop.data.model.*
import com.sun.dummyshop.utils.Constants
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface Api {
    @GET("products/toprated")
    fun getTopRatingProducts(): Observable<ProductResponse>

    @GET("products/topsales")
    fun getTopSellingProducts(): Observable<ProductResponse>

    @GET("departments")
    fun getCategories(): Observable<CategoryResponse>

    @GET("departments/{department_id}/toprated")
    fun getProductsOfCategory(@Path("department_id") categoryId: String): Observable<ProductResponse>

    @GET("products/{product_id}")
    fun getSimilarProducts(
        @Path("product_id") productId: String,
        @Query("similarities") similar: String = Constants.SIMILARITIES_VALUE
    ): Observable<SimilarProductResponse>

    @GET("products/search")
    fun searchProductsWithFilters(
        @Query("term") keyword: String,
        @Query("price") priceFilter: String,
        @Query("ratings") ratingFilter: String
    ): Observable<ProductResponse>

    @Headers("Content-Type: application/json")
    @POST("actions/checkout")
    fun checkout(@Body cartBody: CartBody): Single<Bill>
}
