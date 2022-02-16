package com.example.tmdbclient.data.repository.tvshow.datasourceimpl

import com.example.tmdbclient.data.db.TvShowDAO
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowsLocalDataSourceImpl(private val tvShowDao: TvShowDAO): TvShowsLocalDataSource {
    override suspend fun getTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getAllTvShows()
    }

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvShows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }

}