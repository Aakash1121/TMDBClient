package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowsRepository
import com.example.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository:MovieRepository):GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideGetMovieUpdatedUseCase(movieRepository:MovieRepository):GetUpdatedMovieUseCase{
        return GetUpdatedMovieUseCase(movieRepository)
    }
    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository):GetArtistUseCase{
        return GetArtistUseCase(artistRepository)
    }

    @Provides
    fun provideGetUpdateArtistUseCase(artistRepository: ArtistRepository):GetUpdatedArtistUseCase{
        return GetUpdatedArtistUseCase(artistRepository)
    }

    @Provides
    fun provideGetTvShowsUseCase(tvShowsRepository: TvShowsRepository):GetTvShowsUseCase{
        return GetTvShowsUseCase(tvShowsRepository)
    }

    @Provides
    fun provideGetUpdatedTvShowsUseCase(tvShowsRepository: TvShowsRepository):GetUpdatedTvShowsUseCase{
        return GetUpdatedTvShowsUseCase(tvShowsRepository)
    }

}
