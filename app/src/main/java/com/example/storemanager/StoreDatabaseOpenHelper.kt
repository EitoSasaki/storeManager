package com.example.storemanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StoreDatabaseOpenHelper(context: Context) : SQLiteOpenHelper(context, "StoreManager", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        /*
        db?.execSQL(
            "CREATE TABLE " + "store" + " ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "time text not null, "+
                    "amount INTEGER not null, " +
                    "comment text not null " +
                    ");")
                    */
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}