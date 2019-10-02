package com.kizio.tabcorptest.spacexapi

import org.json.JSONArray

class Launches (private val launches: JSONArray?) : Iterable<Launch?> {
    fun getLaunchCount() : Int {
        return launches?.length() ?: 0
    }

    fun getLaunchData(launchNumber: Int) : Launch? {
        val json = launches?.optJSONObject(launchNumber)

        return if (json != null) {
            Launch(json)
        } else {
            null
        }
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<Launch?> {
        return object : Iterator<Launch?> {
            var position = 0

            /**
             * Returns `true` if the iteration has more elements.
             */
            override fun hasNext(): Boolean {
                return position < getLaunchCount()
            }

            /**
             * Returns the next element in the iteration.
             */
            override fun next(): Launch? {
                position ++

                return getLaunchData(position)
            }
        }
    }
}
