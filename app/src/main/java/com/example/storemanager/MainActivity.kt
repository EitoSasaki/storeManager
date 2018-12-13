package com.example.storemanager

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var stockQuantity : Int = 0
    private var currentTime = "00:00:00"
    private var comment = ""

    private var quantityTextView : TextView? = null
    private var plusButton : Button? = null
    private var minusButton : Button? = null
    private var currentTimerView : TextView? = null
    private var commentEditText : EditText? = null
    private var addButton : Button? = null
    private var stockListView : ListView? = null

    private val timerHandler = Handler()
    private val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.US)

    //在庫ダミーデータ
    private val stockList : List<Stock> = listOf(
        Stock("11:40:23", 300, "キャベツ"),
        Stock("12:56:21", 30, "にんじん"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        val storeManagerDB = StoreManagerDatabase(this)
        readDB(storeManagerDB)

        plusButton?.setOnClickListener {
            increaseStock()
        }

        minusButton?.setOnClickListener {
            decreaseStock()
        }

        timer ("currentTime", period = 1000) {
            setCurrentTime()
        }

        addButton?.setOnClickListener {
            setComment()
            storeManagerDB.addStock(currentTime, stockQuantity, comment)
            readDB(storeManagerDB)
        }

    }

    private fun readDB(db: StoreManagerDatabase) {
        if(db.getStock().columnCount != 0) {
            val stockListAdapter = StockListAdapter(applicationContext)
            stockListAdapter.stockList = stockList
            stockListView?.adapter = stockListAdapter
        }
    }

    private fun initView() {
        quantityTextView = checkNotNull(findViewById(R.id.quantityValue))
        plusButton = checkNotNull(findViewById(R.id.plusButton))
        minusButton = checkNotNull(findViewById(R.id.minusButton))
        currentTimerView = checkNotNull(findViewById(R.id.currentTimer))
        commentEditText = checkNotNull(findViewById(R.id.inputComment))
        addButton = checkNotNull(findViewById(R.id.addButton))
        stockListView = checkNotNull(findViewById(R.id.stockList))
    }

    private fun increaseStock() {
        if (stockQuantity < 9999) {
            stockQuantity++
            quantityTextView?.text = formatStockQuantity(stockQuantity)
        }
    }

    private fun decreaseStock() {
        if (stockQuantity > 0) {
            stockQuantity--
            quantityTextView?.text = formatStockQuantity(stockQuantity)
        }
    }

    private fun setCurrentTime() {
        val calendar = Calendar.getInstance()
        timerHandler.post {
            currentTime = dateFormat.format(calendar.time)
            currentTimerView?.text = currentTime
        }
    }

    private fun setComment() {
        comment = commentEditText?.text.toString()
        if(comment == ""){
            comment = "未入力"
        }
    }

    private fun formatStockQuantity(stockQuantity: Int): String {
        return NumberFormat.getNumberInstance().format(stockQuantity.toLong())
    }

}
