package com.kizio.tabcorptest.listeners

/**
 * Provides a common interface for handling error messages from Volley.
 *
 * @author Graeme Sutherland
 * @since 01/10/2019
 */
interface ErrorListener {
    /**
     * Called when a network error is received.
     *
     * @param error The error message [String]
     */
    fun onError(error: String?)
}
