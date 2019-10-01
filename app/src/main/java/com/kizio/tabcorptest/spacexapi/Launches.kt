package com.kizio.tabcorptest.spacexapi

import org.json.JSONArray

class Launches (private val launches: JSONArray?) {

    fun getLaunchCount() : Int {
        return launches?.length() ?: 0
    }

    fun getLaunchData(launchNumber: Int) : Launch? {
        val json = launches?.optJSONObject(launchNumber)
        val launch: Launch?

        if (json != null) {
            launch = Launch(json)
        } else {
            launch = null
        }

        return launch
    }
}
