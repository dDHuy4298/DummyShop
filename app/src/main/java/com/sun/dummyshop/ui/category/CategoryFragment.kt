package com.sun.dummyshop.ui.category

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.databinding.FragmentCategoryBinding
import com.sun.dummyshop.ui.adapter.ProductAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : BaseFragment<FragmentCategoryBinding>() {

    override val layoutResource get() = R.layout.fragment_category
    override val viewModel by viewModel<CategoryViewModel>()

    private val adapter = ProductAdapter(::clickProduct)
    private val args: CategoryFragmentArgs by navArgs()

    override fun setupViews() {
        binding?.textCategoryTitle?.text = args.category.name
    }

    override fun setupData() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            categoryVM = viewModel
            recyclerProductsOfCategory.adapter = adapter
        }
        viewModel.getProductsOfCategory(args.category.id)
    }

    override fun setupActions() {
        binding?.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            textSearch.setOnClickListener {
                val action = CategoryFragmentDirections.actionCategoryToSearch()
                findNavController().navigate(action)
            }
            buttonCart.setOnClickListener {
                val action = CategoryFragmentDirections.actionCategoryToCart()
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartItemQuantity()
    }

    private fun clickProduct(product: Product) {
        val action = CategoryFragmentDirections.actionCategoryToDetail(product)
        findNavController().navigate(action)
    }
}
