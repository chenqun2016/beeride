package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.AppUpdateInfoBean
import com.bee.rider.bean.LoginBean
import com.bee.rider.http.NetworkApi
import com.bee.rider.params.LoginParams
import com.bee.rider.params.UpdateInfoParams
import kotlinx.coroutines.launch

/**
 - @Description:
 - @Author: bxy
 - @Time:  2022/3/5 下午5:39
 */
class MainViewModel : ViewModel(){

    val updateInfo : MutableLiveData<Result<AppUpdateInfoBean?>> = MutableLiveData()
    fun updateInfo(param: UpdateInfoParams){
        viewModelScope.launch {
            val result = NetworkApi.appUpdateInfo(param)
            updateInfo.value = result
        }
    }
}