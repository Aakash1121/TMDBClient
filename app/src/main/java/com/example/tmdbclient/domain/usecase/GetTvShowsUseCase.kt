package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.domain.repository.TvShowsRepository

class GetTvShowsUseCase(private val tvShowsRepository: TvShowsRepository) {

    suspend fun execute():List<TvShow>? = tvShowsRepository.getTvShows()

}