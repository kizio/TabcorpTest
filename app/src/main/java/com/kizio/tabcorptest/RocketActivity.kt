package com.kizio.tabcorptest

import android.os.Bundle
import com.kizio.tabcorptest.listeners.RocketListener
import com.kizio.tabcorptest.spacexapi.Launch
import com.kizio.tabcorptest.spacexapi.Rocket

class RocketActivity : BaseActivity(), RocketListener {

    companion object {
        const val LAUNCH_DATA = "launch_data"
    }

    private var launch : Launch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch = intent.extras?.getParcelable<Launch>(LAUNCH_DATA)

        getLaunchDownloader()?.retrieve(this, "falcon1")
    }

    /**
     * Called when the listener receives a piece of JSON back from the download code.
     *
     * @param rocket A [Rocket] containing the downloaded data
     */
    override fun onReceiveRocket(rocket: Rocket) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
