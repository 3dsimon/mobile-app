package com.example.bigproject.account

data class AccountItem (
    /**id*/
    val id:Int,
    /** 用户名 */
    val name: String?,
    /** 账号 */
    val account: String?,
    /** 密码 */
    val password: String?,
    /** 头像 */
    val image_path:ByteArray?,
    /** 简介 */
    val description:String?,
    /** 详细介绍 */
    val content:String?
)