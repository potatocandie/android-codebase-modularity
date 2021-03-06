package com.irfanirawansukirman.domain.interaction.movies

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.info.MovieInfo
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper

interface MoviesUseCase {
    // remote sources
    suspend fun getMovies(apiKey: String, sortBy: String): Result<MovieInfoMapper>

    // local sources
    suspend fun getLocalMovies(): Result<List<MovieInfo>>
    suspend fun saveLocalMovies(movies: List<MovieInfo>): Result<Boolean>
    suspend fun deleteLocalMovies()
}