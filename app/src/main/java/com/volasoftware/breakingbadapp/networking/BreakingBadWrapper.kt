package com.volasoftware.breakingbadapp.networking

import androidx.annotation.NonNull
import com.volasoftware.breakingbadapp.networking.models.Character
import com.volasoftware.breakingbadapp.utils.LogLevelUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = LogLevelUtil.getLevel()
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.connectTimeout(NetworkingConstants.TIMEOUT, TimeUnit.SECONDS)
        okHttpBuilder.addInterceptor(loggingInterceptor)
        val client = okHttpBuilder.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(NetworkingConstants.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create<BreakingBadService>(BreakingBadService::class.java)
    }

    fun getCharacters(dataListener: DataListener<List<Character>>) {
        val call: Call<List<Character>> = apiService.getCharacters()
        call.enqueue(object : Callback<List<Character>?> {
            override fun onResponse(
                @NonNull call: Call<List<Character>?>,
                @NonNull response: Response<List<Character>?>
            ) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let { dataListener.onDataReceived(it) }
                    }

                    else -> {
                        dataListener.onDataFailed()
                    }
                }
            }

            override fun onFailure(@NonNull call: Call<List<Character>?>, @NonNull t: Throwable) {
                dataListener.onDataFailed()
            }
        })
    }

    interface DataListener<T> {
        fun onDataReceived(data: T)
        fun onDataFailed()
    }
}