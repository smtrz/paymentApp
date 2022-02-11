package com.tahir.paymentApp.config

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tahir.paymentApp.repository.AppDB
import com.tahir.paymentApp.repository.appDao

class AppConfig : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        appDao = Room.databaseBuilder(appContext, AppDB::class.java, "switchPayments").build().appDao()

    }

    companion object {

        lateinit var appContext: Context
        lateinit var appDao: appDao

    }
}