package com.tahir.switchchallenge.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahir.switchchallenge.Helpers.GeneralHelpers
import com.tahir.switchchallenge.R
import com.tahir.switchchallenge.adapters.BaseRecyclerViewAdapter
import com.tahir.switchchallenge.adapters.PaymentListAdapter
import com.tahir.switchchallenge.extensions.obtainViewModel
import com.tahir.switchchallenge.generics.DataState
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.viewmodels.PaymentViewModel
import kotlinx.android.synthetic.main.payment_list.*

class Payments : AppCompatActivity(), BaseRecyclerViewAdapter.OnItemClickListener<Payments> {
    lateinit var payNowViewModel: PaymentViewModel
    lateinit var paymentAdapter: PaymentListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_list)
        init()
    }

    fun init() {
        paymentAdapter = this?.let { PaymentListAdapter() }!!
        paymentAdapter.setOnItemClickListener(this)
        payNowViewModel = obtainViewModel(PaymentViewModel::class.java)
        payNowViewModel.getAllPayments()
        subscribe()

    }

    fun setupListAdapter(arrayList: List<Payments>) {
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_payments.layoutManager = mLayoutManager
        rv_payments.adapter = paymentAdapter
        arrayList?.let { paymentAdapter.addItems(it as ArrayList<Payments>) }
        paymentAdapter.notifyDataSetChanged()
        noDataFound()
    }

    fun subscribe() {

        payNowViewModel.getAllPayments().observe(
            this
        ) {

            paymentAdapter.clear()
            setupListAdapter(it)
        }


    }

    private fun noDataFound() {
        if (paymentAdapter.getList().isEmpty() || paymentAdapter.getList() == null) {
            imgNoDataFound.visibility = View.VISIBLE
            nodata_txtview.visibility = View.VISIBLE
        } else {
            imgNoDataFound.visibility = View.GONE
            nodata_txtview.visibility = View.GONE

        }
    }

    override fun onItemClick(listitem: Payments, position: Int, view: View?) {
        when (view?.id) {
            R.id.refund -> {
// call refund request procedure.
                val intent = Intent(this, RefundRequest::class.java)
                intent.putExtra("payment_data", listitem)
                startActivity(intent)

            }
        }
    }
}