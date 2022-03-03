package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedTvShowsUseCase
import com.example.tmdbclient.presentation.tvShow.TvShowsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowsModule {

    @TvShowsScope
    @Provides
    fun provideTvShowsViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        getUpdatedTvShowsUseCase: GetUpdatedTvShowsUseCase
    ): TvShowsViewModelFactory {
        return TvShowsViewModelFactory(getTvShowsUseCase, getUpdatedTvShowsUseCase)
    }

}