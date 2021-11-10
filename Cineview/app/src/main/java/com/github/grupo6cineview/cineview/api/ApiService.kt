package com.github.grupo6cineview.cineview.api


import com.github.grupo6cineview.cineview.BuildConfig
import com.github.grupo6cineview.cineview.utils.ConstantsApp
import com.github.grupo6cineview.cineview.utils.ConstantsApp.Api.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    val tmdbApi: TMDBApi = getTMDBApiClient().create(TMDBApi::class.java)

    fun getTMDBApiClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getInterceptorClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun getInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter(ConstantsApp.Api.API_TOKEN_KEY, ConstantsApp.Api.API_TOKEN)
                    .addQueryParameter(ConstantsApp.Api.QUERY_PARAM_LANGUAGE_KEY, ConstantsApp.Api.QUERY_PARAM_LANGUAGE_VALUE)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
        return interceptor.build()
    }
}