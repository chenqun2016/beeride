package com.bee.rider.params

/**
 * 创建时间：2022/7/30
 * 编写人： 陈陈陈
 * 功能描述：
 */
data class ResetPasswordParams (var password:String, var smsCode:String,var username:String,var phone:String)