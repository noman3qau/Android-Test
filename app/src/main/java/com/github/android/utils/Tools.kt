package com.github.android.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


object Tools {

    /**
     * A tool function to check if network available
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

}