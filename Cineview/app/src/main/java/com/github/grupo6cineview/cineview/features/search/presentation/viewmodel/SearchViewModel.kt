package com.github.grupo6cineview.cineview.features.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.grupo6cineview.cineview.extensions.BaseViewModel
import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.datamodel.SearchTrendingResult
import com.github.grupo6cineview.cineview.features.search.domain.SearchUseCase
import kotlinx.coroutines.launch

class SearchViewModel : BaseViewModel() {

    private val _onSuccessSearch = MutableLiveData<List<SearchTrendingResult>>()
    val onSuccessSearch: LiveData<List<SearchTrendingResult>> get() = _onSuccessSearch

    private val searchUseCase = SearchUseCase()

    fun getSearchResult(search: String) {
        viewModelScope.launch {
            searchUseCase.getSearchResult(search).let { response ->
                when (response) {
                    is ResponseApi.Success -> {
                        (response.data as List<*>).let { listResult ->
                            _onSuccessSearch.postValue(listResult.filterIsInstance(SearchTrendingResult::class.java))
                        }
                    }

                    is ResponseApi.Error -> {
                        _onSuccessSearch.postValue(listOf())
                    }
                }
            }
        }
    }

}