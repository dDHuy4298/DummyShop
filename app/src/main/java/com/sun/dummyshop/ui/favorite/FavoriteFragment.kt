package com.sun.dummyshop.ui.favorite

import androidx.navigation.fragment.findNavController
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.databinding.FragmentFavoriteBinding
import com.sun.dummyshop.ui.adapter.ProductAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    override val layoutResource get() = R.layout.fragment_favorite
    override val viewModel by viewModel<FavoriteViewModel>()

    private val adapter = ProductAdapter(::clickProduct)

    override fun setupViews() {
    }

    override fun setupData() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            favoriteVM = viewModel
            recyclerFavorite.adapter = adapter
        }
    }

    override fun setupActions() {
        binding?.buttonCart?.setOnClickListener {
            val action = FavoriteFragmentDirections.actionFavoriteToCart()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartItemQuantity()
    }

    private fun clickProduct(product: Product) {
        val action = FavoriteFragmentDirections.actionFavoriteToDetail(product)
        findNavController().navigate(action)
    }
}
