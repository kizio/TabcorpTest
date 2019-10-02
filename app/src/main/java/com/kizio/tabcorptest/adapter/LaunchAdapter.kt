package com.kizio.tabcorptest.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.kizio.tabcorptest.spacexapi.Launch
import com.kizio.tabcorptest.spacexapi.Launches
import java.util.*
import kotlin.collections.ArrayList

class LaunchAdapter (private val context: Context, private val launches: Launches?) : BaseExpandableListAdapter() {

    private val launchesToDisplay = TreeMap<String, List<Launch>>()

    init {
        setDisplayFormat(true, false)
    }

    override fun getGroup(groupPosition: Int): String {
        return launchesToDisplay.keys.elementAt(groupPosition)
    }

    private fun getGroupContents(groupPosition: Int) : List<Launch>? {
        val group = getGroup(groupPosition)

        return launchesToDisplay[group]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Launch? {
        return getGroupContents(groupPosition)?.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong().shl(32)
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return getGroupId(groupPosition) + childPosition
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val textView: TextView = if (convertView is TextView) {
            convertView
        } else {
            TextView(context)
        }

        textView.text = getGroup(groupPosition)

        return textView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val launch = getChild(groupPosition, childPosition)
        val textView: TextView = if (convertView is TextView) {
            convertView
        } else {
            TextView(context)
        }

        textView.text = launch?.getFlightNumber()?.toString() + " - " + launch?.getDateString() + " " + launch?.getMissionName()

        return textView
    }

    override fun getGroupCount(): Int {
        return launchesToDisplay.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return getGroupContents(groupPosition)?.size ?: 0
    }

    fun setDisplayFormat(isOrderByYear: Boolean, isFilterByFailure: Boolean) {
        val filtered = filterLaunches(isFilterByFailure)
    }

    private fun filterLaunches(isFilterByFailure: Boolean) : List<Launch>? {
        val filtered: List<Launch>?

        if (launches != null) {
            filtered = ArrayList<Launch>()

            for (launch in launches) {
                if (launch != null && (!isFilterByFailure || launch.isLaunchSuccess())){
                    filtered.add (launch)
                }
            }
        } else {
            filtered = null
        }

        return filtered
    }
}