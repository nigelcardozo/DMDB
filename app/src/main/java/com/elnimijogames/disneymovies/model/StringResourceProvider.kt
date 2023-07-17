package com.elnimijogames.disneymovies.model

import androidx.annotation.StringRes

interface StringResourceProvider {
    fun getString(@StringRes resourceId: Int, parameter: String): String
}