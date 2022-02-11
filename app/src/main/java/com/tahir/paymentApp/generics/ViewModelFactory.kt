package com.tahir.paymentApp.generics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tahir.paymentApp.viewmodels.PaymentViewModel

/**
 * [author] by `Tahir Raza`
 * [created] on 25/01/2022
 *
 * View Model Factory
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    /**
     * factory method to get view model
     *
     * [modelClass] of which the class object for which the view model requested
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaymentViewModel::class.java)) {
            return PaymentViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }

    companion object {

        /**
         * instance method for this factory
         */
        @Volatile
        private var instance: ViewModelFactory? = null

        /**
         * singleton instance method for this factory
         */
        fun getInstance() =
            instance
                ?: synchronized(ViewModelFactory::class.java) {
                    instance
                        ?: ViewModelFactory()
                            .also { instance = it }
                }
    }
}