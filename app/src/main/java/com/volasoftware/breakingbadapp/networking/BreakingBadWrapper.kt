package com.volasoftware.breakingbadapp.networking

import androidx.annotation.NonNull
import com.volasoftware.breakingbadapp.networking.models.CharactersResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BreakingBadWrapper @Inject constructor() {

    private var apiService: BreakingBadService

    init {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.connectTimeout(NetworkingConstants.TIMEOUT, TimeUnit.SECONDS)
        val client = okHttpBuilder.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(NetworkingConstants.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create<BreakingBadService>(BreakingBadService::class.java)
    }

    fun getCharacters() {
        val call: Call<List<CharactersResponse>> = apiService.getCharacters()
        call.enqueue(object : Callback<List<CharactersResponse>?> {
            override fun onResponse(
                @NonNull call: Call<List<CharactersResponse>?>,
                @NonNull response: Response<List<CharactersResponse>?>
            ) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let { }
                    }

                    else -> {
                        response.errorBody()?.let {}
                    }
                }
            }

            override fun onFailure(
                @NonNull call: Call<List<CharactersResponse>?>, @NonNull t: Throwable
            ) {

            }
        })
    }
}