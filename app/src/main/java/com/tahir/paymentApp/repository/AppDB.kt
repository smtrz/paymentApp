package com.tahir.paymentApp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tahir.paymentApp.models.Payments
import com.tahir.paymentApp.models.Refunds

// Creating database abstract class holding the data access object
@Database(entities = [Payments::class,Refunds::class], version = 1, exportSchema = false)

abstract class AppDB : RoomDatabase() {

    abstract fun appDao(): appDao
}