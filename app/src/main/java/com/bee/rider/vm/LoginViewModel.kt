package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.LoginBean
import com.bee.rider.http.NetworkApi
import com.bee.rider.params.LoginParams
import kotlinx.coroutines.launch

/**
 * 创建时间：2022/3/5
 * @Author： 陈陈陈
 * 功能描述：
 */
class LoginViewModel : ViewModel(){

    val login : MutableLiveData<Result<LoginBean?>> = MutableLiveData()
    fun doLogin(param: LoginParams){
        viewModelScope.launch {
            val result = NetworkApi.login(param)
            login.value = result
        }
    }
}