package com.volasoftware.breakingbadapp.utils

import com.volasoftware.breakingbadapp.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

object LogLevelUtil {

    fun getLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
    }
}