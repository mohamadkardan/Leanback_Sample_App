package com.example.leanbackpaging.domain.usecase

import android.app.appsearch.SearchResult
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.leanbackpaging.data.api.ApiService
import com.example.leanbackpaging.data.util.Resource
import com.example.leanbackpaging.domain.repository.AppRepository
import com.example.leanbackpaging.model.Genre
import com.example.leanbackpaging.model.Movie
import com.example.leanbackpaging.model.PopularTvShowsResponse
import com.example.leanbackpaging.pagingSource.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetGenresUseCase @Inject constructor(private val appRepository: AppRepository) {

    suspend fun execute(): Resource<List<Genre>> = appRepository.getGenres()

}