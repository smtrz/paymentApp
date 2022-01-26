package com.tahir.switchchallenge.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Refunds")

data class Refunds(
    @PrimaryKey val id: String,
    var paymentId: Int?,
    var status: Double?,
    @ColumnInfo(name = "isActive", defaultValue = "0") var isActive: Boolean,
    var addOn: String? = null,

    )
