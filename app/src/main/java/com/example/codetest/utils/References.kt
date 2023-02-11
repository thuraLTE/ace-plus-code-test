package com.example.codetest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object References {

    const val WEB_LINK = "https://aceplussolutions.com/"
    const val EVENT_PAGE_LINK = "https://aceplussolutions.com/events-gallery/"
    const val BASE_API_LINK = "https://dummyjson.com/"

    fun checkNetworkStatus(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities != null && (networkCapabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI
            ) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}