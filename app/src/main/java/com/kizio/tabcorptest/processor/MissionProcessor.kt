package com.kizio.tabcorptest.processor

import com.kizio.tabcorptest.spacexapi.Launch

class MissionProcessor : BaseLaunchProcessor() {

    override fun getKey(launch: Launch?) : String? {
        return launch?.getFirstLetter()
    }
}
