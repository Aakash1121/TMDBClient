package com.example.tmdbclient.data.db

import androidx.room.*
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.movie.MovieList

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies:List<Movie>)

    @Query("DELETE FROM popular_movie")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM popular_movie")
    suspend fun getAllMovies():List<Movie>
}