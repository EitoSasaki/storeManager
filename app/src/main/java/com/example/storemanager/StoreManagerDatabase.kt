package com.example.storemanager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class StoreManagerDatabase(context: Context) {

    private val storeDatabaseOpenHelper = StoreDatabaseOpenHelper(context)
    private val database : SQLiteDatabase = storeDatabaseOpenHelper.writableDatabase

    fun addStore(time: String, amount: Int, comment: String) {
        val values = ContentValues()
        values.put("time", time)
        values.put("amount", amount )
        values.put("comment", comment)

        database.insertOrThrow("store", null, values)
    }

    fun getStore() : List<Store>? {
        val sql = "select * from store order by id ASC"
        val cursor : Cursor = database.rawQuery(sql, null)
        var result : List<Store>? = null

        while (cursor.moveToNext()) {
            result!!.plus(Store(cursor.getString(1), cursor.getInt(2), cursor.getString(3)))
        }

        return result
    }

}