package com.sun.dummyshop.data.source

import com.sun.dummyshop.data.model.Bill
import com.sun.dummyshop.data.model.CartBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface BillDataSource {
    interface Local {
        fun getBills(): Observable<List<Bill>>
        fun insertBill(bill: Bill): Completable
    }

    interface Remote {
        fun checkout(cartBody: CartBody): Single<Bill>
    }
}
