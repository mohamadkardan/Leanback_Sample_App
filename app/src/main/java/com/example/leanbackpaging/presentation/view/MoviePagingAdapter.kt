package com.example.leanbackpaging.presentation.view

import android.annotation.SuppressLint
import androidx.leanback.paging.PagingDataAdapter
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.DiffCallback
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import androidx.paging.AsyncPagedListDiffer
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagedList
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.leanbackpaging.model.Movie
import kotlinx.coroutines.Dispatchers


fun getMoviePagingAdapter(cardPresenter: Presenter): PagingDataAdapter<Movie> =
    PagingDataAdapter(cardPresenter,
        object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        })

//


