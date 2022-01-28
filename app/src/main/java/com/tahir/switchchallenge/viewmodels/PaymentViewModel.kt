package com.tahir.switchchallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahir.switchchallenge.generics.DataState
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.models.Refunds
import com.tahir.switchchallenge.repository.DbRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * [author] by `Tahir Raza`
 * [created] on 25/01/2022
 *
 * Payment ViewModel
 */
class PaymentViewModel : ViewModel() {
    // private variables and getters.
    private val _payments: MutableLiveData<DataState<Long>> =
        MutableLiveData()
    val paymentObx: MutableLiveData<DataState<Long>>
        get() = _payments
    private val _refpayments: MutableLiveData<DataState<Long>> =
        MutableLiveData()

    val refundSum: MutableLiveData<DataState<Long>> = MutableLiveData()

    /*
    getting the refund sum from db
     */
    fun getRefundSum(refund: Refunds, payment: Payments) {
        viewModelScope.launch(Dispatchers.IO) {

            DbRepo.getRefundSum(refund, payment)
                .onEach { result ->

                    refundSum.postValue(result)
                }.collect()

        }

    }

    /*
    Getting all the payments posted.
     */
    fun getAllPayments(): LiveData<List<Payments>> {

        return DbRepo.getPaymentsfromDb()

    }

    /*
    Submitting the payment into the database.
     */
    fun submitPayment(payment: Payments) {
        viewModelScope.launch(Dispatchers.IO) {

            DbRepo.payNow(payment)
                .onEach { result ->
                    _payments.postValue(result)
                }.collect()

        }

    }

    /*
    Submit refund
     */
    fun submitRefunds(refunds: Refunds) {
        viewModelScope.launch(Dispatchers.IO) {

            DbRepo.refundNow(refunds)
                .onEach { result ->
                    _refpayments.postValue(result)
                }.collect()

        }

    }

    /*
    Get all refunds.
     */
    fun getAllRefunds(): LiveData<List<Refunds>> {

        return DbRepo.getallRefunds()

    }
}
