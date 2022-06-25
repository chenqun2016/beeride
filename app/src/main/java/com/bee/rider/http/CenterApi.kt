package com.bee.rider.http

import com.bee.rider.Constants
import com.chenchen.base.network.base.BaseNetworkApi

/**
 * 创建时间：2022/6/25
 * 编写人： 陈陈陈
 * 功能描述：
 */
object CenterApi : BaseNetworkApi<ICenterService> (Constants.base_url_dis) {
    /**
     * 获取用户详情
     */
    suspend fun getUserDetail() = getResult {
        service.getDetail()
    }
}