package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.presentation.movie.MovieActivity
import com.example.tmdbclient.presentation.tvShow.TvShowActivity
import dagger.Subcomponent

@TvShowsScope
@Subcomponent(modules = [TvShowsModule::class])
interface TvShowsSubComponent {
    fun inject(tvShowActivity: TvShowActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():TvShowsSubComponent
    }

}