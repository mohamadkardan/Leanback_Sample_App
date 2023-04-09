package com.example.leanbackpaging.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.leanbackpaging.domain.usecase.GetPopularTvShowsUseCase
import com.example.leanbackpaging.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase) :
    ViewModel() {

        fun getPopularTvShows(page: Int): kotlinx.coroutines.flow.Flow<PagingData<Movie>> =
        getPopularTvShowsUseCase.executeWithPaging(page).cachedIn(viewModelScope)

}
