package com.bee.rider.bean
/**
 - @Description:
 - @Author: bxy
 - @Time:  2022/3/5 下午5:35
 */
data class AppUpdateInfoBean (
    val contents: String,
    val createBy: Int,
    val createTime: String,
    val id: Int,
    val isForceUpdate: Int,
    val pushTime: String,
    val updateBy: Int,
    val updateTime: String,
    val url: String,
    val versionCode: String,
    val versionName: String
)
