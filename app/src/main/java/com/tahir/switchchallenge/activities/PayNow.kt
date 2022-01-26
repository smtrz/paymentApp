package com.tahir.switchchallenge.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.highbryds.washer.Helpers.UIHelper
import com.tahir.switchchallenge.Helpers.GeneralHelpers
import com.tahir.switchchallenge.R
import com.tahir.switchchallenge.extensions.ExtensionFunctions.afterTextChanged
import com.tahir.switchchallenge.extensions.obtainViewModel
import com.tahir.switchchallenge.generics.DataState
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.utils.Validations
import com.tahir.switchchallenge.viewmodels.PaymentViewModel
import kotlinx.android.synthetic.main.paynow.*

class PayNow : AppCompatActivity(), View.OnClickListener {
    lateinit var payNowViewModel: PaymentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paynow)
        init()
    }

    fun init() {
        payNowViewModel = obtainViewModel(PaymentViewModel::class.java)
        subscribe()
        pay_now.setOnClickListener(this)
        cc.afterTextChanged {


            if (it.length == 16) {

                if (!Validations.validateCardNumber(it)) {
                    cc.setError("Credit card number is not valid.")

                } else {
                    cc.setError(null)

                }

            } else if (it.length < 16) {
                cc.setError("Credit card number should not be less 16 digits.")


            } else {

                cc.setError(null)

            }
        }
        expiry.afterTextChanged {
            if (it.length > 2) {
                if (!Validations.validateCardExpiryDate(it)) {
                    expiry.setError(null)


                } else {
                    expiry.setError("please make sure your card is valid.")

                }


            }
        }
        card_name.afterTextChanged {
            if (it.length == 0) {

                card_name.setError("Name cannot be left blank")
            } else {
                card_name.setError(null)

            }


        }
        cvv.afterTextChanged {
            if (it.length < 3) {
                cvv.setError("please enter a valid cvv pin.")


            } else {
                expiry.setError(null)

            }


        }
    }


    fun subscribe() {
        payNowViewModel.paymentObx.observe(this) { dataState ->
            when (dataState) {


                is DataState.Success<Long?> -> {

                    dataState.data?.let { it1 ->
                        if (it1 > 0) {

                            UIHelper.showShortToastInCenter(
                                this,
                                "You have successfully paid the amount."
                            )
                            finish()
                        } else {
                            UIHelper.showShortToastInCenter(this, "Payment was not successful.")


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

        if (cc.error != null || expiry.error != null || card_name.error != null || cvv.error != null) {

            return
        }
        if (cc.text.length == 0 || expiry.text.length == 0 || card_name.text.length == 0 || cvv.text.length == 0) {

            UIHelper.showShortToastInCenter(this, "please fill in the complete form.")
            return
        }

        // call database insertion functions from here.
        payNowViewModel.submitPayment(
            Payments(
                amount = GeneralHelpers.generateRadomAmount().toDouble(),
                purpose = "Bought MacBook Pro",
                currency = "USD",
                paymentDateTime = GeneralHelpers.getCurrentDateTime()
            )
        )

    }


}