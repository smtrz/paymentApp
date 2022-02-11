package com.tahir.paymentApp.activities

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.highbryds.washer.Helpers.UIHelper
import com.tahir.paymentApp.Helpers.DigitsInputFilter
import com.tahir.paymentApp.Helpers.GeneralHelpers
import com.tahir.paymentApp.R
import com.tahir.paymentApp.extensions.ExtensionFunctions.afterTextChanged
import com.tahir.paymentApp.extensions.obtainViewModel
import com.tahir.paymentApp.generics.DataState
import com.tahir.paymentApp.models.Payments
import com.tahir.paymentApp.utils.Validations
import com.tahir.paymentApp.viewmodels.PaymentViewModel
import kotlinx.android.synthetic.main.paynow.*


/**
 * [author] by `Tahir Raza`
 * [created] on 25/01/2022
 *
 * Activity => PayNow
 */
class PayNow : AppCompatActivity(), View.OnClickListener {
    lateinit var payNowViewModel: PaymentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paynow)
        init()
    }

    /*
    function that initializes the views,setup Ontextchangelistener and obtain view model
     */
    private fun init() {
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
        amount.setFilters(arrayOf<InputFilter>(DigitsInputFilter(8, 2)))

    }

    /*
    Subscribe function that subscribes to the livedata event of payment submission.
    */
    fun subscribe() {
        payNowViewModel.paymentObx.observe(this) { dataState ->
            when (dataState) {


                is DataState.Success<Long?> -> {

                    dataState.data?.let { it1 ->
                        // if the row is inserted successfully.
                        if (it1 > 0) {

                            UIHelper.showShortToastInCenter(
                                this,
                                "You have successfully paid the amount."
                            )
                            finish()
                        } else {
                            // in case of error in insertion.
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

    /*
    OnClick events : validating and then submit => submitpayment
     */
    override fun onClick(view: View?) {

        if (cc.error != null || expiry.error != null || card_name.error != null || cvv.error != null) {

            return
        }
        if (amount.text.isEmpty() || cc.text.isEmpty() || expiry.text.isEmpty() || card_name.text.isEmpty() || cvv.text.isEmpty()) {

            UIHelper.showShortToastInCenter(this, "please fill in the complete form.")
            return
        }

        // call database insertion functions from here.
        payNowViewModel.submitPayment(
            Payments(
                amount = amount.text.toString().toDouble(),
                purpose = "Bought MacBook Pro",
                currency = "USD",
                paymentDateTime = GeneralHelpers.getCurrentDateTime()
            )
        )

    }


}