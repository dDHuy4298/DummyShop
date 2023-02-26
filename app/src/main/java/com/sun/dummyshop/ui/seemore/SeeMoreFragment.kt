package com.sun.dummyshop.ui.seemore

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.data.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sun.dummyshop.databinding.FragmentSeeMoreBinding
import com.sun.dummyshop.ui.adapter.ProductAdapter
import kotlinx.android.synthetic.main.fragment_see_more.*

class SeeMoreFragment : BaseFragment<FragmentSeeMoreBinding>() {

    override val layoutResource get() = R.layout.fragment_see_more
    override val viewModel by viewModel<SeeMoreViewModel>()

    private val adapter = ProductAdapter(::clickProduct)
    private val args: SeeMoreFragmentArgs by navArgs()

    override fun setupViews() {
    }

    override fun setupData() {
        recyclerSeeMore.adapter = adapter
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            seeMoreVM = viewModel
        }
        viewModel.getProducts(args.type)
    }

    override fun setupActions() {
        binding?.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonCart.setOnClickListener {
                val action = SeeMoreFragmentDirections.actionSeeMoreToCart()
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartItemQuantity()
    }

    private fun clickProduct(product: Product) {
        val action = SeeMoreFragmentDirections.actionSeeMoreToDetail(product)
        findNavController().navigate(action)
    }
}
