package com.bee.rider.http

import com.bee.rider.Constants
import com.bee.rider.params.LoginParams
import com.bee.rider.utils.toApiBody
import com.chenchen.base.network.base.BaseNetworkApi

/**
 * 创建时间：2022/4/5
 * 编写人： 陈陈陈
 * 功能描述：
 */
object LoginApiTest : BaseNetworkApi<ILoginServiceTest>(Constants.base_url_dis) {

    suspend fun login2(param: LoginParams) = getResult {
        service.login2(param.toApiBody())
    }
}