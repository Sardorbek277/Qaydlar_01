package com.example.qaydlar_01.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION_CODES
import com.example.qaydlar_01.models.User

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION_CODE),
    MyInterface {

    companion object {
        const val DB_NAME = "my_contact_db"
        const val TABLE_NAME = "users_table"
        const val VERSION_CODE = 1

        const val ID = "id"
        const val NAME = "name"
        const val NUMBER = "number"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME($ID integer not null primary key autoincrement unique," +
                    "$NAME text not null," +
                    "$NUMBER text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addUser(user: User) {

        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, user.name)
        contentValues.put(NUMBER, user.number)

        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun getUsers(): ArrayList<User> {
        val list = ArrayList<User>()

        val query = "select * from $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    )
                list.add(user)
            } while (cursor.moveToNext())
        }
        return list
    }
}

