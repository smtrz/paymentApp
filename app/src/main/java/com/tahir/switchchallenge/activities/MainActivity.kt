package com.tahir.switchchallenge.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tahir.switchchallenge.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() {
        payment.setOnClickListener(this)
        paymenthistory.setOnClickListener(this)
        refund.setOnClickListener(this)

    }

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