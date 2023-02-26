package com.sun.dummyshop.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.dummyshop.base.BaseViewModel
import com.sun.dummyshop.data.model.Keyword
import com.sun.dummyshop.data.repository.KeywordRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel(
    private val repository: KeywordRepository
) : BaseViewModel() {

    private val _keywords = MutableLiveData<List<Keyword>>()
    val keywords: LiveData<List<Keyword>>
        get() = _keywords

    private val _isSave = MutableLiveData<Boolean>()

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean>
        get() = _isEmpty

    init {
        getKeywords()
    }

    private fun getKeywords() {
        repository.getKeywords()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _keywords.value = it.reversed()
                _isEmpty.value = it.isEmpty()
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun insertKeyword(keyword: Keyword) {
        repository.insertKeyword(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isSave.value = true
                _isEmpty.value = false
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun deleteKeyword(keyword: Keyword) {
        repository.deleteKeyword(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isSave.value = false
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }

    fun deleteAllKeywords() {
        repository.deleteAllKeywords()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _isEmpty.value = true
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposable)
    }
}
