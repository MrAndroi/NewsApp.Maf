package com.maf.listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maf.listing.domain.models.NewsListingModel
import com.maf.listing.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListingViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _progressFlow = MutableStateFlow(false)
    val progressFlow: StateFlow<Boolean> = _progressFlow

    private val _errorState = MutableSharedFlow<Throwable?>()
    val errorState = _errorState.asSharedFlow()

    private val _newsList = MutableStateFlow<List<NewsListingModel>?>(null)
    val newsList: StateFlow<List<NewsListingModel>?> = _newsList

    private var selectedCategory = "sports"
    private var selectedCountry = "us"

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            _progressFlow.emit(true)
            try {
                _newsList.emit(getNewsUseCase(selectedCountry, selectedCategory))
            } catch (e: Exception) {
                _errorState.emit(e)
            }
            _progressFlow.emit(false)
        }
    }

    fun updateCategory(newCategory: String) {
        selectedCategory = newCategory
        getNews()
    }

    fun updateCountry(newCountry: String) {
        selectedCountry = newCountry
        getNews()
    }

}