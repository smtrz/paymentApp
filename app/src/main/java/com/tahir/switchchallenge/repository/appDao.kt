package com.tahir.switchchallenge.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tahir.switchchallenge.models.Payments

@Dao
interface appDao {
    // inserting payment record ignoring the one which are already there for the sake of simplicity.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertpayment(payment: Payments): Long

    //getting all the inserted appartments ordered by distance in ascending  order
    @Query("SELECT * from Payments where isActive=1 AND refundRequested=0  ORDER BY id  DESC")
    suspend fun getAllActivePayments(): List<Payments>
}