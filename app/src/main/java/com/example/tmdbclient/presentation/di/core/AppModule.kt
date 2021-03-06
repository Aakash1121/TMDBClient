package com.example.tmdbclient.presentation.di.core

import android.content.Context
import com.example.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.example.tmdbclient.presentation.di.movie.MovieSubComponent
import com.example.tmdbclient.presentation.di.tvshow.TvShowsSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieSubComponent::class,ArtistSubComponent::class,TvShowsSubComponent::class])
class AppModule(private val context:Context) {

    @Singleton
    @Provides
    fun providesApplicationContext():Context{
        return context.applicationContext
    }

}