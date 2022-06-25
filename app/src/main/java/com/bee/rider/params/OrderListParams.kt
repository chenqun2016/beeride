package com.bee.rider.params

import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import java.util.*

/**
 * 创建时间：2022/3/5
 * @Author： 陈陈陈
 * 功能描述：
 */
data class OrderListParams(val queryVO:QueryVO,var pageNum:Int,var pageSize:Int)
//TODO  horsemanId 测试用
data class QueryVO(val queryStatus:Int, val beginDate: String? = null, val endDate:String? = null)