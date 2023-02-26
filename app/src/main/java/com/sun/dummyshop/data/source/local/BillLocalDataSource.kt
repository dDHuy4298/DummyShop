package com.sun.dummyshop.data.source.local

import com.sun.dummyshop.data.model.Bill
import com.sun.dummyshop.data.source.BillDataSource
import com.sun.dummyshop.data.source.local.dao.BillDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

class BillLocalDataSource(
    private val billDao: BillDao
) : BillDataSource.Local {

    override fun getBills(): Observable<List<Bill>> = billDao.getBills()

    override fun insertBill(bill: Bill): Completable = billDao.insertBill(bill)
}
