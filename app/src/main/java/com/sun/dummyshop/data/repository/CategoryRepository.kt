package com.sun.dummyshop.data.repository

import com.sun.dummyshop.data.model.Category
import io.reactivex.rxjava3.core.Observable

interface CategoryRepository {
    fun getCategories(): Observable<List<Category>>
}
