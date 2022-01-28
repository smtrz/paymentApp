package com.tahir.switchchallenge.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.models.Refunds

@Dao
interface appDao {
    // inserting payment record ignoring the one which are already there for the sake of simplicity.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertpayment(payment: Payments): Long

    //getting all the inserted appartments ordered by distance in ascending  order
    @Query("SELECT * from Payments where isActive=1 AND refundRequested=0  ORDER BY id  DESC")
    fun getAllActivePayments(): LiveData<List<Payments>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRefunds(refund: Refunds): Long

    // getting the sum of all the refunds that have been submitted up till now for a particular payment

    @Query("SELECT SUM(amount)  from Refunds where paymentId=:paymentid")
    suspend fun getRefundSum(paymentid: Int): Double?

    // getting all the active refunds.
    @Query("SELECT *  from Refunds where isActive=1")
    fun getAllRefunds(): LiveData<List<Refunds>>

}