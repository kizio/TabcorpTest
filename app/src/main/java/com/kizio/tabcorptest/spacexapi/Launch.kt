package com.kizio.tabcorptest.spacexapi

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Launch : Parcelable {
    companion object CREATOR : Parcelable.Creator<Launch> {
        private const val TO_MILLISECONDS = 1000

        private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        private val yearFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)

        override fun createFromParcel(parcel: Parcel): Launch {
            return Launch(parcel)
        }

        override fun newArray(size: Int): Array<Launch?> {
            return arrayOfNulls(size)
        }
    }

    private val data: JSONObject?

    constructor(jsonObject: JSONObject?) {
        data = jsonObject
    }

    constructor(parcel: Parcel) {
        val jsonString = parcel.readString()

        data = if (jsonString != null) {
            JSONObject(jsonString)
        } else {
            null
        }
    }

    fun getFlightNumber() : Int? {
        return data?.optInt("flight_number")
    }

    fun getMissionName() : String? {
        return data?.optString("mission_name")
    }

    private fun getDate() : Date? {
        val time = data?.optLong("launch_date_unix")

        // SpaceX uses the UNIX time in seconds, Java in milliseconds.
        return time?.let { Date (it * TO_MILLISECONDS) }
    }

    fun getDateString() : String? {
        return getDate()?.let { dateFormat.format(it)}
    }

    fun getRocketId() : String? {
        return data?.optJSONObject("rocket")?.optString("rocket_id")
    }

    fun isLaunchSuccess() : Boolean {
        return data?.optBoolean("launch_success") ?: false
    }

    fun getYear() : String? {
        return getDate()?.let { yearFormat.format(it)}
    }

    /**
     * Gets the first letter of the mission name as an upper case [String]. Whilst returning a
     * character might be easier, this allows the same code to be used to bucket the flight list
     * by year or name.
     *
     * @return The first letter of the mission name in a [String]
     */
    fun getFirstLetter() : String? {
        return getMissionName()?.substring(0, 1)?.toUpperCase(Locale.ENGLISH)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data?.toString())
    }

    override fun describeContents(): Int {
        return 0
    }
}
