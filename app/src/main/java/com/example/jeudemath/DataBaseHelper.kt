package com.example.jeudemath
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.reflect.Array

val DATABASENAME = "SCORE DATABASE"
val TABLENAME = "Score"
val COL_SCORE = "score"
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,
                                                               1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLENAME ($COL_SCORE INTEGER)"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }
    fun insertData(score: Int) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_SCORE, score)
        database.insert(TABLENAME, null, contentValues)
    }
    fun updateData(score: Int,score2: Int) {
        val db = this.readableDatabase
        val createTable = "UPDATE $TABLENAME SET $COL_SCORE = '$score2' WHERE $COL_SCORE=$score"
        db?.execSQL(createTable)
    }


    fun readData(): MutableList<Int> {
        val list: MutableList<Int> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME ORDER BY $COL_SCORE DESC"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val score = result.getString(result.getColumnIndex(COL_SCORE)).toInt()
                list.add(score)
            }
            while (result.moveToNext())
        }
        return list
    }

}
