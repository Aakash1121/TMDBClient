package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbclient.domain.usecase.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedArtistUseCase

class ArtistsViewModelFactory(
    private val getArtistUseCase: GetArtistUseCase,
    private val getUpdatedArtistUseCase: GetUpdatedArtistUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArtistsViewModel(getArtistUseCase,getUpdatedArtistUseCase) as T
    }
}