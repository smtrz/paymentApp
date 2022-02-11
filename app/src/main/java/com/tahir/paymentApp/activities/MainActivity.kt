package com.tahir.paymentApp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tahir.paymentApp.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * [author] by `Tahir Raza`
 * [created] on 25/01/2022
 *
 * Activity => MainActivity
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    /*
    the entry point function for initializations.
     */
    fun init() {
        payment.setOnClickListener(this)
        paymenthistory.setOnClickListener(this)
        refund.setOnClickListener(this)

    }

    /*
    OnClick event that handles the activation navigations on card clicks.
     */
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.payment -> {
                var i = Intent(this, PayNow::class.java)
                startActivity(i)

            }
            R.id.paymenthistory -> {
                var i = Intent(this, Payments::class.java)
                startActivity(i)

            }
            R.id.refund -> {
                var i = Intent(this, RefundList::class.java)
                startActivity(i)

            }
        }


    }
}