package com.sun.dummyshop.ui.adapter.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.dummyshop.base.UpdateData

@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, data: List<T>?) {
    if (recyclerView.adapter is UpdateData<*>) {
        (recyclerView.adapter as UpdateData<T>).updateData(data)
    }
}
