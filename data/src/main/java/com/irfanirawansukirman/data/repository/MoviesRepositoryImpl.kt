package com.irfanirawansukirman.data.repository

import com.irfanirawansukirman.data.network.base.getData
import com.irfanirawansukirman.data.network.model.MoviesResponse
import com.irfanirawansukirman.data.network.service.MovieApi
import com.irfanirawansukirman.data.repository.base.BaseRepository
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import com.irfanirawansukirman.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val movieApi: MovieApi) :
    BaseRepository<MovieInfoMapper, MoviesResponse>(),
    MoviesRepository {

    override suspend fun getMovies(apiKey: String, sortBy: String): Result<MovieInfoMapper> {
        return fetchData(dataProvider = { movieApi.getMovies(apiKey, sortBy).getData() })
    }

}