package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.StatisticBean
import com.bee.rider.http.CenterApi
import com.bee.rider.http.NetworkApi
import kotlinx.coroutines.launch

/**
 * 创建时间：2022/7/23
 * 编写人： 陈陈陈
 * 功能描述：
 */
class StatisticsViewModel: ViewModel() {
    /**
     * 获取统计数据
     */
    val lvStatisticsDatas : MutableLiveData<Result<StatisticBean?>> = MutableLiveData()
    fun getStatisticsDatas(){
        viewModelScope.launch {
            val datas = NetworkApi.disDataStatistics()
            lvStatisticsDatas.value = datas
        }
    }
}