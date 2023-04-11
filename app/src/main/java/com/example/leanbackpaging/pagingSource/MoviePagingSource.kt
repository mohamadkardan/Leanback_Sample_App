package com.example.leanbackpaging.pagingSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.leanbackpaging.BuildConfig
import com.example.leanbackpaging.data.api.ApiService
import com.example.leanbackpaging.model.Movie
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingSource @Inject constructor(val apiService: ApiService) :
    PagingSource<Int, Movie>() {

    val genreId: Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            // Start refresh at page 1 if undefined.
            val page = params.key ?: 1

//            val response =
//                apiService.getPopularTVShows(
//                    page = page
//                )

            val response =
                apiService.getMoviesByGenre(
                    page = page,
                    genreId = genreId
                )

            val body = response.body()

            val videos =
                body?.data?.filter { it.id != null }.orEmpty()

            val prevKey = if (page > 1) page - 1 else null

            var nextKey: Int? = null

            nextKey = page + 1

            return LoadResult.Page(
                data = videos,
                prevKey = prevKey, // pass null for Only paging forward.
                nextKey = nextKey
            )

        } catch (e: IOException) {
            Log.d("loadStates", "LoadState.ErrorIO")
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d("loadStates", "LoadState.ErrorHttpException")
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}