package com.sun.dummyshop.data.repository

import com.sun.dummyshop.data.model.Category
import com.sun.dummyshop.data.source.CategoryDataSource
import io.reactivex.rxjava3.core.Observable

class CategoryRepositoryImpl(
    private val remote: CategoryDataSource
) : CategoryRepository {

    override fun getCategories(): Observable<List<Category>> =
        remote.getCategories().map { it.data }
}
