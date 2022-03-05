package com.chenchen.bee_rider.http

import com.chenchen.base.network.base.BaseNetworkApi
import com.chenchen.bee_rider.Constants
import com.chenchen.bee_rider.params.LoginParams
import com.chenchen.bee_rider.utils.toApiBody

/**
 * 网络请求具体实现
 */
object NetworkApi : BaseNetworkApi<INetworkService>(Constants.base_url) {

    suspend fun login(param: LoginParams) = getResult {
        service.login(param.toApiBody())
    }
}