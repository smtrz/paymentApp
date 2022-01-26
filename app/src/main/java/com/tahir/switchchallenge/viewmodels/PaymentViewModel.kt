package com.tahir.switchchallenge.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tahir.switchchallenge.generics.DataState
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.repository.DbRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {

    private val _payments: MutableLiveData<DataState<Long>> =
        MutableLiveData()
    val paymentObx: MutableLiveData<DataState<Long>>
        get() = _payments


    init {
        Log.d("##", "Add PaymentViewModel called.")

    }

    fun submitPayment(payment: Payments) {
        viewModelScope.launch(Dispatchers.IO) {

            DbRepo.payNow(payment)
                .onEach { result ->
                    _payments.postValue(result)
                }.collect()

        }

    }


}
