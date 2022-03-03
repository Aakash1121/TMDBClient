package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.domain.usecase.GetArtistUseCase
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedArtistUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedMovieUseCase
import com.example.tmdbclient.presentation.artist.ArtistsViewModelFactory
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        getUpdatedMovieUseCase: GetUpdatedMovieUseCase
    ):MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesUseCase,getUpdatedMovieUseCase)
    }

}