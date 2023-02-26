package com.sun.dummyshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseAdapter
import com.sun.dummyshop.base.BaseViewHolder
import com.sun.dummyshop.data.model.BillProduct
import com.sun.dummyshop.databinding.ItemBillProductBinding

class BillProductAdapter :
    BaseAdapter<BillProduct, BillProductAdapter.BillProductViewHolder>(BillProduct.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillProductViewHolder =
        BillProductViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_bill_product, parent, false
            )
        )

    class BillProductViewHolder(
        private val itemBillProductBinding: ItemBillProductBinding
    ) : BaseViewHolder<BillProduct>(itemBillProductBinding, {}) {

        override fun bindData(item: BillProduct) {
            super.bindData(item)
            itemBillProductBinding.product = item
        }
    }
}
