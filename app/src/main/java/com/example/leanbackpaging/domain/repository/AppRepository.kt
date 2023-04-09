package com.example.leanbackpaging.domain.repository

import com.example.leanbackpaging.data.util.Resource
import com.example.leanbackpaging.model.PopularTvShowsResponse

interface AppRepository {
    suspend fun getPopularTvShows(page : Int) : Resource<PopularTvShowsResponse>
}