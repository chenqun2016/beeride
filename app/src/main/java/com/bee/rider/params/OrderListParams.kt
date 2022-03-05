package com.bee.rider.params

/**
 * 创建时间：2022/3/5
 * @Author： 陈陈陈
 * 功能描述：
 */
data class OrderListParams(val query:QueryVO,var pageNum:Int,var pageSize:Int)
data class QueryVO(val queryStatus:Int)