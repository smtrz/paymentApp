package com.tahir.switchchallenge.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.models.Refunds

// Creating database abstract class holding the data access object
@Database(entities = [Payments::class,Refunds::class], version = 1, exportSchema = false)

abstract class AppDB : RoomDatabase() {

    abstract fun appDao(): appDao
}