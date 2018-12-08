package com.example.storemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val store = Store("11:40:23", "300", "キャベツ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storeListAdapter = StoreListAdapter(applicationContext)
        storeListAdapter.storeList = listOf(store)

        val storeListView : ListView = findViewById(R.id.storeList) as ListView
        storeListView.adapter = storeListAdapter

    }
}
