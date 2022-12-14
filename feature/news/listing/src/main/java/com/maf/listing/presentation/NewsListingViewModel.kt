package com.maf.listing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maf.listing.domain.models.FilterData
import com.maf.listing.domain.models.NewsListingModel
import com.maf.listing.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    private var selectedCategory = MutableStateFlow(FilterData())

    init {
        selectedCategory.map {
            getNews(it)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, FilterData())

    }

    private fun getNews(filterData: FilterData) {
        viewModelScope.launch {
            _progressFlow.emit(true)
            try {
                _newsList.emit(getNewsUseCase(filterData.country, filterData.category))
            } catch (e: Exception) {
                _errorState.emit(e)
            }
            _progressFlow.emit(false)
        }
    }

    fun updateCategory(newCategory: String) {
        selectedCategory.update {
            it.copy(category = newCategory)
        }
    }

    fun updateCountry(newCountry: String) {
        selectedCategory.update {
            it.copy(country = newCountry)
        }
    }

}