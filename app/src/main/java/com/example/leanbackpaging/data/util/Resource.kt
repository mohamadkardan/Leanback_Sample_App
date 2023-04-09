package com.example.leanbackpaging.data.util

import androidx.annotation.NonNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.security.cert.CertificateException


class Resource<T> private constructor(
    val status: Status,
    val data: T? = null,
    val exception: Exception? = null,
    val certificateException: CertificateException? = null,
    val httpException: HttpException? = null,
    val errorBody: ResponseBody? = null
) {
    enum class Status {
        SUCCESS, ERROR, HTTP_ERROR, CERTIFICATE_ERROR, LOADING, ERROR_BODY
    }

    companion object {
        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data)
        }

        fun <T> httpError(httpException: HttpException): Resource<T> {
            return Resource(status = Status.HTTP_ERROR, httpException = httpException)
        }

        fun <T> error(ex: Exception?): Resource<T> {
            return Resource(status = Status.ERROR, exception = ex)
        }

        fun <T> errorBody(responseBody: ResponseBody?): Resource<T> {
            return Resource(status = Status.ERROR_BODY, errorBody = responseBody)
        }

        fun <T> loading(): Resource<T> {
            return Resource(status = Status.LOADING)
        }

        fun <T> certificateError(ex: java.security.cert.CertificateException): Resource<T> {
            return Resource(status = Status.CERTIFICATE_ERROR, certificateException = ex)
        }
    }
}