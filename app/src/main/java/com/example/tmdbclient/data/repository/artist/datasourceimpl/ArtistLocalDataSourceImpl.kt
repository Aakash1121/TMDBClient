package com.example.tmdbclient.data.repository.artist.datasourceimpl

import com.example.tmdbclient.data.db.ArtistDAO
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistsDAO: ArtistDAO):ArtistLocalDataSource {
    override suspend fun getArtistsFromDB(): List<Artist> {
        return artistsDAO.getAllArtists()
    }

    override suspend fun saveArtistsToDB(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistsDAO.saveAllArtists(artists)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistsDAO.deleteAllArtists()
        }
    }
}