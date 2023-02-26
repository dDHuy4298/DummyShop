package com.sun.dummyshop.data.source

import com.sun.dummyshop.data.model.Keyword
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface KeywordDataSource {
    fun getKeywords(): Observable<List<Keyword>>
    fun insertKeyword(keyword: Keyword): Completable
    fun deleteKeyword(keyword: Keyword): Completable
    fun deleteAllKeywords(): Completable
}
