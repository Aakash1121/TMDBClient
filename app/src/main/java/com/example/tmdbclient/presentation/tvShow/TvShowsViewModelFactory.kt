package com.example.tmdbclient.presentation.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedTvShowsUseCase

class TvShowsViewModelFactory(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val getUpdatedTvShowsUseCase: GetUpdatedTvShowsUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowsViewModel(getTvShowsUseCase,getUpdatedTvShowsUseCase) as T
    }
}