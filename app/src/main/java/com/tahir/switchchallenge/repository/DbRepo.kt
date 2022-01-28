package com.tahir.switchchallenge.repository

import androidx.lifecycle.LiveData
import com.tahir.switchchallenge.config.AppConfig
import com.tahir.switchchallenge.generics.DataState
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.models.Refunds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DbRepo {
    /**
     *  Paynow , inserts the payment data into the database.
     */
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

    /**
     *  get all the active payments from database.
     */
    fun getPaymentsfromDb(): LiveData<List<Payments>> {

        return AppConfig.appDao.getAllActivePayments()


    }

    /**
     *  get sum of refunds for a particular payment.
     */
    suspend fun getRefundSum(
        refund: Refunds, payment: Payments
    ): Flow<DataState<Long>> =

        flow {
            emit(DataState.Loading)
            try {
                // Getting the sum of refund from the payment id..
                var result = AppConfig.appDao.getRefundSum(payment.id)
                var amount_allowed = 0.0
                if (result == null) {
                    amount_allowed = payment.amount!! - 0.0
                } else {

                    amount_allowed = result?.let { payment.amount?.minus(it) }!!

                }

                if (refund.amount!! <= amount_allowed!!) {
                    var result = AppConfig.appDao.insertRefunds(refund)
                    emit(DataState.Success(result))


                } else {

                    emit(DataState.Error("You cannot exceed the payment amount"))
                }


            } catch (e: Exception) {
                emit(DataState.Error(e.message.toString()))
            }
        }

    /**
     *  insert the refund into database.
     */
    suspend fun refundNow(
        refunds: Refunds
    ): Flow<DataState<Long>> =

        flow {
            emit(DataState.Loading)
            try {
                // inserting new payment record into the database.
                var result = AppConfig.appDao.insertRefunds(refunds)
                //emitting the data of all the payments.
                emit(DataState.Success(result))
            } catch (e: Exception) {
                emit(DataState.Error(e.message.toString()))
            }
        }

    /**
     *  getting all the refunds from database
     */
    fun getallRefunds(): LiveData<List<Refunds>> {

        return AppConfig.appDao.getAllRefunds()


    }
}