package com.maf.listing.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maf.listing.databinding.RowNewsListingBinding
import com.maf.listing.domain.models.NewsListingModel

class NewsListingAdapter(private val onClick: (link: String) -> Unit) :
    ListAdapter<NewsListingModel, NewsListingAdapter.ViewHolder>(NewsDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowNewsListingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RowNewsListingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: NewsListingModel) {
            binding.news = model
            itemView.setOnClickListener { onClick(model.link) }
            binding.executePendingBindings()
        }
    }

    private object NewsDiffer : DiffUtil.ItemCallback<NewsListingModel>() {

        override fun areItemsTheSame(
            oldItem: NewsListingModel,
            newItem: NewsListingModel
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: NewsListingModel,
            newItem: NewsListingModel
        ): Boolean {
            return oldItem == newItem
        }

    }

}