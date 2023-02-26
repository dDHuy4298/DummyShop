package com.sun.dummyshop.data.source.local

import com.sun.dummyshop.data.model.Keyword
import com.sun.dummyshop.data.source.KeywordDataSource
import com.sun.dummyshop.data.source.local.dao.KeywordDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class KeywordLocalDataSource(
    private val keywordDao: KeywordDao
) : KeywordDataSource {

    override fun getKeywords(): Observable<List<Keyword>> = keywordDao.getKeywords()

    override fun insertKeyword(keyword: Keyword): Completable = keywordDao.insertKeyword(keyword)

    override fun deleteKeyword(keyword: Keyword): Completable = keywordDao.deleteKeyword(keyword)

    override fun deleteAllKeywords(): Completable = keywordDao.deleteAllKeywords()
}
