package com.sun.dummyshop.ui.home

import androidx.navigation.fragment.findNavController
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.data.model.Category
import com.sun.dummyshop.data.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sun.dummyshop.databinding.FragmentHomeBinding
import com.sun.dummyshop.ui.adapter.CategoryAdapter
import com.sun.dummyshop.ui.adapter.ProductAdapter
import com.sun.dummyshop.utils.Constants

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutResource get() = R.layout.fragment_home
    override val viewModel by viewModel<HomeViewModel>()

    private val categoryAdapter = CategoryAdapter(::clickCategory)
    private val topRatingAdapter = ProductAdapter(::clickProduct)
    private val topSellingAdapter = ProductAdapter(::clickProduct)

    override fun setupViews() {
    }

    override fun setupData() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            homeVM = viewModel
            recyclerCategories.adapter = categoryAdapter
            recyclerTopSelling.adapter = topSellingAdapter
            recyclerTopRating.adapter = topRatingAdapter
        }
    }

    override fun setupActions() {
        binding?.apply {
            textSearch.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToSearch()
                findNavController().navigate(action)
            }
            textTopRatingSeeMore.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToSeeMore(Constants.BUNDLE_TOP_RATING)
                findNavController().navigate(action)
            }
            textTopSellingSeeMore.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToSeeMore(Constants.BUNDLE_TOP_SELLING)
                findNavController().navigate(action)
            }
            buttonCart.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToCart()
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartItemQuantity()
    }

    private fun clickCategory(category: Category) {
        val action = HomeFragmentDirections.actionHomeToCategory(category)
        findNavController().navigate(action)
    }

    private fun clickProduct(product: Product) {
        val action = HomeFragmentDirections.actionHomeToDetail(product)
        findNavController().navigate(action)
    }
}
