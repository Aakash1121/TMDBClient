package com.example.tmdbclient.data.repository.tvshow.datasourceimpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.tvshow.TvShowList
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsRemoteDataSource
import retrofit2.Response

class TvShowsRemoteDataSourceImpl(private val tmdbService: TMDBService, private val apiKey: String) :
    TvShowsRemoteDataSource {

    override suspend fun getTvShows(): Response<TvShowList> =tmdbService.getTvShowList(apiKey)

}