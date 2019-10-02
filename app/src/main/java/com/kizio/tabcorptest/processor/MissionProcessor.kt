package com.kizio.tabcorptest.processor

import com.kizio.tabcorptest.spacexapi.Launch

class MissionProcessor : BaseLaunchProcessor<String>() {

    override fun getKey(launch: Launch?) : String? {
        return launch?.getFirstLetter()
    }

    override fun getSortKey(launch: Launch?): String? {
        return launch?.getMissionName()
    }
}
