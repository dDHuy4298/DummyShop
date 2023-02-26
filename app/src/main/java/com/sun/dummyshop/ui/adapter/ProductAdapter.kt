package com.sun.dummyshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.databinding.DataBindingUtil
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseAdapter
import com.sun.dummyshop.base.BaseViewHolder
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.databinding.ItemProductBinding
import kotlinx.android.synthetic.main.item_product.view.*
import java.util.*

class ProductAdapter(
    private val itemClick: (Product) -> Unit
) : BaseAdapter<Product, ProductAdapter.ProductViewHolder>(Product.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_product, parent, false
            ),
            itemClick
        )

    class ProductViewHolder(
        private val itemProductBinding: ItemProductBinding,
        itemClick: (Product) -> Unit
    ) : BaseViewHolder<Product>(itemProductBinding, itemClick) {

        override fun bindData(item: Product) {
            super.bindData(item)
            itemProductBinding.product = item
        }
    }
}
