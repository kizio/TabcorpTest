package com.kizio.tabcorptest

import android.os.Bundle
import com.kizio.tabcorptest.adapter.LaunchAdapter
import com.kizio.tabcorptest.listeners.LaunchListener
import com.kizio.tabcorptest.spacexapi.Launches
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), LaunchListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLaunchDownloader()?.retrieve(this)
    }

    /**
     * Called when the listener receives a piece of data from the download code.
     *
     * @param launches A [Launches] object containing the downloaded data
     */
    override fun onReceiveLaunches(launches: Launches) {
        launch_list.adapter = LaunchAdapter(this, launches)
    }
}
