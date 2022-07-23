package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.MessageBean
import com.bee.rider.http.NetworkApi
import com.bee.rider.params.MessageParams
import kotlinx.coroutines.launch

/**
 * 创建时间：2022/7/17
 * 编写人： 陈陈陈
 * 功能描述：
 */
class MessageViewModel :ViewModel() {
    val list : MutableLiveData<Result<List<MessageBean>?>> = MutableLiveData()
    fun doGetList(params: MessageParams){
        viewModelScope.launch {
//            val result = NetworkApi.homeList()
//            list.value = result
        }
    }
}