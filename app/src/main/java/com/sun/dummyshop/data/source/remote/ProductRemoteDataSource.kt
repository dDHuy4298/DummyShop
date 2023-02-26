package com.sun.dummyshop.data.source.remote

import com.sun.dummyshop.data.model.ProductResponse
import com.sun.dummyshop.data.model.SimilarProductResponse
import com.sun.dummyshop.data.source.ProductDataSource
import io.reactivex.rxjava3.core.Observable

class ProductRemoteDataSource(
    private val api: Api
) : ProductDataSource.Remote {

    override fun getTopRatingProducts(): Observable<ProductResponse> = api.getTopRatingProducts()

    override fun getTopSellingProducts(): Observable<ProductResponse> = api.getTopSellingProducts()

    override fun getProductsOfCategory(id: String): Observable<ProductResponse> =
        api.getProductsOfCategory(id)

    override fun getSimilarProducts(id: String): Observable<SimilarProductResponse> =
        api.getSimilarProducts(id)

    override fun searchProductsWithFilters(
        keyword: String,
        priceFilter: String,
        ratingFilter: String
    ): Observable<ProductResponse> =
        api.searchProductsWithFilters(keyword, priceFilter, ratingFilter)
}
