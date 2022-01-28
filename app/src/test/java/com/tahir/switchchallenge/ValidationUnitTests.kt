package com.tahir.switchchallenge

import com.tahir.switchchallenge.utils.Validations
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationUnitTests {
    @Test
    fun visaMasterCardValidator_ReturnsTrue() {
        // please enter a sixteen digits card number to validate
        var sys = Validations.validateCardNumber("4545454545454545")
        assertTrue(sys)
    }

    @Test
    fun cardExpiryValidator_ReturnsTrue() {
        // Will return true if the card is expired.
        assertTrue(Validations.validateCardExpiryDate("11/21"))
    }
}