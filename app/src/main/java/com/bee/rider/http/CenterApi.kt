package com.bee.rider.http

import com.bee.rider.Constants
import com.bee.rider.params.LoginParams
import com.bee.rider.params.UpdateWorkStatusParams
import com.bee.rider.utils.toApiBody
import com.chenchen.base.network.base.BaseNetworkApi

/**
 * 创建时间：2022/6/25
 * 编写人： 陈陈陈
 * 功能描述：
 */
object CenterApi : BaseNetworkApi<ICenterService> (Constants.base_url_dis) {
    /**
     * 获取骑手信息
     */
    suspend fun getUserDetail() = getResult {
        service.getDetail()
    }

    /**
     * 修改【骑手信息】工作状态
     */
    suspend fun updateWorkStatus(param: UpdateWorkStatusParams) = getResult {
        service.updateWorkStatus(param.toApiBody())
    }
}