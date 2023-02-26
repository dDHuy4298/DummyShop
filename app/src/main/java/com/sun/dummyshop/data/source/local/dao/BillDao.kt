package com.sun.dummyshop.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sun.dummyshop.data.model.Bill
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface BillDao {
    @Query("SELECT * FROM bill")
    fun getBills(): Observable<List<Bill>>

    @Insert
    fun insertBill(bill: Bill): Completable
}
