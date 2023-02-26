package com.sun.dummyshop.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.dummyshop.R
import com.sun.dummyshop.databinding.FragmentCheckoutBinding
import com.sun.dummyshop.ui.adapter.BillProductAdapter

class CheckoutFragment : Fragment() {

    private val args: CheckoutFragmentArgs by navArgs()
    private val adapter = BillProductAdapter()
    lateinit var binding: FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil
        .inflate<FragmentCheckoutBinding>(inflater, R.layout.fragment_checkout, container, false)
        .apply { binding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
        setupActions()
    }

    private fun setupData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            recyclerBillProducts.adapter = adapter
            bill = args.bill
        }
    }

    private fun setupActions() {
        binding.buttonBackToHome.setOnClickListener {
            val action = CheckoutFragmentDirections.actionCheckoutToHome()
            findNavController().navigate(action)
        }
    }
}
