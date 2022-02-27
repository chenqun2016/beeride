package com.chenchen.bee_rider

import java.text.SimpleDateFormat

/**
 * 创建时间：2022/1/8
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
object Constants {
    //    隐私权政策
    const val agreement_privacy = "http://www.quxianfeng.vip/yhyszc.html"

    //    用户注册协议
    const val agreement_regist = "http://www.quxianfeng.vip/yszcxy.html"
    var sdfLong2 = SimpleDateFormat("yyyy/MM/dd")

    var LOCATION = "location"

    /**
     * 是否播放提示音
     */
    var SETTING_NOTICE_VOICE = "setting_notice_voice"
    /**
     * 是否震动
     */
    var SETTING_NOTICE_SHAKE = "setting_notice_shake"

    /**
     * 自动接单
     */
    var SETTING_ORDER_ACCEPT_AUTO = "setting_order_accept_auto"
    /**
     * 一键接单
     */
    var SETTING_ORDER_ACCEPT_ONEKEY = "setting_order_accept_onekey"

    //用户信息
    var USER_INFO = "user_info"

    //用户个人信息页面回调
    var ITEMRESULT = "itemResult"
}