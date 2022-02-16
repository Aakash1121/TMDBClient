package com.example.tmdbclient.data.repository.tvshow.datasource


import com.example.tmdbclient.data.model.tvshow.TvShow

interface TvShowsCacheDataSource {

    suspend fun getTvShowsFromCache():List<TvShow>
    suspend fun saveTvShowsToCache(tvShows:List<TvShow>)

}