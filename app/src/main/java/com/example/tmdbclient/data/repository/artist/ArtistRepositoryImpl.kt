package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtist = getArtistsFromApi()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfArtist)
        artistCacheDataSource.saveArtistsToCache(newListOfArtist)
        return newListOfArtist

    }

    suspend fun getArtistsFromApi(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()
            if (body != null) {
                artistList = body.artists
            }
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        return artistList
    }

    suspend fun getArtistsFromDB(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistsFromDB()
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if (artistList.size > 0) {
            return artistList
        } else {
            artistList = getArtistsFromApi()
            artistLocalDataSource.saveArtistsToDB(artistList)
        }
        return artistList
    }

    suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistsFromCache()
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if (artistList.size > 0) {
            return artistList
        } else {
            artistList = getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }

}