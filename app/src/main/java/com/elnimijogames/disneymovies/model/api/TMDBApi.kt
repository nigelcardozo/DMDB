package com.elnimijogames.disneymovies.model.api

import com.elnimijogames.disneymovies.model.responses.TMDBDiscoverMoviesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.themoviedb.org/3/
//discover/movie?
//api_key=1a2bf3735ca18e4982eef9caad46931d&
//with_companies=3166%7C6125&
//with_genres=16

class TMDBWebService {
    private lateinit var api: TMDBApi
    private val BASE_URL: String = "https://api.themoviedb.org/3/"

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        api = retrofit.create(TMDBApi::class.java)
    }

    suspend fun getDiscoverMovies(): TMDBDiscoverMoviesResponse {
        //ToDo: These should be passed in from a higher layer
        return api.getDiscoverMovies(
            API_KEY,
            (VAL_COMPANIES_DISNEY_ANIMATION + PIPE_SEPARATOR + VAL_COMPANIES_DISNEY_PRODUCTION),
            VAL_GENRES_ANIMATED
        )
    }

    interface TMDBApi {
        @GET("discover/movie")
        suspend fun getDiscoverMovies(
            @Query(PARAM_QUERY_API_KEY) apiKey: String,
            @Query(PARAM_QUERY_KEY_WITH_COMPANIES) withCompany: String,
            @Query(PARAM_QUERY_KEY_WITH_GENRES) withGenres: String

        ): TMDBDiscoverMoviesResponse
    }

    companion object {
        private const val PIPE_SEPARATOR: String = "|"
        private const val API_KEY: String = "1a2bf3735ca18e4982eef9caad46931d"
        private const val SEARCH_TYPE: String = "discover"
        private const val PARAM_QUERY_API_KEY: String = "api_key"
        private const val PARAM_QUERY_KEY_WITH_COMPANIES: String = "with_companies"
        private const val PARAM_QUERY_KEY_WITH_GENRES: String = "with_genres"
        private const val VAL_COMPANIES_DISNEY_ANIMATION: String = "6125"
        private const val VAL_COMPANIES_DISNEY_PRODUCTION: String = "3166"
        private const val VAL_GENRES_ANIMATED: String = "16"

    }
}

