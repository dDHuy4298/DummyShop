package com.sun.dummyshop.ui.detail

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.data.model.Product
import com.sun.dummyshop.databinding.FragmentDetailBinding
import com.sun.dummyshop.ui.adapter.ProductAdapter
import com.sun.dummyshop.utils.CircleAnimationUtil
import com.sun.dummyshop.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override val layoutResource get() = R.layout.fragment_detail
    override val viewModel by viewModel<DetailViewModel>()

    private val adapter = ProductAdapter(::clickProduct)
    private val args: DetailFragmentArgs by navArgs()

    override fun setupViews() {
    }

    override fun setupData() {
        getProductRelateInformation(args.product)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            detailVM = viewModel
            recyclerSimilar.adapter = adapter
        }
    }

    override fun setupActions() {
        binding?.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonAddToCart.setOnClickListener {
                viewModel.addToCart()
                Toast.makeText(context, R.string.text_added_to_cart, Toast.LENGTH_SHORT).show()
                CircleAnimationUtil().attachActivity(activity).setTargetView(imageProductImageDuplicate)
                    .setDestView(buttonCart).startAnimation()
            }
            buttonFavorite.setOnClickListener {
                viewModel.setFavorite()
            }
            textSimilarSeeMore.setOnClickListener {
                viewModel.currentProduct.value?.let { product ->
                    val action = DetailFragmentDirections.actionDetailToSeeMore(product.id)
                    findNavController().navigate(action)
                }
            }
            buttonCart.setOnClickListener {
                val action = DetailFragmentDirections.actionDetailToCart()
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartItemQuantity()
    }

    private fun clickProduct(product: Product) {
        getProductRelateInformation(product)
        binding?.nestedScroll?.smoothScrollTo(Constants.TOP_POSITION, Constants.TOP_POSITION)
    }

    private fun getProductRelateInformation(product: Product) {
        viewModel.apply {
            currentProduct.value = product
            getSimilar()
            getProductById()
            checkFavorite()
        }
    }
}
