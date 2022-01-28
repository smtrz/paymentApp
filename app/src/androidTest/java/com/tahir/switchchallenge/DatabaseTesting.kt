package com.tahir.switchchallenge

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.tahir.switchchallenge.Helpers.GeneralHelpers
import com.tahir.switchchallenge.generics.DataState
import com.tahir.switchchallenge.models.Payments
import com.tahir.switchchallenge.models.Refunds
import com.tahir.switchchallenge.repository.AppDB
import com.tahir.switchchallenge.repository.appDao
import com.tahir.switchchallenge.viewmodels.PaymentViewModel
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTesting {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var db: AppDB
    private lateinit var dao: appDao
    lateinit var pVM: PaymentViewModel
    private lateinit var data_observer: Observer<List<Payments>>
    private lateinit var refund_data_observer: Observer<DataState<Long>>


    @Before
    fun setUp() {
        pVM = PaymentViewModel()
        // setting up in memory database
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDB::class.java
        ).build()
        // getting data access object from the database created.
        dao = db.appDao()
        data_observer = Observer<List<Payments>> {}
        refund_data_observer = Observer<DataState<Long>> {}

    }


    @Test
    fun testInsertPayment() {
        Assert.assertTrue(insertPaymentData() > 0)

    }


    fun insertPaymentData(): Long {
        // inserting sample payment object to the table.
        var data_insertion: Long
        val sample_payment = Payments(
            amount = GeneralHelpers.generateRadomAmount().toDouble(),
            purpose = "Bought MacBook Pro",
            currency = "USD",
            paymentDateTime = GeneralHelpers.getCurrentDateTime()
        )


        runBlocking {
            data_insertion = dao.insertpayment(sample_payment)

        }
        return data_insertion

    }

    @Test
    fun getPaymentsfromDb() {
        pVM.getAllPayments().observeForever(data_observer)
        data_observer = Observer { it: List<Payments> ->
            run {
                Assert.assertTrue(it.size > 0)
            }

        }


    }

    @Test
    fun submitRefund() {


        val sample_payment = Payments(
            id = 4,
            amount = 650.0,
            purpose = "Bought MacBook Pro",
            currency = "USD",
            paymentDateTime = "28/1/2022 02:24:03"
        )

        val sample_refund = Refunds(
            amount = 5.0,
            type = "partial",
            status = "refund requested",
            paymentId = 4,
            isActive = true,
            addOn = GeneralHelpers.getCurrentDateTime()
        )
        pVM.getRefundSum(sample_refund, sample_payment)

        refund_data_observer = Observer<DataState<Long>> { datastate ->
            when (datastate) {
                is DataState.Success<Long> -> {
                    Assert.assertTrue(datastate.data > 0)

                }
                is DataState.Error -> {
                    Assert.assertTrue(false)


                }
            }

        }
        pVM.refundSum.observeForever(refund_data_observer)

    }

    @After
    fun tearDown() {
        // closing the database after it has been used.
        db.close()
        // removing the observer afterwards
        pVM.getAllPayments().removeObserver(data_observer)
        pVM.refundSum.removeObserver(refund_data_observer)

    }
}