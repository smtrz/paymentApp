package com.tahir.switchchallenge.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Payments")

data class Payments(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var amount: Int?,
    var purpose: String?,
    var currency: String?,
    @ColumnInfo(name = "status", defaultValue = "1") var status: String = "1",

    @ColumnInfo(name = "isActive", defaultValue = "1") var isActive: Boolean = true,
    var paymentDateTime: String? = null,
    @ColumnInfo(name = "refundRequested", defaultValue = "0") var refundRequested: Boolean = false,

    )
