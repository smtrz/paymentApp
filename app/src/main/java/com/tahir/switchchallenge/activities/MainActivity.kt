package com.tahir.switchchallenge.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tahir.switchchallenge.R
import com.tahir.switchchallenge.utils.Validations

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paynow)

        Log.d("##expiry", "==> " + Validations.validateCardExpiryDate("04/21"))
    }
}