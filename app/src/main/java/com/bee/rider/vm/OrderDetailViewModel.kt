package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.OrderDetailBean
import com.bee.rider.bean.OrderDetailTraceBean
import com.bee.rider.http.NetworkApi
import kotlinx.coroutines.launch

/**
 * 创建时间：2022/3/5
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailViewModel : ViewModel() {

    val orderDetail : MutableLiveData<Result<OrderDetailBean?>> = MutableLiveData()
    fun doOrderDetail(id: String){
        viewModelScope.launch {
            val result = NetworkApi.orderDetail(id)
            orderDetail.value = result
        }
    }

    val getOperateHistory : MutableLiveData<Result<List<OrderDetailTraceBean>?>> = MutableLiveData()
    fun doGetOperateHistory(orderId: String?){
        viewModelScope.launch {
            val result = NetworkApi.getOperateHistory(orderId)
            getOperateHistory.value = result
        }
    }
}