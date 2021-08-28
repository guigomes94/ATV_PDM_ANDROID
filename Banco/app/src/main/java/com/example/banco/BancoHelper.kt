package com.example.banco

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context): SQLiteOpenHelper(context, "pessoas.dat", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table pessoas( " +
                     "id integer primary key autoincrement, " +
                     "nome text, " +
                     "datahora integer)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table pessoas")
        this.onCreate(db)
    }
}