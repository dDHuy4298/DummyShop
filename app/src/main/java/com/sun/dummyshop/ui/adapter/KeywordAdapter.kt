package com.sun.dummyshop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseAdapter
import com.sun.dummyshop.base.BaseViewHolder
import com.sun.dummyshop.data.model.Keyword
import com.sun.dummyshop.databinding.ItemKeywordBinding

class KeywordAdapter(
    private val itemClick: (Keyword) -> Unit,
    private val deleteButtonClick: (Keyword) -> Unit
) : BaseAdapter<Keyword, KeywordAdapter.KeywordViewHolder>(Keyword.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder =
        KeywordViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_keyword, parent, false
            ),
            itemClick,
            deleteButtonClick
        )

    class KeywordViewHolder(
        private val itemKeywordBinding: ItemKeywordBinding,
        itemClick: (Keyword) -> Unit,
        deleteButtonClick: (Keyword) -> Unit
    ) : BaseViewHolder<Keyword>(itemKeywordBinding, itemClick) {

        private var keyword: Keyword? = null

        init {
            itemKeywordBinding.buttonDelete.setOnClickListener {
                keyword?.let(deleteButtonClick)
            }
        }

        override fun bindData(item: Keyword) {
            super.bindData(item)
            keyword = item
            itemKeywordBinding.keyword = item
        }
    }
}
