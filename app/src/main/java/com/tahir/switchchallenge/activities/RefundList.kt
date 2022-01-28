package com.tahir.switchchallenge.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahir.switchchallenge.R
import com.tahir.switchchallenge.adapters.RefundListAdapter
import com.tahir.switchchallenge.extensions.obtainViewModel
import com.tahir.switchchallenge.models.Refunds
import com.tahir.switchchallenge.viewmodels.PaymentViewModel
import kotlinx.android.synthetic.main.payment_list.imgNoDataFound
import kotlinx.android.synthetic.main.payment_list.nodata_txtview
import kotlinx.android.synthetic.main.refund_list.*

/**
 * [author] by `Tahir Raza`
 * [created] on 25/01/2022
 *
 * Activity => RefundList
 */
class RefundList : AppCompatActivity() {
    lateinit var payNowViewModel: PaymentViewModel
    lateinit var refundAdapter: RefundListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.refund_list)
        init()
    }

    /**
     *  function that initializes the views,obtain view model and getAllRefunds.
     */
    fun init() {
        refundAdapter = this?.let { RefundListAdapter() }!!
        payNowViewModel = obtainViewModel(PaymentViewModel::class.java)
        payNowViewModel.getAllRefunds()
        subscribe()

    }

    /**
     *  setup list for all the refunds.
     */
    fun setupListAdapter(arrayList: List<Refunds>) {
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_refunds.layoutManager = mLayoutManager
        rv_refunds.adapter = refundAdapter
        arrayList?.let { refundAdapter.addItems(it as ArrayList<Refunds>) }
        refundAdapter.notifyDataSetChanged()
        noDataFound()
    }

    /**
     *  subscribe to the refunds list.
     */
    fun subscribe() {

        payNowViewModel.getAllRefunds().observe(
            this
        ) {

            refundAdapter.clear()
            setupListAdapter(it)
        }


    }

    /**
     *  hide and show textviews on ondatafound.
     */
    private fun noDataFound() {
        if (refundAdapter.getList().isEmpty() || refundAdapter.getList() == null) {
            imgNoDataFound.visibility = View.VISIBLE
            nodata_txtview.visibility = View.VISIBLE
        } else {
            imgNoDataFound.visibility = View.GONE
            nodata_txtview.visibility = View.GONE

        }
    }


}