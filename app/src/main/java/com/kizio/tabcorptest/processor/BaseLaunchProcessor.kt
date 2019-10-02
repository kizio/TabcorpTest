package com.kizio.tabcorptest.processor

import com.kizio.tabcorptest.spacexapi.Launch
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseLaunchProcessor<T : Comparable<T>> {

    fun process(launches: List<Launch>, map: TreeMap<String, ArrayList<Launch>>) {
        map.clear()

        Collections.sort(launches, getComparator())

        for (launch in launches) {
            val key = getKey(launch)

            if (key != null) {
                if (!map.containsKey(key)) {
                    map[key] = ArrayList()
                }

                map[key]?.add(launch)
            }
        }
    }

    protected abstract fun getKey(launch: Launch?): String?

    protected abstract fun getSortKey(launch: Launch?): T?

    @Suppress("UNUSED")
    private fun getComparator(): Comparator<Launch> {
        return Comparator { launchA, launchB ->
            val keyA = getSortKey(launchA)
            val keyB = getSortKey(launchB)

            if (keyA != null) {
                if (keyB != null) {
                    keyA.compareTo(keyB)
                } else {
                    -1
                }
            } else if (keyB != null) {
                1
            } else {
                0
            }
        }
    }
}
