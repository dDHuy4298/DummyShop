package com.sun.dummyshop.ui.cart

import androidx.navigation.fragment.findNavController
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.databinding.FragmentCartBinding
import com.sun.dummyshop.ui.adapter.CartProductAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : BaseFragment<FragmentCartBinding>() {

    override val layoutResource get() = R.layout.fragment_cart
    override val viewModel by viewModel<CartViewModel>()

    private val adapter = CartProductAdapter(
        ::clickProduct,
        ::clickDeleteButton,
        ::clickAddButton,
        ::clickMinusButton
    )

    override fun setupViews() {
    }

    override fun setupData() {
        recyclerCartProducts.adapter = adapter
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            cartVM = viewModel
        }
        viewModel.getProductsAddedToCart()
    }

    override fun setupActions() {
        binding?.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonCheckout.setOnClickListener {
                viewModel.checkout()
                viewModel.bill.observe(viewLifecycleOwner, {
                    val action = CartFragmentDirections.actionCartToCheckout(it)
                    findNavController().navigate(action)
                })
            }
        }
    }

    private fun clickProduct(product: Product) {
        val action = CartFragmentDirections.actionCartToDetail(product)
        findNavController().navigate(action)
    }

    private fun clickDeleteButton(product: Product) {
        viewModel.removeFromCart(product)
    }

    private fun clickAddButton(product: Product) {
        viewModel.addQuantity(product)
    }

    private fun clickMinusButton(product: Product) {
        viewModel.minusQuantity(product)
    }
}
