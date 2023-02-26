package com.sun.dummyshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseAdapter
import com.sun.dummyshop.base.BaseViewHolder
import com.sun.dummyshop.data.model.Bill
import com.sun.dummyshop.databinding.ItemHistoryBinding

class BillAdapter : BaseAdapter<Bill, BillAdapter.BillViewHolder>(Bill.diffUtil) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder =
        BillViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_history, parent, false
            ),
            viewPool
        )

    class BillViewHolder(
        private val itemBillBinding: ItemHistoryBinding,
        private val viewPool: RecyclerView.RecycledViewPool
    ) : BaseViewHolder<Bill>(itemBillBinding, {}) {

        private val adapter = BillProductAdapter()

        override fun bindData(item: Bill) {
            super.bindData(item)
            itemBillBinding.apply {
                bill = item
                recyclerBoughtItems.setRecycledViewPool(viewPool)
                recyclerBoughtItems.adapter = adapter
            }
        }
    }
}
