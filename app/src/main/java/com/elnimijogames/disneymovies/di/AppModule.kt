package com.elnimijogames.disneymovies.di

import android.content.Context
import com.elnimijogames.disneymovies.model.MoviesListRepository
import com.elnimijogames.disneymovies.model.StringResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun providesMovieListRepository(): MoviesListRepository = MoviesListRepository()

    @Provides
    fun providesStringResourceProvider(@ApplicationContext appContext: Context): StringResourceProviderImpl {
        return StringResourceProviderImpl(appContext.resources)
    }

    @Provides
    fun providesStringResourceProviderImpl(@ApplicationContext appContext: Context): StringResourceProviderImpl {
        return StringResourceProviderImpl(appContext.resources)
    }
}