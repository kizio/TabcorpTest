package com.kizio.tabcorptest.processor

import com.kizio.tabcorptest.spacexapi.Launch

class YearProcessor : BaseLaunchProcessor() {

    override fun getKey(launch: Launch?) : String? {
        return launch?.getYear()
    }

    override fun getComparator(): Comparator<Launch> {
        return Comparator<Launch> { launchA, launchB ->
            val yearA = launchA?.getYear()
            val yearB = launchB?.getYear()

            if (yearA != null) {
                if (yearB != null) {
                    yearA.compareTo(yearB)
                } else {
                    -1
                }
            } else if (yearB != null) {
                1
            } else {
                0
            }
        }
    }
}