package com.example.leanbackpaging.data.repository

import com.example.leanbackpaging.data.api.ApiService
import com.example.leanbackpaging.data.util.Resource
import com.example.leanbackpaging.domain.repository.AppRepository
import com.example.leanbackpaging.model.PopularTvShowsResponse
import retrofit2.HttpException
import java.io.IOException
import java.security.cert.CertificateException
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val apiService: ApiService) : AppRepository {
    override suspend fun getPopularTvShows(page: Int): Resource<PopularTvShowsResponse> {
        var requestResult: Resource<PopularTvShowsResponse> = Resource.loading()
        try {
            val response = apiService.getPopularTVShows(page = page)
            val successBody = response.body()

            if (successBody != null) {
                if (response.isSuccessful) {
                    requestResult = Resource.success(successBody)
                }
            } else {
                val errorBody = response.errorBody()
                if (errorBody != null) {
                    requestResult = try {
                        Resource.errorBody(errorBody)
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Resource.error(e)
                    }
                }
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            requestResult = Resource.httpError(e)
        } catch (e: Exception) {
            e.printStackTrace()
            requestResult = if (e.cause != null) {
                if (e.cause is CertificateException) {
                    Resource.certificateError(e.cause as CertificateException)
                } else {
                    Resource.error(e)
                }
            } else {
                Resource.error(e)
            }
        }
        return requestResult
    }
}