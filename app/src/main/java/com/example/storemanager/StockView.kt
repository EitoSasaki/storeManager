package com.example.storemanager

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView

class StockView : FrameLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    var timeTextView : TextView? = null

    var quantityTextView : TextView? = null

    var commentTextView : TextView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_store, this)
        timeTextView = findViewById(R.id.listTime)
        quantityTextView = findViewById(R.id.listQuantity)
        commentTextView = findViewById(R.id.listComment)
    }

    fun setStock(stock: Stock) {
        timeTextView?.text = stock.time
        quantityTextView?.text = stock.quantity.toString()
        commentTextView?.text = stock.comment
    }

}