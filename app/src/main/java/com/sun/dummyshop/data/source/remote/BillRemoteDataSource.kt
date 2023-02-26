package com.sun.dummyshop.data.source.remote

import com.sun.dummyshop.data.model.Bill
import com.sun.dummyshop.data.model.CartBody
import com.sun.dummyshop.data.source.BillDataSource
import io.reactivex.rxjava3.core.Single

class BillRemoteDataSource(
    private val api: Api
) : BillDataSource.Remote {

    override fun checkout(cartBody: CartBody): Single<Bill> = api.checkout(cartBody)
}
