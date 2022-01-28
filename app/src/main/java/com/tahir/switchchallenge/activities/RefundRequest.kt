package com.tahir.switchchallenge.activities

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.highbryds.washer.Helpers.UIHelper
import com.tahir.switchchallenge.Helpers.DigitsInputFilter
import com.tahir.switchchallenge.Helpers.GeneralHelpers
import com.tahir.switchchallenge.R
import com.tahir.switchchallenge.extensions.obtainViewModel
import com.tahir.switchchallenge.generics.DataState
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.models.Refunds
import com.tahir.switchchallenge.viewmodels.PaymentViewModel
import kotlinx.android.synthetic.main.paynow.amount
import kotlinx.android.synthetic.main.paynow.progress_bar
import kotlinx.android.synthetic.main.refund.*

class RefundRequest : AppCompatActivity(), View.OnClickListener {
    lateinit var payNowViewModel: PaymentViewModel
    lateinit var payment_data: Payments
    var amount_allowed: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.refund)
        init()
    }

    fun init() {
        payment_data = intent.getParcelableExtra("payment_data")!!
        amount_allowed = payment_data.amount!!
        payNowViewModel = obtainViewModel(PaymentViewModel::class.java)
        refund_now.setOnClickListener(this)
        amount.setFilters(arrayOf<InputFilter>(DigitsInputFilter(8, 2)))
        getSumofRefunds()
    }


    fun getSumofRefunds() {
        payNowViewModel.refundSum.observe(this) { dataState ->
            when (dataState) {


                is DataState.Success<Long?> -> {

                    if (dataState.data != null) {

                        if (dataState.data > 0) {
                            UIHelper.showShortToastInCenter(this, "Refund posted successfully.")
                            finish()
                        }
                    }



                    GeneralHelpers.displayProgressBar(false, progress_bar, this)

                }
                is DataState.Error -> {
                    GeneralHelpers.displayProgressBar(false, progress_bar, this)
                    GeneralHelpers.displayError(dataState.exception, this)
                }
                is DataState.Loading -> {
                    GeneralHelpers.displayProgressBar(true, progress_bar, this)
                }
            }

        }


    }

    override fun onClick(view: View?) {
        if (amount.text.isEmpty()) {

            UIHelper.showShortToastInCenter(this, "Amount can't be left blank.")
            return
        }
        if (!amount.text.equals("")) {
            var refundType = ""
            if (amount.text.toString().toDouble() < payment_data.amount!!) {
                refundType = "Partial"

            } else if (amount.text.toString().toDouble() == payment_data.amount) {
                refundType = "Full"

            }
            var refund = Refunds(
                amount = amount.text.toString().toDouble(),
                paymentId = payment_data.id,
                addOn = GeneralHelpers.getCurrentDateTime(),
                isActive = true,
                status = "request submitted.",
                type = refundType


            )
            payNowViewModel.getRefundSum(refund, payment_data)
        }


    }


}