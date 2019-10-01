package com.kizio.tabcorptest

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kizio.tabcorptest.listeners.ErrorListener
import com.kizio.tabcorptest.spacexapi.LaunchDownloader

abstract class BaseActivity : AppCompatActivity(), ErrorListener {

    private var launchDownloader : LaunchDownloader? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        launchDownloader = LaunchDownloader(this)
    }

    /**
     * Called when a network error is received. I should add better error handling than this, but it
     * is a piece of demo code.
     *
     * @param error The error message [String]
     */
    override fun onError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    protected fun getLaunchDownloader() : LaunchDownloader? {
        return launchDownloader
    }
}