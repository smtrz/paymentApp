package com.tahir.switchchallenge.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tahir.switchchallenge.generics.ViewModelFactory

/**
 * [author] by `Tahir Raza`
 * [created] on 25/01/2022
 *
 * Activity Extension functions
 */
fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this, ViewModelFactory.getInstance()).get(viewModelClass)



