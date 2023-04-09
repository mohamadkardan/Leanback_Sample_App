package com.example.leanbackpaging.data.api

import com.example.leanbackpaging.BuildConfig
import com.example.leanbackpaging.model.Movie
import com.example.leanbackpaging.model.PopularTvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/tv/popular/")
    suspend fun getPopularTVShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY_1,
        @Query("page") page: Int
    ): Response<PopularTvShowsResponse>
}