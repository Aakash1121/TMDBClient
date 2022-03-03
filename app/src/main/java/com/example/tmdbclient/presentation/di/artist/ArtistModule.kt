package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.domain.usecase.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedArtistUseCase
import com.example.tmdbclient.presentation.artist.ArtistsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistUseCase: GetArtistUseCase,
        getUpdatedArtistUseCase: GetUpdatedArtistUseCase
    ): ArtistsViewModelFactory {
        return ArtistsViewModelFactory(getArtistUseCase, getUpdatedArtistUseCase)
    }

}