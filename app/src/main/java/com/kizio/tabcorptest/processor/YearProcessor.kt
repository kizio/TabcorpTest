package com.kizio.tabcorptest.processor

import com.kizio.tabcorptest.spacexapi.Launch
import java.util.*

class YearProcessor : BaseLaunchProcessor<Date>() {

    override fun getKey(launch: Launch?) : String? {
        return launch?.getYear()
    }

    override fun getSortKey(launch: Launch?): Date? {
        return launch?.getDate()
    }
}
