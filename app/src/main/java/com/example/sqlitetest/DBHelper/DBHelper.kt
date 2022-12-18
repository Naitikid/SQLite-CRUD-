package com.example.sqlitetest.DBHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlitetest.ModelClass.UserViewModel

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query =
            ("CREATE TABLE $TABLENAME($ID INTEGER PRIMARY KEY,$USERNAME TEXT,$SURENAME TEXT,$COMPANYNAME TEXT,$USEREMAIL TEXT,$PHONENUMBER TEXT)")
        db.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        onCreate(db)
    }

    /*ADD DATA IN DATABASE*/
    fun addName(usermodle: UserViewModel): Long {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(USERNAME, usermodle.username)
        values.put(SURENAME, usermodle.surname)
        values.put(COMPANYNAME, usermodle.companyname)
        values.put(USEREMAIL, usermodle.userremail)
        values.put(PHONENUMBER, usermodle.phonenumber)
        return db.insert(TABLENAME, null, values)
    }

    /*SHOW DATA IN ACIVITY*/
    @SuppressLint("Range")
    fun showdata(): ArrayList<UserViewModel> {
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLENAME"
        val userlist = ArrayList<UserViewModel>()
        val cursor: Cursor
        cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                userlist.add(
                    UserViewModel(
                        cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(USERNAME)),
                        cursor.getString(cursor.getColumnIndex(SURENAME)),
                        cursor.getString(cursor.getColumnIndex(COMPANYNAME)),
                        cursor.getString(cursor.getColumnIndex(USEREMAIL)),
                        cursor.getString(cursor.getColumnIndex(PHONENUMBER)),
                    )
                )
            } while (cursor.moveToNext())
        }
        return userlist
    }

    /*this is used for update*/

    fun updatedata(userModel: UserViewModel): Int {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, userModel.id)
        values.put(USERNAME, userModel.username)
        values.put(SURENAME, userModel.surname)
        values.put(COMPANYNAME, userModel.companyname)
        values.put(USEREMAIL, userModel.userremail)
        values.put(PHONENUMBER, userModel.phonenumber)

        var userdata = db.update(TABLENAME, values, "id = " + userModel.id, null)
        db.close()
        return userdata
    }

    /*this is used for Delect */

    fun delect(id: Int): Int {
        val db = this.writableDatabase
        val delete = db.delete(TABLENAME, "id = " + id, null)
        return delete
    }

    companion object {
        val DATABASE_NAME = "sqlitetestdatabase"
        val DATABASE_VERSION = 3
        val TABLENAME = "sqlitetable"
        val ID = "id"
        val USERNAME = "username"
        val SURENAME = "surename"
        val COMPANYNAME = "companyname"
        val USEREMAIL = "useremail"
        val PHONENUMBER = "phonenumber"
    }


}