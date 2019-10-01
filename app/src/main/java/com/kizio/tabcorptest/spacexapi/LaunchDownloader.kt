package com.kizio.tabcorptest.spacexapi

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.kizio.tabcorptest.listeners.LaunchListener
import com.kizio.tabcorptest.listeners.RocketListener

/**
 * Provides methods for downloading the raw JSON data for launches from the SpaceX server.
 *
 * @author Graeme Sutherland
 * @since 09/08/2019
 * @constructor Creates a new instance of the [LaunchDownloader] class.
 * @param context The [Context] the downloader is running in.
 */
class LaunchDownloader(context: Context) {

	/**
	 * Holds constant values for the [LaunchDownloader].
	 */
	companion object {
		/**
		 * Debug tag [String] for logging.
		 */
		private const val TAG = "LaunchDownloader"

		/**
		 * URL [String] containing the target for downloading the JSON data for the launch data.
		 *
		 * In a proper, commercial implementation, I'd push this into a configuration file so that
		 * it could be easily changed. However, I'm keeping things simple as it's a piece of demo
		 * code.
		 */
		private const val LAUNCHES_URL = "https://api.spacexdata.com/v3/launches"

		private const val ROCKET_URL = "https://api.spacexdata.com/v3/rockets/"
	}

	/**
	 * The request queue for handling the Volley requests.
	 */
	private val requestQueue = Volley.newRequestQueue(context)

	/**
	 * Performs the network call to download the launches data.
	 *
	 * @param listener The [LaunchListener] that handles the result of the download operation
	 */
	fun retrieve(listener: LaunchListener) {
		val jsonRequest = JsonArrayRequest(Request.Method.GET, LAUNCHES_URL, null,
			Response.Listener { response ->
				listener.onReceiveLaunches (Launches(response))
			},
			Response.ErrorListener { error ->
				Log.e(TAG, "Failed to download launch data from $LAUNCHES_URL", error)

				listener.onError(error.message)
			}
		)

		requestQueue.add(jsonRequest)
	}

	/**
	 * Performs the network call to download a piece of rocket data.
	 */
	fun retrieve(listener: RocketListener, rocketId: String?) {
		val jsonRequest = JsonObjectRequest(Request.Method.GET, ROCKET_URL + rocketId, null,
			Response.Listener { response ->
				listener.onReceiveRocket (Rocket(response))
			},
			Response.ErrorListener { error ->
				Log.e(TAG, "Failed to download rocket data from $LAUNCHES_URL", error)

				listener.onError(error.message)
			}
		)

		requestQueue.add(jsonRequest)
	}
}
