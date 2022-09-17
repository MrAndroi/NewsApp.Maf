package com.maf.listing.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.maf.core.utils.openUrl
import com.maf.core.utils.showPrimaryMessage
import com.maf.listing.R
import com.maf.listing.databinding.FragmentNewsListingBinding
import com.maf.listing.domain.models.NewsListingModel
import com.maf.listing.presentation.NewsListingViewModel
import com.maf.listing.presentation.ui.adapter.NewsListingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListingFragment : Fragment() {

    private lateinit var binding: FragmentNewsListingBinding
    private val viewModel: NewsListingViewModel by viewModels()

    private lateinit var newsListingAdapter: NewsListingAdapter
    private lateinit var categoriesAdapter: ArrayAdapter<String>
    private lateinit var countriesAdapter: ArrayAdapter<String>

    private lateinit var categories:Array<String>
    private lateinit var countries:Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsListingAdapter = NewsListingAdapter(::onClickNews)

        categories = resources.getStringArray(R.array.categories)
        countries = resources.getStringArray(R.array.countries)

        categoriesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, categories)
        countriesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, countries)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListingBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpAdapters()
        setUpObservers()
    }

    private fun setUpObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.progressFlow.collect(::handleLoading) }
                launch { viewModel.errorState.collect(::handleError) }
                launch { viewModel.newsList.collect(::handleSuccessNewsList) }

            }
        }
    }

    private fun handleLoading(show: Boolean) {
        binding.showLoading = show
    }

    private fun handleError(error: Throwable?) {
        error?.let {
            binding.showError = true
            showPrimaryMessage(it.message ?: "Unknown Error")
        }
    }

    private fun handleSuccessNewsList(newsList: List<NewsListingModel>?) {
        newsList?.let {
            binding.showError = it.isEmpty()
            newsListingAdapter.submitList(it)
        }
    }

    private fun setUpAdapters() {
        binding.recyclerViewNewsListing.adapter = newsListingAdapter
        binding.autoCompleteTextCategories.setAdapter(categoriesAdapter)
        binding.autoCompleteTextCountries.setAdapter(countriesAdapter)

        binding.autoCompleteTextCategories.setOnItemClickListener { _, _, pos, _ ->
            val selectedCategory = categories[pos]
            viewModel.updateCategory(selectedCategory)
        }

        binding.autoCompleteTextCountries.setOnItemClickListener { _, _, pos, _ ->
            val selectedCountry = countries[pos]
            viewModel.updateCountry(selectedCountry)
        }
    }

    private fun onClickNews(link: String) {
        openUrl(link)
    }

}