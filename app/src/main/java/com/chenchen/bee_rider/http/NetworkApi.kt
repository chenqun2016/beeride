package com.chenchen.bee_rider.http

import com.chenchen.base.network.base.BaseNetworkApi
import com.chenchen.bee_rider.utils.toApiBody

/**
 * 网络请求具体实现
 */
object NetworkApi : BaseNetworkApi<INetworkService>("http://wcs.whischer.net/wcs/") {

    suspend fun login(param: Any) = getResult {
        service.login(param.toApiBody())
    }
}