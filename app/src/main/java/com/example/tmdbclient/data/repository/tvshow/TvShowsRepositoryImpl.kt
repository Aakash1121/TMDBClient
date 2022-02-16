package com.example.tmdbclient.data.repository.tvshow

import android.util.Log
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowsRepository

class TvShowsRepositoryImpl(
    private val tvShowsRemoteDataSource: TvShowsRemoteDataSource,
    private val tvShowsLocalDataSource: TvShowsLocalDataSource,
    private val tvShowsCacheDataSource: TvShowsCacheDataSource
) : TvShowsRepository {

    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val newListOfTvShows = getTvShowsFromAPI()
        tvShowsLocalDataSource.clearAll()
        tvShowsLocalDataSource.saveTvShowsToDB(newListOfTvShows)
        tvShowsCacheDataSource.saveTvShowsToCache(newListOfTvShows)
        return newListOfTvShows
    }

    suspend fun getTvShowsFromAPI(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            val response = tvShowsRemoteDataSource.getTvShows()
            val body = response.body()
            if (body != null) {
                tvShowList = body.tvShows
            }
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        return tvShowList
    }

    suspend fun getTvShowsFromDB(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList=tvShowsLocalDataSource.getTvShowsFromDB()

        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if(tvShowList.size>0){
            return tvShowList
        }else{
            tvShowList=getTvShowsFromAPI()
            tvShowsLocalDataSource.saveTvShowsToDB(tvShowList)
        }
        return tvShowList
    }


    suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList=tvShowsCacheDataSource.getTvShowsFromCache()

        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if(tvShowList.size>0){
            return tvShowList
        }else{
            tvShowList=getTvShowsFromDB()
            tvShowsCacheDataSource.saveTvShowsToCache(tvShowList)
        }
        return tvShowList
    }
}