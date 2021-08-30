package com.github.grupo6cineview.cineview.features.home.model

import com.github.grupo6cineview.cineview.datamodel.NowPlaying
import com.github.grupo6cineview.cineview.extensions.ResponseApi
import com.github.grupo6cineview.cineview.extensions.getFullImageUrl
import com.github.grupo6cineview.cineview.features.home.repository.HomeRepository


class HomeUseCase {

    private val homeRepository = HomeRepository()

    suspend fun getNowPlayingMovies(): ResponseApi {
        return when (val responseApi = homeRepository.getNowPlayingMovies()) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? NowPlaying
                val result = data?.results?.map {
                    it.backdrop_path = it.backdrop_path.getFullImageUrl()
                    it.poster_path = it.poster_path.getFullImageUrl()
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
            else -> responseApi
        }
    }

}