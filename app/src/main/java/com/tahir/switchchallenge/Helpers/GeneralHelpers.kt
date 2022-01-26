package com.tahir.switchchallenge.Helpers

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import com.highbryds.washer.Helpers.UIHelper
import java.text.SimpleDateFormat
import java.util.*

object GeneralHelpers {

    fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        return currentDate

    }

    /**
     * Display progressbar
     */
    fun displayProgressBar(isDisplayed: Boolean, progess: View, context: Activity) {

        if (isDisplayed) {

            context.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            );
        } else {
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


        }

        progess.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    /**
     * This method will be used to show the error messages if any
     */
    fun displayError(message: String?, context: Context) {

        var msg: String?
        msg = message
        if (message == null) {

            msg = "unknown error"
        }
        UIHelper.showShortToastInCenter(context, msg!!)


    }

    fun generateRadomAmount(): Int {

        // initialize a Random object s
        val random = Random()

// generate a random integer from 0 to 899, then add 100
        return random.nextInt(900) + 100
    }
}