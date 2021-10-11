package com.github.grupo6cineview.cineview.extensions

class ConstantsApp {

    object Api {
        const val BASE_URL = "https://api.themoviedb.org/3/"

        const val API_TOKEN_KEY = "api_key"
        const val API_TOKEN = "8fd01556c90d7e7501b523235f5ce905"

        const val QUERY_PARAM_LANGUAGE_KEY = "language"
        const val QUERY_PARAM_LANGUAGE_VALUE = "pt-BR"

        const val PATH_TRENDING_WEEK = "week"

        const val PATH_TRENDING_MOVIE = "movie"
    }

    object Paging {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
        const val MAX_SIZE = 100
    }

    object Detail {
        const val BUNDLE_KEY_ID = "MOVIE_ID"
        const val TAG_SHOW_DETAIL_FRAGMENT = "DETAIL_FRAGMENT"
    }
}