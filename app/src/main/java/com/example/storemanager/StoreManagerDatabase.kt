package com.example.storemanager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class StoreManagerDatabase(context: Context) {

    private val storeDatabaseOpenHelper = StoreDatabaseOpenHelper(context)
    private val database : SQLiteDatabase = storeDatabaseOpenHelper.writableDatabase

    fun addStock(time: String, quantity: Int, comment: String) {
        val values = ContentValues()
        values.put("time", time)
        values.put("quantity", quantity )
        values.put("comment", comment)

        database.insertOrThrow("store", null, values)
    }

    fun getStock() : List<Stock>? {
        val sql = "select * from store order by id ASC"
        val cursor : Cursor = database.rawQuery(sql, null)
        var result : List<Stock>? = null

        while (cursor.moveToNext()) {
            result?.plus(Stock(cursor.getString(1), cursor.getInt(2), cursor.getString(3)))
        }

        return result
    }

}