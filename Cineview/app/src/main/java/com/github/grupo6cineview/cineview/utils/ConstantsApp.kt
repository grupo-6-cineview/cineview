package com.github.grupo6cineview.cineview.extensions

class ConstantsApp {

    object Api {
        const val API_TOKEN_KEY = "api_key"
        const val API_TOKEN = "8fd01556c90d7e7501b523235f5ce905"
        const val QUERY_PARAM_LANGUAGE_KEY = "language"
        const val QUERY_PARAM_LANGUAGE_VALUE = "pt-BR"
    }

    object Home {
        const val PATH_TRENDING_DAY = "day"
        const val PATH_TRENDING_WEEK = "week"

        const val PATH_TRENDING_MOVIE = "movie"
        const val PATH_TRENDING_TV = "tv"
    }

    object Paging {
        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 20
    }

}