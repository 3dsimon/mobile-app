package com.example.bigproject.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import com.example.bigproject.account.AccountItem
import java.io.ByteArrayOutputStream

class AccountDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Account表的建表语句，id是整型主键自增长，为每一组Array自动分配唯一id
        // text表示文本类型
         val createAccount = "create table Account (" +
                " id integer primary key autoincrement, " +
                " name text, account text,password text,image_path text,description text,content text)"
        db.execSQL(createAccount);
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Account.db"
    }

    fun putvalues(name:String,account:String,password:String,bit:Bitmap,description:String?,content:String?): Long{
        val db=readableDatabase
        var values= ContentValues()
        val os = ByteArrayOutputStream()//压缩图片用输出流
        bit.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, os)
        values.put("name", name)
        values.put("account", account)
        values.put("password", password)
        values.put("image_path",os.toByteArray())
        values.put("description",description)
        values.put("content",content)
        return db.insert("Account", null, values)
        // 插入其它数据前先清空
//        values.clear()
    }
    /**读取数据库*/
    fun readvalues(cont:Context):ArrayList<AccountItem> {
            val list = ArrayList<AccountItem>()
            // 以读取模式获取数据存储库
            val db: SQLiteDatabase =readableDatabase
            val cursor = db.query("Account", null, null, null, null, null, null)
            with(cursor) {
                while (moveToNext()) {
                    list.add(
                        AccountItem(
                            getInt(getColumnIndexOrThrow("id")),
                            getString(getColumnIndexOrThrow("name")),
                            getString(getColumnIndexOrThrow("account")),
                            getString(getColumnIndexOrThrow("password")),
                            getBlob(getColumnIndexOrThrow("image_path")),
                            getString(getColumnIndexOrThrow("description")),
                            getString(getColumnIndexOrThrow("content"))
                            )
                    )
                }
            }
            cursor.close()
            return list
        }

    fun updatevalues(id:Int,name:String?,description: String?,content: String?) {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()
        if(name!=null)values.put("name",name)
        if(description!=null)values.put("description",description)
        if(content!=null)values.put("content",content)
        // ？是一个占位符
        // 第四个参数提供一个字符串数组，为第三个参数中的每个占位符指定相应的内容
        db.update("Account", values, "id= ?", arrayOf(id.toString()))
    }

    fun updatephoto(id:Int,bit:Bitmap){
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()
        val os = ByteArrayOutputStream()//压缩图片用输出流
        bit.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, os)
        values.put("image_path",os.toByteArray())
        db.update("Account", values, "id= ?", arrayOf(id.toString()))
    }
}