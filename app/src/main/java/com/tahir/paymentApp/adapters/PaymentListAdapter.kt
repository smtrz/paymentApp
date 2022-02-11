package com.tahir.paymentApp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tahir.paymentApp.R
import com.tahir.paymentApp.models.Payments
import kotlinx.android.synthetic.main.payment_row_item.view.*

class PaymentListAdapter() :
    BaseRecyclerViewAdapter<Payments>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.payment_row_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myHolder = holder as? MyViewHolder
        myHolder?.setUpView(getItem(position))
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var model: Payments? = null

        init {
            view.setOnClickListener(this)


        }

        private val txtamount: TextView = view.txtamount
        private val txtpurpose: TextView = view.paymentno
        private val date_time: TextView = view.date_time
        private val payment_status: TextView = view.payment_status
        private val refund: Button = view.refund


        fun setUpView(payments: Payments?) {
            model = payments

            txtamount.text = payments?.amount.toString() + " " + payments?.currency.toString()
            txtpurpose.text = payments?.purpose
            date_time.text = payments?.paymentDateTime
            if (payments?.status.equals("1")) {
                payment_status.text = "Paid Successfully"


            }





            refund.setOnClickListener(this)


        }


        override fun onClick(v: View?) {

            model?.let { itemClickListener.onItemClick(it, absoluteAdapterPosition, v) }
        }
    }


}