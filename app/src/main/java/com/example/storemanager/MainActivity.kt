package com.example.storemanager

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
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
    private var stockListAdapter : StockListAdapter? = null
    private var clearButton : Button? = null

    private val timerHandler = Handler()
    private val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        val storeManagerDB = StoreManagerDatabase(this)
        stockListAdapter = StockListAdapter(applicationContext)

        loadStockList(storeManagerDB)

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
            loadStockList(storeManagerDB)
        }

        stockListView?.setOnItemClickListener { _: AdapterView<*>, _: View, position: Int, id: Long ->
            if (id.toInt() == R.id.deleteStockButton) {
                var stockIDs : Array<String> = arrayOf(stockListAdapter?.stockList?.get(position)?.id.toString())
                storeManagerDB.deleteStock(stockIDs)
                loadStockList(storeManagerDB)
            }
        }

        clearButton?.setOnClickListener {
            storeManagerDB.deleteAllStock()
            loadStockList(storeManagerDB)
        }

    }

    private fun loadStockList(db: StoreManagerDatabase) {
        if (db.getStockCount() > 0) {
            stockListAdapter?.stockList = db.readStock(db.getStock())
        } else {
            stockListAdapter?.stockList = listOf()
        }
        stockListView?.adapter = stockListAdapter

    }

    private fun initView() {
        quantityTextView = checkNotNull(findViewById(R.id.quantityValue))
        plusButton = checkNotNull(findViewById(R.id.plusButton))
        minusButton = checkNotNull(findViewById(R.id.minusButton))
        currentTimerView = checkNotNull(findViewById(R.id.currentTimer))
        commentEditText = checkNotNull(findViewById(R.id.inputComment))
        addButton = checkNotNull(findViewById(R.id.addButton))
        stockListView = checkNotNull(findViewById(R.id.stockList))
        clearButton = checkNotNull(findViewById(R.id.clearButton))
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
