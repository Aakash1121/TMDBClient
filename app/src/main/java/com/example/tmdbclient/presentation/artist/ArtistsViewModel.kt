package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedArtistUseCase

class ArtistsViewModel(
    private val getArtistUseCase: GetArtistUseCase,
    private val getUpdatedArtistUseCase: GetUpdatedArtistUseCase
):ViewModel() {

    fun getArtists()= liveData{
        val movieList=getArtistUseCase.execute()
        emit(movieList)
    }

    fun updateArtistsList()= liveData {
        val movieList=getUpdatedArtistUseCase.execute()
        emit(movieList)
    }

}