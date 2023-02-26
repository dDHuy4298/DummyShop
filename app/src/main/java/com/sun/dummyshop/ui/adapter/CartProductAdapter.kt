package com.sun.dummyshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseAdapter
import com.sun.dummyshop.base.BaseViewHolder
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.databinding.ItemCartProductBinding

class CartProductAdapter(
    private val itemClick: (Product) -> Unit,
    private val deleteButtonClick: (Product) -> Unit,
    private val addButtonClick: (Product) -> Unit,
    private val minusButtonClick: (Product) -> Unit
) : BaseAdapter<Product, CartProductAdapter.CardProductViewHolder>(Product.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardProductViewHolder =
        CardProductViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_cart_product, parent, false
            ),
            itemClick,
            deleteButtonClick,
            addButtonClick,
            minusButtonClick
        )

    class CardProductViewHolder(
        private val itemCartProductBinding: ItemCartProductBinding,
        itemClick: (Product) -> Unit,
        deleteButtonClick: (Product) -> Unit,
        addButtonClick: (Product) -> Unit,
        minusButtonClick: (Product) -> Unit
    ) : BaseViewHolder<Product>(itemCartProductBinding, itemClick) {

        private var product: Product? = null

        init {
            itemCartProductBinding.apply {
                buttonDelete.setOnClickListener {
                    product?.let(deleteButtonClick)
                }
                buttonAdd.setOnClickListener {
                    product?.let(addButtonClick)
                }
                buttonMinus.setOnClickListener {
                    product?.let(minusButtonClick)
                }
            }
        }

        override fun bindData(item: Product) {
            super.bindData(item)
            product = item
            itemCartProductBinding.product = item
        }
    }
}
