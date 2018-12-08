package com.example.storemanager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class StoreListAdapter(private val context: Context) : BaseAdapter() {

    var storeList: List<Store> = emptyList()

    override fun getCount(): Int = storeList.size

    override fun getItemId(position: Int): Long = 0

    override fun getItem(position: Int): Any = storeList[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}