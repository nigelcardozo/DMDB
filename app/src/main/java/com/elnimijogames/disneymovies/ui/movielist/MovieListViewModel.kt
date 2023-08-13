package com.elnimijogames.disneymovies.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.elnimijogames.disneymovies.AppDispatchers
import com.elnimijogames.disneymovies.model.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val moviesPagingSource: MoviesPagingSource
    ) : ViewModel() {

    val items = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            moviesPagingSource
        }
    ).flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch(appDispatchers.IO) {
        }
    }
}