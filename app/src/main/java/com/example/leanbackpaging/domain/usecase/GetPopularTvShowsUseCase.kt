package com.example.leanbackpaging.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.leanbackpaging.data.api.ApiService
import com.example.leanbackpaging.data.util.Resource
import com.example.leanbackpaging.domain.repository.AppRepository
import com.example.leanbackpaging.model.Movie
import com.example.leanbackpaging.model.PopularTvShowsResponse
import com.example.leanbackpaging.pagingSource.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTvShowsUseCase @Inject constructor(private val apiService: ApiService) {

    private val moviePagingSource by lazy {
        MoviePagingSource(apiService)
    }

    fun executeWithPaging(page: Int): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(page, enablePlaceholders = true, maxSize = 10),
            pagingSourceFactory = {
                moviePagingSource
            }).flow

}