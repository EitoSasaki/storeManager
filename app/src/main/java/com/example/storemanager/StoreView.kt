package com.example.storemanager

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView

class StoreView : FrameLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    var timeTextView : TextView? = null

    var amountTextView : TextView? = null

    var commentTextView : TextView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_store, this)
        timeTextView = findViewById(R.id.listTime)
        amountTextView = findViewById(R.id.listAmount)
        commentTextView = findViewById(R.id.listComment)
    }

    fun setStore(store: Store) {
        timeTextView?.text = store.time
        amountTextView?.text = store.amount
        commentTextView?.text = store.comment
    }

}