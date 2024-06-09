package com.example.proyecto_innovacion_gt03_03_tema11

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.proyecto_innovacion_gt03_03_tema11.ui.gallery.Gallery

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "maps.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PLACE_NAME = "place"
        private const val COLUMN_PLACE_ID = "id"
        private const val COLUMN_PLACE_NAME = "name"
        private const val COLUMN_PLACE_RATING = "rating"
        private const val COLUMN_PLACE_TYPE = "type"
        private const val COLUMN_PLACE_LOCATION = "location"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PLACE_TABLE = ("CREATE TABLE $TABLE_PLACE_NAME (" +
                "$COLUMN_PLACE_ID TEXT PRIMARY KEY," +
                "$COLUMN_PLACE_NAME TEXT," +
                "$COLUMN_PLACE_RATING TEXT," +
                "$COLUMN_PLACE_TYPE TEXT," +
                "$COLUMN_PLACE_LOCATION TEXT)")
        db.execSQL(CREATE_PLACE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PLACE_NAME")
        onCreate(db)
    }

    fun insertPlace(id: String, name: String, rating: String, type: String, location: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COLUMN_PLACE_ID, id)
        values.put(COLUMN_PLACE_NAME, name)
        values.put(COLUMN_PLACE_RATING, rating)
        values.put(COLUMN_PLACE_TYPE, type)
        values.put(COLUMN_PLACE_LOCATION, location)

        val result = db.insert(TABLE_PLACE_NAME, null, values)
        return result
        db.close()
    }

    fun showPlace(): ArrayList<Gallery> {
        val placeList = ArrayList<Gallery>()
        val selectQuery = "SELECT * FROM $TABLE_PLACE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        while (cursor.moveToNext()) {
            val id = cursor.getString(0)
            val name = cursor.getString(1)
            val rating = cursor.getString(2)
            val type = cursor.getString(3)
            val location = cursor.getString(4)
            val gallery = Gallery(name, rating, type, id, location)
            placeList.add(gallery)
        }

        cursor.close()
        db.close()
        return placeList
    }
}