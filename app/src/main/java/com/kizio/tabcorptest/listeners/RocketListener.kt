package com.kizio.tabcorptest.listeners

import com.kizio.tabcorptest.spacexapi.Rocket

/**
 * Provides a callback for receiving data from the network.
 *
 * TODO: Replace this with a single interface that takes a generic parameter.
 *
 * @author Graeme Sutherland
 * @since 01/10/2019
 */
interface RocketListener : ErrorListener {

	/**
	 * Called when the listener receives a piece of JSON back from the download code.
	 *
	 * @param rocket A [Rocket] containing the downloaded data
	 */
	fun onReceiveRocket(rocket: Rocket)
}