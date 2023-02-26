package com.sun.dummyshop.data.source

import com.sun.dummyshop.data.model.CategoryResponse
import io.reactivex.rxjava3.core.Observable

interface CategoryDataSource {
    fun getCategories(): Observable<CategoryResponse>
}
