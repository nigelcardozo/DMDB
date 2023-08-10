package com.elnimijogames.disneymovies

import kotlinx.coroutines.test.TestCoroutineDispatcher

private val testDispatcher = AppDispatchers(
    IO = TestCoroutineDispatcher()
)