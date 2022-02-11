package com.tahir.paymentApp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahir.paymentApp.R
import com.tahir.paymentApp.adapters.BaseRecyclerViewAdapter
import com.tahir.paymentApp.adapters.PaymentListAdapter
import com.tahir.paymentApp.extensions.obtainViewModel
import com.tahir.paymentApp.models.Payments
import com.tahir.paymentApp.viewmodels.PaymentViewModel
import kotlinx.android.synthetic.main.payment_list.*

/**
 * [author] by `Tahir Raza`
 * [created] on 25/01/2022
 *
 * Activity => Payments
 */
class Payments : AppCompatActivity(), BaseRecyclerViewAdapter.OnItemClickListener<Payments> {
    lateinit var payNowViewModel: PaymentViewModel
    lateinit var paymentAdapter: PaymentListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_list)
        init()
    }

    /*
       the entry point function for initializations.
        */
    fun init() {
        paymentAdapter = this?.let { PaymentListAdapter() }!!
        paymentAdapter.setOnItemClickListener(this)
        payNowViewModel = obtainViewModel(PaymentViewModel::class.java)
        payNowViewModel.getAllPayments()
        subscribe()

    }

    /*
       Setup payment list adapter.
        */
    fun setupListAdapter(arrayList: List<Payments>) {
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_payments.layoutManager = mLayoutManager
        rv_payments.adapter = paymentAdapter
        arrayList?.let { paymentAdapter.addItems(it as ArrayList<Payments>) }
        paymentAdapter.notifyDataSetChanged()
        noDataFound()
    }

    /*
      Subscribe to the events
        */
    fun subscribe() {

        payNowViewModel.getAllPayments().observe(
            this
        ) {

            paymentAdapter.clear()
            setupListAdapter(it)
        }


    }

    /*
       no data found for the list
        */
    private fun noDataFound() {
        if (paymentAdapter.getList().isEmpty() || paymentAdapter.getList() == null) {
            imgNoDataFound.visibility = View.VISIBLE
            nodata_txtview.visibility = View.VISIBLE
        } else {
            imgNoDataFound.visibility = View.GONE
            nodata_txtview.visibility = View.GONE

        }
    }

    /*
       onItemClick event - for click events of payments
        */
    override fun onItemClick(listitem: Payments, position: Int, view: View?) {
        when (view?.id) {
            R.id.refund -> {
                // open refund request activity and pass the payment data.
                val intent = Intent(this, RefundRequest::class.java)
                intent.putExtra("payment_data", listitem)
                startActivity(intent)

            }
        }
    }
}