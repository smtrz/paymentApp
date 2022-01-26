package com.tahir.switchchallenge.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object Validations {
    fun validateCardNumber(ccNumber: String): Boolean {
        var isTrue = false
        var ptVisa = "^4[0-9]{6,}$"
        var ptMasterCard = "^5[1-5][0-9]{5,}$"
        val listOfPattern = ArrayList<String>()
        listOfPattern.add(ptVisa)
        listOfPattern.add(ptMasterCard)
        for (card in listOfPattern) {
            if (ccNumber.matches(card.toRegex())) {
                isTrue = true

                break
            }
        }
        return isTrue

    }

    fun validateCardExpiryDate(expiryDate: String): Boolean {
        var isTrue: Boolean;
        try {
            val simpleDateFormat = SimpleDateFormat("MM/yy")
            simpleDateFormat.setLenient(false)
            val expiry: Date = simpleDateFormat.parse(expiryDate)
            isTrue = expiry.before(Date())
        } catch (e: Exception) {
            isTrue = false;

        }
        return isTrue


    }

}