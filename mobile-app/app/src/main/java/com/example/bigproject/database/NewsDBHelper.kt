package com.example.bigproject.database

import android.content.ContentValues
import android.content.Context
import android.database.AbstractWindowedCursor
import android.database.CursorWindow
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.bigproject.newsrecycler.AddItem
import java.io.ByteArrayOutputStream


class NewsDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Book表的建表语句，id是整型主键自增长，为每一组Array自动分配唯一id
        // text表示文本类型
         val createBook = "create table Book (" +
                " id integer primary key autoincrement, " +
                " title text, author text,description text,content text,image_path1 text,image_path2 text)"
        db.execSQL(createBook);
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Book.db"
    }

    fun putvalues(title:String,author:String,description:String,content:String,bit1:Bitmap,bit2:Bitmap): Long{
        val db=readableDatabase
        var values= ContentValues()
        val os1 = ByteArrayOutputStream()//压缩图片用输出流
        val os2 = ByteArrayOutputStream()//压缩图片用输出流
        bit1.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, os1)
        bit2.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, os2)
        values.put("title", title)
        values.put("author",author)
        values.put("description", description)
        values.put("content", content)
        values.put("image_path1", os1.toByteArray())
        values.put("image_path2",os2.toByteArray())
        return db.insert("Book", null, values)
        // 插入其它数据前先清空
//        values.clear()
    }
    /**读取数据库*/
    @RequiresApi(Build.VERSION_CODES.P)
    fun readvalues(cont:Context):ArrayList<AddItem> {
            val list = ArrayList<AddItem>()
            // 以读取模式获取数据存储库
            val db: SQLiteDatabase = readableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
        val cw = CursorWindow("test", 5000000)
        val ac = cursor as AbstractWindowedCursor
        ac.window = cw
//        val data = StringBuilder()
            with(cursor) {
                while (moveToNext()) {
                    list.add(
                        AddItem(
                            getInt(getColumnIndexOrThrow("id")),
                            getString(getColumnIndexOrThrow("title")),
                            getString(getColumnIndexOrThrow("author")),
                            getString(getColumnIndexOrThrow("description")),
                            getString(getColumnIndexOrThrow("content")),
                            getBlob(getColumnIndexOrThrow("image_path1")),
                            getBlob(getColumnIndexOrThrow("image_path2"))
                        )
                    )
                }
            }
            cursor.close()
            return list
        }
    /**删除数据库*/
    fun dbDelete(position:Int) {
        val db: SQLiteDatabase = writableDatabase
        // ？是一个占位符
        // 第四个参数提供一个字符串数组，为第三个参数中的每个占位符指定相应的内容
        db.delete("Book", "id = ?", arrayOf(position.toString()))
    }
}