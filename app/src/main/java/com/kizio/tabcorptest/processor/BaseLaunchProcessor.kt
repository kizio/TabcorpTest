package com.kizio.tabcorptest.processor

import com.kizio.tabcorptest.spacexapi.Launch
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseLaunchProcessor {

    fun process (launches: List<Launch>, map: TreeMap<String, ArrayList<Launch>>)  {
        map.clear()

        Collections.sort(launches, getComparator())

        for (launch in launches) {
            val key = getKey(launch)
            val values: List<Launch>?

            if (key != null) {
                if (!map.containsKey(key)) {
                    map[key] = ArrayList<Launch>()
                }

                map[key]?.add(launch)
            }
        }
    }

    protected abstract fun getKey (launch: Launch?) : String?

    private fun getComparator(): Comparator<Launch> {
        return Comparator<Launch> { launchA, launchB ->
            val keyA = getKey(launchA)
            val keyB = getKey(launchB)

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
        }}