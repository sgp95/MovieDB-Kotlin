package com.santiago.moviedb_kotlin.di.modules

import android.content.Context
import androidx.room.Room
import com.santiago.moviedb_kotlin.data.datasource.api.ApiManager
import com.santiago.moviedb_kotlin.data.datasource.api.Services
import com.santiago.moviedb_kotlin.data.datasource.api.impl.ApiMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.api.impl.ApiTvShowDataSource
import com.santiago.moviedb_kotlin.data.datasource.interfaces.LocalMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.interfaces.LocalTvShowsDataSource
import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.interfaces.RemoteTvShowsDataSource
import com.santiago.moviedb_kotlin.data.datasource.room.AppDatabase
import com.santiago.moviedb_kotlin.data.datasource.room.dao.MoviesDao
import com.santiago.moviedb_kotlin.data.datasource.room.dao.TvShowDao
import com.santiago.moviedb_kotlin.data.datasource.room.impl.RoomMoviesDataSource
import com.santiago.moviedb_kotlin.data.datasource.room.impl.RoomTvShowsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    // solve issue with this: https://medium.com/@svvashishtha/using-room-with-hilt-cb57a1bc32f
    /* Retrofit */
    @Provides
    @Singleton
    fun providesServices(): Services = ApiManager().create(Services::class.java)

    @Provides
    @Singleton
    fun providesRemoteMoviesDataSource(apiMoviesDataSource: ApiMoviesDataSource): RemoteMoviesDataSource = apiMoviesDataSource

    @Provides
    @Singleton
    fun providesRemoteTvShowsDataSource(apiTvShowDataSource: ApiTvShowDataSource): RemoteTvShowsDataSource = apiTvShowDataSource

    /* Room */
    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "MovieApp_Database").build()

    @Provides
    @Singleton
    fun providesMoviesDao(appDatabase: AppDatabase): MoviesDao = appDatabase.moviesDao()

    @Provides
    @Singleton
    fun providesTvShowDao(appDatabase: AppDatabase): TvShowDao = appDatabase.tvShowDao()

    @Provides
    @Singleton
    fun providesLocalMoviesDataSource(roomMoviesDataSource: RoomMoviesDataSource): LocalMoviesDataSource = roomMoviesDataSource

    @Provides
    @Singleton
    fun providesLocalTvShowsDataSource(roomTvShowsDataSource: RoomTvShowsDataSource): LocalTvShowsDataSource = roomTvShowsDataSource
}