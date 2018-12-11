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

    private var storeAmount : Int = 0
    private var currentTime : String = "00:00:00"
    private var comment = ""

    private val timerHandler = Handler()
    private val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.US)

    //在庫ダミーデータ
    private val storeList : List<Store> = listOf(
        Store("11:40:23", 300, "キャベツ"),
        Store("12:56:21", 30, "にんじん"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountTextView : TextView = findViewById(R.id.amountValue) as TextView
        val plusButton : Button = findViewById(R.id.plusButton) as Button
        val minusButton : Button = findViewById(R.id.minusButton) as Button
        val currentTimerView = findViewById(R.id.currentTimer) as TextView
        val commentEditText = findViewById(R.id.inputComment) as EditText
        val addButton : Button = findViewById(R.id.addButton) as Button

        val storeManagerDB = StoreManagerDatabase(this)


        plusButton.setOnClickListener {
            if (storeAmount < 9999) {
                storeAmount++
                amountTextView.text = formatAmountValue(storeAmount)
            }
        }

        minusButton.setOnClickListener {
            if (storeAmount > 0) {
                storeAmount--
                amountTextView.text = formatAmountValue(storeAmount)
            }
        }

        timer ("currentTime", period = 1000) {
            val calendar = Calendar.getInstance()
            timerHandler.post {
                currentTime = dateFormat.format(calendar.getTime())
                currentTimerView.text = currentTime
            }
        }

        addButton.setOnClickListener {
            comment = commentEditText.text.toString()
            if(comment == ""){
                comment = "未入力"
            }
            storeManagerDB.addStore(currentTime, storeAmount, comment)
        }

        val storeListAdapter = StoreListAdapter(applicationContext)
        storeListAdapter.storeList = storeList

        val storeListView : ListView = findViewById(R.id.storeList) as ListView
        storeListView.adapter = storeListAdapter

    }

    private fun formatAmountValue(storeAmount: Int): String {
        return NumberFormat.getNumberInstance().format(storeAmount.toLong())
    }

}
