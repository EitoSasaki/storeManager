package com.example.storemanager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class StoreManagerDatabase(context: Context) {

    private val storeDatabaseOpenHelper = StoreDatabaseOpenHelper(context)
    private val database : SQLiteDatabase = storeDatabaseOpenHelper.writableDatabase

    fun addStock(time: String, quantity: Int, comment: String) {
        val values = ContentValues()
        values.put("time", time)
        values.put("quantity", quantity)
        values.put("comment", comment)

        var id : Long = database.insertOrThrow("store", null, values)
        Log.d("_id", id.toString())
    }

    fun getStock() : Cursor {
        val sql = "select id, time, quantity, comment from store order by id ASC"
        return database.rawQuery(sql, null)
    }

    fun readStock(cursor: Cursor) : List<Stock> {
        var isEof = cursor.moveToFirst()
        var stockList : MutableList<Stock> = mutableListOf()

        while(isEof){
            stockList.add(
                Stock(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
                )
            )
            isEof = cursor.moveToNext()
        }

        return stockList.toList()
    }

    fun getStockCount() : Int {
        return  getStock().count
    }

}