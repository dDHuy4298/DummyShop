package com.sun.dummyshop.data.source.local.dao

import androidx.room.*
import com.sun.dummyshop.data.model.Keyword
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface KeywordDao {
    @Query("SELECT * FROM keyword")
    fun getKeywords(): Observable<List<Keyword>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertKeyword(keyword: Keyword): Completable

    @Delete
    fun deleteKeyword(keyword: Keyword): Completable

    @Query("DELETE FROM keyword")
    fun deleteAllKeywords(): Completable
}
