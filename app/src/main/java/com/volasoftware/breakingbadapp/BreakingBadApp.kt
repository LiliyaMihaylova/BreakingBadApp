package com.volasoftware.breakingbadapp

import android.app.Application
import android.content.Context
import com.volasoftware.breakingbadapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BreakingBadApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    init {
        instance = this
    }

    companion object {
        private const val TAG = "BreakingBadApp"
        private var instance: BreakingBadApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .applicationBind(this)
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = activityDispatchingAndroidInjector
}