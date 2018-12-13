package com.example.storemanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StoreDatabaseOpenHelper(context: Context) : SQLiteOpenHelper(context, "StoreManager", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE " + "store" + " ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "time text not null, "+
                    "quantity INTEGER not null, " +
                    "comment text not null " +
                    ");")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + "store" + ";")
        onCreate(db)
    }

}