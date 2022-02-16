package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1

interface TMDBService {

    @GET("movie/popular")
    suspend fun getMovieList(@Query("api_key") apiKey: String): Response<MovieList>

    @GET("tv/popular")
    suspend fun getTvShowList(@Query("api_key") apiKey: String): Response<TvShowList>

    @GET("person/popular")
    suspend fun getArtistList(@Query("api_key") apiKey: String): Response<ArtistList>

}

