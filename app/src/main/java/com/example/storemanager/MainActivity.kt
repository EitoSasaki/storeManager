package com.example.storemanager

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var amount : Int = 0
    private var currentTime : String = "00:00:00"
    private val timerHandler = Handler()
    private val dateFormat = SimpleDateFormat("HH:mm:ss")

    //在庫ダミーデータ
    private val store = Store("11:40:23", "300", "キャベツ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountTextView : TextView = findViewById(R.id.amountValue) as TextView

        val plusButton : Button = findViewById(R.id.plusButton) as Button
        plusButton.setOnClickListener {
            if (amount < 9999) {
                amount++
                amountTextView.text = formatNumber(amount)
            }
        }

        val minusButton : Button = findViewById(R.id.minusButton) as Button
        minusButton.setOnClickListener {
            if (amount > 0) {
                amount--
                amountTextView.text = formatNumber(amount)
            }
        }

        val currentTimerView = findViewById(R.id.currentTimer) as TextView
        timer ("currentTime", period = 1000) {
            val calendar = Calendar.getInstance()
            timerHandler.post {
                currentTime = dateFormat.format(calendar.getTime())
                currentTimerView.text = currentTime
            }
        }

        val storeListAdapter = StoreListAdapter(applicationContext)
        storeListAdapter.storeList = listOf(store)

        val storeListView : ListView = findViewById(R.id.storeList) as ListView
        storeListView.adapter = storeListAdapter

    }

    private fun formatNumber(value: Int): String {
        return NumberFormat.getNumberInstance().format(value.toLong())
    }

}
