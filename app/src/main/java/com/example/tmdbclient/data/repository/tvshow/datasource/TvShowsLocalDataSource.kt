package com.example.tmdbclient.data.repository.tvshow.datasource


import com.example.tmdbclient.data.model.tvshow.TvShow
import retrofit2.Response

interface TvShowsLocalDataSource {

    suspend fun getTvShowsFromDB(): List<TvShow>
    suspend fun saveTvShowsToDB(tvShows:List<TvShow>)
    suspend fun clearAll()

}