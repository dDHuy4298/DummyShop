package com.sun.dummyshop.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sun.dummyshop.utils.showToast

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    @get: LayoutRes
    protected abstract val layoutResource: Int
    abstract val viewModel: BaseViewModel
    protected var binding: V? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil
        .inflate<V>(inflater, layoutResource, container, false)
        .apply { binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner, {
            view.context.showToast(it)
        })
        setupViews()
        setupData()
        setupActions()
    }

    protected abstract fun setupViews()

    protected abstract fun setupData()

    protected abstract fun setupActions()

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
