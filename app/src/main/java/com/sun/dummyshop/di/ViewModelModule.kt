package com.sun.dummyshop.di

import com.sun.dummyshop.ui.cart.CartViewModel
import com.sun.dummyshop.ui.category.CategoryViewModel
import com.sun.dummyshop.ui.detail.DetailViewModel
import com.sun.dummyshop.ui.favorite.FavoriteViewModel
import com.sun.dummyshop.ui.history.HistoryViewModel
import com.sun.dummyshop.ui.home.HomeViewModel
import com.sun.dummyshop.ui.result.SearchResultViewModel
import com.sun.dummyshop.ui.search.SearchViewModel
import com.sun.dummyshop.ui.seemore.SeeMoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchResultViewModel(get()) }
    viewModel { SeeMoreViewModel(get()) }
    viewModel { CartViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { HistoryViewModel(get(), get()) }
}
