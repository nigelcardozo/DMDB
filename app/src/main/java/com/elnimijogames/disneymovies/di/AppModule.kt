package com.elnimijogames.disneymovies.di

import android.content.Context
import com.elnimijogames.disneymovies.AppDispatchers
import com.elnimijogames.disneymovies.model.MovieRepository
import com.elnimijogames.disneymovies.model.StringResourceProviderImpl
import com.elnimijogames.disneymovies.model.api.TMDBWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun providesTMDBWebService(): TMDBWebService = TMDBWebService()

    @Provides
    fun providesMovieRepository(): MovieRepository = MovieRepository(tmdbWebService = TMDBWebService())

    @Provides
    fun providesStringResourceProvider(@ApplicationContext appContext: Context): StringResourceProviderImpl {
        return StringResourceProviderImpl(appContext.resources)
    }

    @Provides
    fun providesAppDispatcher(): AppDispatchers {
        return AppDispatchers()
    }

    @Provides
    fun providesStringResourceProviderImpl(@ApplicationContext appContext: Context): StringResourceProviderImpl {
        return StringResourceProviderImpl(appContext.resources)
    }
}