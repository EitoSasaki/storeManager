package com.example.storemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val store = Store("11:40:23", "300", "キャベツ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storeView = StoreView(applicationContext)

        storeView.setStore(store)

        setContentView(storeView)

    }
}
