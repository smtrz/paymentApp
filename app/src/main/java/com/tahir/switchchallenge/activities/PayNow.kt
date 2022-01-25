package com.tahir.switchchallenge.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.tahir.switchchallenge.R
import com.tahir.switchchallenge.utils.Validations
import kotlinx.android.synthetic.main.paynow.*

class PayNow : AppCompatActivity(), TextWatcher {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paynow)
        init()
    }

    fun init() {

        cc.addTextChangedListener(this)

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun afterTextChanged(p0: Editable?) {

        if (!Validations.validateCardNumber(p0.toString())) {
            cc.setError("Credit card number is not valid.")

        } else {
            cc.setError(null)

        }


    }
}