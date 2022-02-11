package com.tahir.paymentApp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Refunds")

data class Refunds(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var paymentId: Int?,
    var status: String?,
    var amount: Double?,
    var type: String?,
    @ColumnInfo(name = "isActive", defaultValue = "0") var isActive: Boolean,
    var addOn: String? = null,

    )
