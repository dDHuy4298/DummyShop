package com.sun.dummyshop.data.repository

import com.sun.dummyshop.data.model.Bill
import com.sun.dummyshop.data.model.CartBody
import com.sun.dummyshop.data.source.BillDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class BillRepositoryImpl(
    private val local: BillDataSource.Local,
    private val remote: BillDataSource.Remote
) : BillRepository {

    override fun getBills(): Observable<List<Bill>> = local.getBills()

    override fun insertBill(bill: Bill): Completable = local.insertBill(bill)

    override fun checkout(cartBody: CartBody): Single<Bill> = remote.checkout(cartBody)
}
