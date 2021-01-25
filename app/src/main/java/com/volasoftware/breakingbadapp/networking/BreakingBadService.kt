package com.volasoftware.breakingbadapp.networking

import com.volasoftware.breakingbadapp.networking.models.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface BreakingBadService {

    @Headers("Content-Type: application/json")
    @GET("characters")
    fun getCharacters(): Call<List<CharactersResponse>>
}