package com.sun.dummyshop.data.source.remote

import com.sun.dummyshop.data.model.CategoryResponse
import com.sun.dummyshop.data.source.CategoryDataSource
import io.reactivex.rxjava3.core.Observable

class CategoryRemoteDataSource(
    private val api: Api
) : CategoryDataSource {

    override fun getCategories(): Observable<CategoryResponse> = api.getCategories()
}
