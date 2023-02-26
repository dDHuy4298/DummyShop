package com.sun.dummyshop.ui.result

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.slider.RangeSlider
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.data.model.Product
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sun.dummyshop.databinding.FragmentSearchResultBinding
import com.sun.dummyshop.ui.adapter.ProductAdapter
import com.sun.dummyshop.utils.Constants
import kotlinx.android.synthetic.main.fragment_search_result.*

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>() {

    override val layoutResource get() = R.layout.fragment_search_result
    override val viewModel by viewModel<SearchResultViewModel>()

    private val adapter = ProductAdapter(::clickProduct)
    private val args: SearchResultFragmentArgs by navArgs()

    override fun setupViews() {
    }

    override fun setupData() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            resultVM = viewModel
            recyclerSearchResult.adapter = adapter
            textResultTitle.text =
                String.format(getString(R.string.text_search_result), args.keyword)
        }
        setPrice()
        searchWithFilters(
            args.keyword,
            ratingFilter.rating.toString()
        )
    }

    override fun setupActions() {
        binding?.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonFilter.setOnClickListener {
                if (groupFilter.visibility == View.GONE) {
                    groupFilter.visibility = View.VISIBLE
                } else {
                    groupFilter.visibility = View.GONE
                }
            }
            sliderPriceFilter.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: RangeSlider) {
                }

                override fun onStopTrackingTouch(slider: RangeSlider) {
                    setPrice()
                }
            })
            buttonApply.setOnClickListener {
                searchWithFilters(
                    args.keyword,
                    ratingFilter.rating.toString()
                )
                groupFilter.visibility = View.GONE
            }
        }
    }

    private fun clickProduct(product: Product) {
        val action = SearchResultFragmentDirections.actionSearchResultToDetail(product)
        findNavController().navigate(action)
    }

    private fun searchWithFilters(
        keyword: String,
        minRating: String
    ) {
        val minPrice = sliderPriceFilter.values[0].toInt().toString()
        val maxPrice = sliderPriceFilter.values[1].toInt().toString()
        val priceFilter =
            Constants.FILTER_GREATER_EQUAL + minPrice + Constants.FILTER_LESS_EQUAL + maxPrice
        val ratingFilter =
            Constants.FILTER_GREATER_EQUAL + minRating + Constants.FILTER_LESS_EQUAL + Constants.FILTER_MAX_RATING
        viewModel.searchProductsWithFilters(keyword, priceFilter, ratingFilter)
    }

    private fun setPrice() {
        viewModel.apply {
            minPrice.value = sliderPriceFilter.values[0].toInt().toString()
            maxPrice.value = sliderPriceFilter.values[1].toInt().toString()
        }
    }
}
