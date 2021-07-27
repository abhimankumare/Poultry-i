package com.example.poultry_i.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.poultry_i.R
import com.example.poultry_i.activity.LoginActivity
import com.example.poultry_i.activity.MainActivity
import com.example.poultry_i.storageHelpers.PreferenceHelper
import com.google.android.material.snackbar.Snackbar
import okhttp3.internal.Util
import java.io.IOException

object Utils {

    const val PATTERN_YEAR = "yyyy"
    const val PATTERN_MONTH = "MMM"
    const val PATTERN_MONTH_FULL = "MMMM"
    const val PATTERN_DAY_OF_MONTH = "dd"
    const val PATTERN_DAY_OF_WEEK = "EEEE"
    const val PATTERN_TIME = "hh:mm a"
    const val PATTERN_TIME_24H = "HH:mm"
    const val PATTERN_SERVER_DATE = "yyyy-MM-dd"
    const val PATTERN_SERVER_DATE_TIME = "yyyy-MM-dd HH:mm:ss"
    const val PATTERN_START_WITH_MONTH = "MMM dd , yyyy"
    const val PATTERN_START_WITH_MONTH_NO_YEAR = "MMMM dd"
    const val PATTERN_START_WITH_DATE_NO_YEAR = "dd MMMM"
    const val PATTERN_START_WITH_MONTH_SHORT_NO_YEAR = "MMM dd"
    const val PATTERN_START_WITH_MONTH_WITH_TIME = "MMM dd, yyyy HH:mm:ss"
    const val PATTERN_START_WITH_MONTH_SMALL_NO_YEAR = "MMM dd"

    @SuppressLint("MissingPermission")
    fun isConnectingToInternet(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }

    fun showDialog(
        message: String,
        okListener: DialogInterface.OnClickListener,
        context: Context
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle("Warning")
        builder.setIcon(R.drawable.ic_baseline_exit_to_app_24)
        builder.setPositiveButton("Yes", okListener)
        builder.setNegativeButton("No", okListener)
        builder.create()
        builder.show()
    }

    fun showDialog1(
        message: String,
        okListener: DialogInterface.OnClickListener,
        context: Context
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton("Yes", okListener)
        builder.create()
        builder.show()
    }

    fun toast(context:Context,message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showIndefiniteSnackBar(view: View, msg: String) {
        try {
            val snackbar = Snackbar.make(
                view,
                msg,
                10000
            )
            val textView =
                snackbar.view.findViewById(R.id.snackbar_text) as TextView
            textView.isSingleLine = false
            snackbar.setAction("OK") {
                snackbar.dismiss() }
            snackbar.show()
        } catch (err: Exception) {
            err.printStackTrace()
        }
    }

    fun logoutclearperf(context: Context) {
        PreferenceHelper.clearValueForKey(context, "token")

        val intent = Intent(context, LoginActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        (context as Activity).finish()
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


    fun getHomePageData(context: Context): String {
        var homepageData =
            PreferenceHelper.getStringPreference(context, "homepageData")
        //If Shared Prefrence is Null Then it will use hamburger json file
        if (homepageData == null || homepageData == "" || homepageData == "{}") {
            homepageData =
                getJsonDataFromAsset(context, "homepage/Homepage.json").toString()

        }

        return homepageData
    }
}