package com.example.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.example.tmdbclient.data.db.ArtistDAO
import com.example.tmdbclient.data.db.MovieDAO
import com.example.tmdbclient.data.db.TMDBDatabase
import com.example.tmdbclient.data.db.TvShowDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): TMDBDatabase {
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbClient").build()
    }

    @Singleton
    @Provides
    fun provideArtistsDao(tmdbDatabase: TMDBDatabase): ArtistDAO {
        return tmdbDatabase.artistDao()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDAO {
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideTvShowsDao(tmdbDatabase: TMDBDatabase): TvShowDAO {
        return tmdbDatabase.tvShowDao()
    }
}