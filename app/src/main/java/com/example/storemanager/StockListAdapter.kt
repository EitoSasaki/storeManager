package com.example.storemanager

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView


class StockListAdapter(private val context: Context) : BaseAdapter() {

    private val inflater: LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    var stockList: List<Stock> = emptyList()

    override fun getCount(): Int = stockList.size

    override fun getItemId(position: Int): Long = 0

    override fun getItem(position: Int): Stock? = stockList.get(position)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var holder : StockViewHolder
        var view = convertView

        if (view == null) {
            view = inflater?.inflate(R.layout.view_store, parent, false)
            holder = setStockView(view)
            view?.tag = holder
        } else {
            holder = view?.tag as StockViewHolder
        }

        //奇数行は背景色を変える
        if (position % 2 == 0) {
            view?.setBackgroundColor(Color.rgb(123, 232, 218))
        }

        holder.timeTextView?.text = stockList[position].time
        holder.quantityTextView?.text = stockList[position].quantity.toString()
        holder.commentTextView?.text = stockList[position].comment

        holder.deleteButton?.setOnClickListener{
            var parentView = parent as ListView
            parentView.performItemClick(view, position, R.id.deleteStockButton.toLong())
        }

        return view
    }

    private fun setStockView (view : View?): StockViewHolder {
        return StockViewHolder(
            view?.findViewById(R.id.listTime),
            view?.findViewById(R.id.listQuantity),
            view?.findViewById(R.id.listComment),
            view?.findViewById(R.id.deleteStockButton)
        )
    }

}