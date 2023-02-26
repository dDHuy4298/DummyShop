package com.sun.dummyshop.data.repository

import com.sun.dummyshop.data.model.Keyword
import com.sun.dummyshop.data.source.KeywordDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class KeywordRepositoryImpl(
    private val local: KeywordDataSource
) : KeywordRepository {

    override fun getKeywords(): Observable<List<Keyword>> = local.getKeywords()

    override fun insertKeyword(keyword: Keyword): Completable = local.insertKeyword(keyword)

    override fun deleteKeyword(keyword: Keyword): Completable = local.deleteKeyword(keyword)

    override fun deleteAllKeywords(): Completable = local.deleteAllKeywords()
}
