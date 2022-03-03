package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.db.ArtistDAO
import com.example.tmdbclient.data.db.MovieDAO
import com.example.tmdbclient.data.db.TvShowDAO
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasourceimpl.ArtistLocalDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasourceimpl.MovieLocalDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowsLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideArtistsLocalDataSource(artistDAO: ArtistDAO): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(artistDAO)
    }

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDAO: MovieDAO): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDAO)
    }

    @Singleton
    @Provides
    fun provideTvShowsLocalDataSource(tvShowDAO: TvShowDAO): TvShowsLocalDataSource {
        return TvShowsLocalDataSourceImpl(tvShowDAO)
    }

}