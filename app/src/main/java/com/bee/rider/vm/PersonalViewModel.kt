package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.StatisticBean
import com.bee.rider.bean.UserBean
import com.bee.rider.http.CenterApi
import com.bee.rider.http.LoginApi
import com.bee.rider.http.NetworkApi
import com.bee.rider.params.LoginParams
import com.bee.rider.params.SmsCodeLoginParams
import com.bee.rider.params.UpdateWorkStatusParams
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
import kotlinx.coroutines.launch

/**
 * 创建时间：2022/3/5
 * @Author： 陈陈陈
 * 功能描述：
 */
class PersonalViewModel : ViewModel(){
    /**
     * 获取骑手信息
     */
    val lvUserDetail : MutableLiveData<Result<UserBean?>> = MutableLiveData()
    fun getUserDetail(){
        viewModelScope.launch {
            val userDetail = CenterApi.getUserDetail()
            lvUserDetail.value = userDetail
        }
    }

    /**
     * 修改【骑手信息】工作状态
     */
    val lvUpdateWorkStatus : MutableLiveData<Result<Any?>> = MutableLiveData()
    fun updateWorkStatus(param: UpdateWorkStatusParams){
        viewModelScope.launch {
            val result = CenterApi.updateWorkStatus(param)
            lvUpdateWorkStatus.value = result
        }
    }

    /**
     * 获取统计数据
     */
    val lvStatisticsDatas : MutableLiveData<Result<StatisticBean?>> = MutableLiveData()
    fun getStatisticsDatas(){
        viewModelScope.launch {
            val datas = NetworkApi.disDataStatistics(2)
            lvStatisticsDatas.value = datas
        }
    }
}