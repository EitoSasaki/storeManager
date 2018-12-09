package com.example.storemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var amount : Int = 0

    //在庫ダミーデータ
    private val store = Store("11:40:23", "300", "キャベツ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountTextView : TextView = findViewById(R.id.amountValue) as TextView

        val plusButton : Button = findViewById(R.id.plusButton) as Button
        plusButton.setOnClickListener {
            //在庫が9999より大きくならないようにする
            if (amount < 9999) {
                amount++
                amountTextView.text = amount.toString()
            }

        }

        val minusButton : Button = findViewById(R.id.minusButton) as Button

        val storeListAdapter = StoreListAdapter(applicationContext)
        storeListAdapter.storeList = listOf(store)

        val storeListView : ListView = findViewById(R.id.storeList) as ListView
        storeListView.adapter = storeListAdapter

    }
}
