package com.kizio.tabcorptest.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kizio.tabcorptest.spacexapi.Launch
import com.kizio.tabcorptest.spacexapi.Launches

class LaunchAdapter (private val context: Context, private val launches: Launches?) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val launchData = getItem(position)
        val textView: TextView = if (convertView is TextView) {
            convertView
        } else {
            TextView(context)
        }

        textView.text = launchData?.getFlightNumber()?.toString() + " - " + launchData?.getDateString() + " " + launchData?.getMissionName()

        return textView
    }

    override fun getItem(position: Int): Launch? {
        return launches?.getLaunchData(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return launches?.getLaunchCount() ?: 0
    }
}