package com.bee.rider.http

import com.chenchen.base.network.base.BaseNetworkApi
import com.bee.rider.Constants
import com.bee.rider.params.InitiativeCreateParams
import com.bee.rider.params.OrderListParams
import com.bee.rider.params.UpdateInfoParams
import com.bee.rider.utils.toApiBody

/**
 * 网络请求具体实现
 */
object NetworkApi : BaseNetworkApi<INetworkService>(Constants.base_url) {

    suspend fun homeList(param: OrderListParams) = getResult {
        service.homeList(param.toApiBody())
    }
    suspend fun historyList(param: OrderListParams) = getResult {
        service.historyList(param.toApiBody())
    }
    suspend fun orderDetail(id:String) = getResult {
        service.orderDetail(id)
    }
    suspend fun initiativeCreate(param: InitiativeCreateParams) = getResult {
        service.initiativeCreate(param.toApiBody())
    }
    suspend fun appUpdateInfo(param: UpdateInfoParams) = getResult {
        service.appUpdateInfo(param.toApiBody())
    }

    suspend fun getOperateHistory(orderId:String?) = getResult {
        service.getOperateHistory(orderId)
    }
}