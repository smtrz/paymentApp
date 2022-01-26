package com.tahir.switchchallenge.repository

import com.tahir.switchchallenge.config.AppConfig
import com.tahir.switchchallenge.generics.DataState
import com.tahir.switchchallenge.models.Payments
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DbRepo {


    suspend fun payNow(
        payment: Payments
    ): Flow<DataState<Long>> =

        flow {
            emit(DataState.Loading)
            try {
                // inserting new payment record into the database.
                var result = AppConfig.appDao.insertpayment(payment)
                //emitting the data of all the payments.
                emit(DataState.Success(result))
            } catch (e: Exception) {
                emit(DataState.Error(e.message.toString()))
            }
        }

}