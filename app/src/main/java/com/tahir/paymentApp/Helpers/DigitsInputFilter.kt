package com.tahir.paymentApp.Helpers

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 *  setting up the validation for the amounts.
 */
class DigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) : InputFilter {

    var mPattern: Pattern

    init {
        mPattern =
            Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
    }


    override fun filter(
        p0: CharSequence?,
        p1: Int,
        p2: Int,
        p3: Spanned?,
        p4: Int,
        p5: Int
    ): CharSequence? {
        val matcher: Matcher = mPattern.matcher(p3)
        if (!matcher.matches())
            return "";
        return null;
    }

}