package com.example.leanbackpaging.data.api

import com.example.leanbackpaging.BuildConfig
import com.example.leanbackpaging.model.Genre
import com.example.leanbackpaging.model.GetMoviesByGenreResponse
import com.example.leanbackpaging.model.Movie
import com.example.leanbackpaging.model.PopularTvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movies")
    suspend fun getPopularTVShows(
        @Query("page") page: Int
    ): Response<PopularTvShowsResponse>

    @GET("genres")
    suspend fun getGenres(
    ): Response<List<Genre>>

    @GET("genres/{genre_id}/movies")
    suspend fun getMoviesByGenre(
        @Path("genre_id") genreId : Int,
        @Query("page") page : Int
    ): Response<GetMoviesByGenreResponse>
}