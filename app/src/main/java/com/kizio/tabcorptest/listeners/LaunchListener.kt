package com.kizio.tabcorptest.listeners

import com.kizio.tabcorptest.spacexapi.Launches

/**
 * Provides a callback for receiving data from the network.
 *
 * TODO: Replace this with a single interface that takes a generic parameter.
 *
 * @author Graeme Sutherland
 * @since 09/08/2019
 */
interface LaunchListener  : ErrorListener{

	/**
	 * Called when the listener receives a piece of JSON back from the download code.
	 *
	 * @param launches A [Launches] object containing the downloaded data
	 */
	fun onReceiveLaunches(launches: Launches)
}