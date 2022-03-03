package com.example.tmdbclient.presentation.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedArtistUseCase
import com.example.tmdbclient.domain.usecase.GetUpdatedTvShowsUseCase

class TvShowsViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val getUpdatedTvShowsUseCase: GetUpdatedTvShowsUseCase
):ViewModel() {

    fun getTvShows()= liveData{
        val movieList=getTvShowsUseCase.execute()
        emit(movieList)
    }

    fun updateTvShows()= liveData {
        val movieList=getUpdatedTvShowsUseCase.execute()
        emit(movieList)
    }

}