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
    private var currentTime : String = "00:00:00"
    private var comment = ""

    private var quantityTextView : TextView? = null
    private val plusButton : Button? = null
    private var minusButton : Button? = null
    private var currentTimerView : TextView? = null
    private var commentEditText : EditText? = null
    private var addButton : Button? = null

    private val timerHandler = Handler()
    private val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.US)

    //在庫ダミーデータ
    private val stockList : List<Stock> = listOf(
        Stock("11:40:23", 300, "キャベツ"),
        Stock("12:56:21", 30, "にんじん"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storeManagerDB = StoreManagerDatabase(this)

        initView()

        plusButton?.setOnClickListener {
            if (stockQuantity < 9999) {
                stockQuantity++
                quantityTextView?.text = formatQuantityValue(stockQuantity)
            }
        }

        minusButton?.setOnClickListener {
            if (stockQuantity > 0) {
                stockQuantity--
                quantityTextView?.text = formatQuantityValue(stockQuantity)
            }
        }

        timer ("currentTime", period = 1000) {
            val calendar = Calendar.getInstance()
            timerHandler.post {
                currentTime = dateFormat.format(calendar.getTime())
                currentTimerView?.text = currentTime
            }
        }

        addButton?.setOnClickListener {
            comment = commentEditText?.text.toString()
            if(comment == ""){
                comment = "未入力"
            }
            storeManagerDB.addStock(currentTime, stockQuantity, comment)
        }

        val stockListAdapter = StockListAdapter(applicationContext)
        stockListAdapter.stockList = stockList

        val stockListView : ListView = findViewById(R.id.stockList) as ListView
        stockListView.adapter = stockListAdapter

    }

    private fun initView() {
        quantityTextView = checkNotNull(findViewById(R.id.quantityValue))
        minusButton = checkNotNull(findViewById(R.id.minusButton))
        currentTimerView = checkNotNull(findViewById(R.id.currentTimer))
        commentEditText = checkNotNull(findViewById(R.id.inputComment))
        addButton = checkNotNull(findViewById(R.id.addButton))
    }

    private fun formatQuantityValue(stockQuantity: Int): String {
        return NumberFormat.getNumberInstance().format(stockQuantity.toLong())
    }

}
