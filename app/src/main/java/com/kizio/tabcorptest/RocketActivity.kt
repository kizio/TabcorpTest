package com.kizio.tabcorptest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.kizio.tabcorptest.listeners.RocketListener
import com.kizio.tabcorptest.spacexapi.Launch
import com.kizio.tabcorptest.spacexapi.Rocket
import kotlinx.android.synthetic.main.activity_rocket.*

class RocketActivity : BaseActivity(), RocketListener {

    companion object {
        const val LAUNCH_DATA = "launch_data"
    }

    private var launch : Launch? = null

    private var rocket : Rocket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rocket)

        launch = intent.getParcelableExtra(LAUNCH_DATA)

        launch?.let {
            getLaunchDownloader()?.retrieve(this, it.getRocketId())
        }
    }

    /**
     * Called when the listener receives a piece of JSON back from the download code.
     *
     * @param rocket A [Rocket] containing the downloaded data
     */
    override fun onReceiveRocket(rocket: Rocket) {
        open_wikipedia.isEnabled = true

        this.rocket = rocket
    }

    fun onClickOpenWikipedia(view: View?) {
        rocket?.let {
            val wikipediaUrl = it.getWikipedia()

            if (wikipediaUrl != null && wikipediaUrl.isNotEmpty()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(wikipediaUrl)))
            }
        }
    }
}
