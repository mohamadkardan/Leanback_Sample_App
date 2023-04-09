package com.example.leanbackpaging.presentation.view

import androidx.leanback.paging.PagingDataAdapter
import androidx.leanback.widget.Presenter
import androidx.recyclerview.widget.DiffUtil
import com.example.leanbackpaging.model.Movie


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
