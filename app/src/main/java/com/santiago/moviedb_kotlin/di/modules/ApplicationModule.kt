package com.santiago.moviedb_kotlin.di.modules

import com.santiago.moviedb_kotlin.data.datasource.api.ApiManager
import com.santiago.moviedb_kotlin.data.datasource.api.Services
import com.santiago.moviedb_kotlin.data.datasource.api.impl.ApiMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.api.impl.ApiTvShowDataSource
import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteTvShowsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun providesServices(): Services = ApiManager().create(Services::class.java)

    @Provides
    @Singleton
    fun providesRemoteMoviesDataSource(apiMoviesDataSource: ApiMoviesDataSource): RemoteMoviesDataSource = apiMoviesDataSource

    @Provides
    @Singleton
    fun providesRemoteTvShowsDataSource(apiTvShowDataSource: ApiTvShowDataSource): RemoteTvShowsDataSource = apiTvShowDataSource

}