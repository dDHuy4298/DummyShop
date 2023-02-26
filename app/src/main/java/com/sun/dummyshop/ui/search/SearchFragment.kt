package com.sun.dummyshop.ui.search

import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.sun.dummyshop.R
import com.sun.dummyshop.base.BaseFragment
import com.sun.dummyshop.data.model.Keyword
import com.sun.dummyshop.databinding.FragmentSearchBinding
import com.sun.dummyshop.ui.adapter.KeywordAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override val layoutResource get() = R.layout.fragment_search
    override val viewModel by viewModel<SearchViewModel>()

    private val adapter = KeywordAdapter(::clickKeyword, ::clickDeleteButtonOfKeyword)

    override fun setupViews() {
        binding?.buttonSearch?.isEnabled = false
    }

    override fun setupData() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            searchVM = viewModel
            recyclerSearchHistory.adapter = adapter
        }
    }

    override fun setupActions() {
        binding?.apply {
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonSearch.setOnClickListener {
                search(editTextSearch.text.toString())
            }
            textClearAll.setOnClickListener {
                viewModel.deleteAllKeywords()
            }
            editTextSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(editTextSearch.text.toString())
                }
                false
            }
        }
    }

    private fun search(keyword: String) {
        if (keyword.isNotEmpty() && keyword.isNotBlank()) {
            val action = SearchFragmentDirections.actionSearchToSearchResult(keyword)
            findNavController().navigate(action)
            viewModel.insertKeyword(Keyword(keyword))
            binding?.let {
                editTextSearch.text.clear()
            }
        }
    }

    private fun clickKeyword(keyword: Keyword) {
        search(keyword.keyword)
    }

    private fun clickDeleteButtonOfKeyword(keyword: Keyword) {
        viewModel.deleteKeyword(keyword)
    }
}
