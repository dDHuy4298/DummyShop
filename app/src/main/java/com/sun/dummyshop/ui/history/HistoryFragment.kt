package com.sun.dummyshop.ui.history

import androidx.navigation.fragment.findNavController
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.databinding.FragmentHistoryBinding
import com.sun.dummyshop.ui.adapter.BillAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    override val layoutResource get() = R.layout.fragment_history

    override val viewModel by viewModel<HistoryViewModel>()

    private val adapter = BillAdapter()

    override fun setupViews() {
    }

    override fun setupData() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            historyVM = viewModel
            recyclerHistory.adapter = adapter
        }
    }

    override fun setupActions() {
        buttonCart.setOnClickListener {
            val action = HistoryFragmentDirections.actionHistoryToCart()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartItemQuantity()
    }
}
