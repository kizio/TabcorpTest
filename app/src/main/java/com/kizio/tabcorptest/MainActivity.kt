package com.kizio.tabcorptest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.kizio.tabcorptest.adapter.LaunchAdapter
import com.kizio.tabcorptest.listeners.LaunchListener
import com.kizio.tabcorptest.spacexapi.Launch
import com.kizio.tabcorptest.spacexapi.Launches
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), LaunchListener, AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLaunchDownloader()?.retrieve(this)

        launch_list.onItemClickListener = this
    }

    /**
     * Called when the listener receives a piece of data from the download code.
     *
     * @param launches A [Launches] object containing the downloaded data
     */
    override fun onReceiveLaunches(launches: Launches) {
        launch_list.adapter = LaunchAdapter(this, launches)
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val adapter = adapterView?.adapter
        val item = adapter?.getItem(position)

        if (item is Launch) {
            val intent = Intent(this, RocketActivity::class.java)

            intent.extras?.putParcelable(RocketActivity.LAUNCH_DATA, item)

            startActivity(intent)
        }
    }
}
