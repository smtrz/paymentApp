package com.tahir.switchchallenge.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tahir.switchchallenge.R
import com.tahir.switchchallenge.models.Refunds
import kotlinx.android.synthetic.main.payment_row_item.view.*

class RefundListAdapter() :
    BaseRecyclerViewAdapter<Refunds>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.refund_row_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myHolder = holder as? MyViewHolder
        myHolder?.setUpView(getItem(position))
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var model: Refunds? = null

        init {
            view.setOnClickListener(this)


        }

        private val txtamount: TextView = view.txtamount
        private val paymentno: TextView = view.paymentno
        private val date_time: TextView = view.date_time
        private val payment_status: TextView = view.payment_status


        fun setUpView(refund: Refunds?) {
            model = refund

            txtamount.text = refund?.amount.toString() + " USD"
            paymentno.text = "Payment No: " + refund?.paymentId.toString()
            date_time.text = refund?.addOn
            payment_status.text = refund?.type


        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }


    }


}