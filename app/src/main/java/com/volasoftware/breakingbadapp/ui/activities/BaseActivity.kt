package com.volasoftware.breakingbadapp.ui.activities

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        onViewCreated()
    }

    protected abstract fun onViewCreated()

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}