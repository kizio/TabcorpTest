package com.kizio.tabcorptest.spacexapi

import org.json.JSONObject

class Rocket (private val data: JSONObject?) {

    fun getRocketName() : String? {
        return data?.optString("rocket_name")
    }

    fun getDescription() : String? {
        return data?.optString("description")
    }

    fun getWikipedia() : String? {
        return data?.optString("wikipedia")
    }
}
