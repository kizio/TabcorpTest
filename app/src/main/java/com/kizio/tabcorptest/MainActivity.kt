package com.kizio.tabcorptest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.kizio.tabcorptest.adapter.LaunchAdapter
import com.kizio.tabcorptest.listeners.LaunchListener
import com.kizio.tabcorptest.spacexapi.Launch
import com.kizio.tabcorptest.spacexapi.Launches
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), LaunchListener, ExpandableListView.OnChildClickListener {

    private var adapter : LaunchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLaunchDownloader()?.retrieve(this)

        launch_list.setOnChildClickListener(this)
    }

    /**
     * Called when the listener receives a piece of data from the download code.
     *
     * @param launches A [Launches] object containing the downloaded data
     */
    override fun onReceiveLaunches(launches: Launches) {
        adapter = LaunchAdapter(this, launches)

        launch_list.setAdapter(adapter)
    }

    override fun onChildClick(
        listView: ExpandableListView?,
        view: View?,
        groupPosition: Int,
        childPosition: Int,
        id: Long
    ): Boolean {
        val item = adapter?.getChild(groupPosition, childPosition)

        return if (item is Launch) {
            val intent = Intent(this, RocketActivity::class.java)

            intent.putExtra(RocketActivity.LAUNCH_DATA, item)

            startActivity(intent)

            true
        } else {
            false
        }
    }

    fun onSort(view: View?) {
        adapter?.setDisplayFormat(sort_switch.isChecked, filter_switch.isChecked)
    }
}
