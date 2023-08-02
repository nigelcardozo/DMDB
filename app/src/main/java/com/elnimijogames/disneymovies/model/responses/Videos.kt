package com.elnimijogames.disneymovies.model.responses

import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf()
)
