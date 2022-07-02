package com.bee.rider

import java.text.SimpleDateFormat

/**
 * 创建时间：2022/1/8
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
object Constants {
    //TODO 测试用
    const val base_url2 = "http://dev-beeweb2.quxianfeng.vip:15889/bee-member-zuul-gateway/"
//    private const val base_url = "http://dev-bee-horseman-zuul-gateway.quxianfeng.vip:15889/"

    //登录
//    const val base_url_uaa = "${base_url}api-uaa/"
//    const val base_url_center = "${base_url2}dis-center/"

    const val base_url_login = "${base_url2}api-uaa/"
    const val base_url_dis = "${base_url2}api-dis/"

    //    隐私权政策
    const val agreement_privacy = "http://www.quxianfeng.vip/yhyszc.html"

    //    用户注册协议
    const val agreement_regist = "http://www.quxianfeng.vip/yszcxy.html"

    var sdfLong1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
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

    //订单id
    var TAKEOUTID = "takeoutId"
    //订单id
    var ORDERID = "orderId"

    //用户
    var USER = "user"

    // 首页订单列表类型，0：新订单，1：带取货，2：待送达
    val TYPE = "type"
}