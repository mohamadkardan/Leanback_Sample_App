package com.example.leanbackpaging.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.leanbackpaging.data.util.Resource
import com.example.leanbackpaging.domain.usecase.GetGenresUseCase
import com.example.leanbackpaging.domain.usecase.GetPopularTvShowsUseCase
import com.example.leanbackpaging.model.Genre
import com.example.leanbackpaging.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    fun getPopularTvShowsWithPaging(page: Int): Flow<PagingData<Movie>> =
        getPopularTvShowsUseCase.executeWithPaging(page).cachedIn(viewModelScope)

    fun getGenresList() = liveData {
        emit(getGenresUseCase.execute())
    }

}
