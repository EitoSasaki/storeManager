package com.example.storemanager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class StockListAdapter(private val context: Context) : BaseAdapter() {

    var stockList: List<Stock> = emptyList()

    override fun getCount(): Int = stockList.size

    override fun getItemId(position: Int): Long = 0

    override fun getItem(position: Int): Stock? = stockList.get(position)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
        ((convertView as? StockView) ?: StockView(context)).apply {
            setStock(stockList.get(position))
        }

}