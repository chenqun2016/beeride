package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.OrderListBean
import com.bee.rider.http.NetworkApi
import com.bee.rider.params.InitiativeCreateParams
import com.bee.rider.params.OrderListParams
import kotlinx.coroutines.launch

/**
 * 创建时间：2022/3/5
 * @Author： 陈陈陈
 * 功能描述：
 */
class HomeViewModel : ViewModel(){

    val homeList : MutableLiveData<Result<OrderListBean?>> = MutableLiveData()
    fun doHomeList(param: OrderListParams){
        viewModelScope.launch {
            val result = NetworkApi.homeList(param)
            homeList.value = result
        }
    }

    val historyList : MutableLiveData<Result<OrderListBean?>> = MutableLiveData()
    fun doHistoryList(param: OrderListParams){
        viewModelScope.launch {
            val result = NetworkApi.historyList(param)
            historyList.value = result
        }
    }
}