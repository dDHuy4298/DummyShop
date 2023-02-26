package com.sun.dummyshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.databinding.DataBindingUtil
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseAdapter
import com.sun.dummyshop.base.BaseViewHolder
import com.sun.dummyshop.data.model.Category
import com.sun.dummyshop.databinding.ItemCategoryBinding
import kotlinx.android.synthetic.main.item_category.view.*
import java.util.*

class CategoryAdapter(
    private val itemClick: (Category) -> Unit
) : BaseAdapter<Category, CategoryAdapter.CategoryViewHolder>(Category.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_category, parent, false
            ),
            itemClick
        )

    class CategoryViewHolder(
        private val itemCategoryBinding: ItemCategoryBinding,
        itemClick: (Category) -> Unit
    ) : BaseViewHolder<Category>(itemCategoryBinding, itemClick) {

        override fun bindData(item: Category) {
            super.bindData(item)
            itemCategoryBinding.category = item
        }
    }
}
