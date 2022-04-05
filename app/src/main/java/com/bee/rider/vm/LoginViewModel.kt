package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.LoginBean
import com.bee.rider.http.LoginApi
import com.bee.rider.http.LoginApiTest
import com.bee.rider.params.LoginParams
import com.bee.rider.params.SmsCodeLoginParams
import kotlinx.coroutines.launch

/**
 * 创建时间：2022/3/5
 * @Author： 陈陈陈
 * 功能描述：
 */
class LoginViewModel : ViewModel(){
    /**
     * 密码登录
     */
    val passwordLogin : MutableLiveData<Result<String?>> = MutableLiveData()
    fun doPasswordLogin(param: LoginParams){
        param.username = "test1"
        param.password = "123456"
        viewModelScope.launch {
            val result = LoginApi.login(param)
            passwordLogin.value = result
        }
    }
    /**
     * 密码登录 测试
     */
    val passwordLogin2 : MutableLiveData<Result<LoginBean?>> = MutableLiveData()
    fun doPasswordLogin2(param: LoginParams){
        param.username = "test01"
        param.password = "123456"
        viewModelScope.launch {
            val result = LoginApiTest.login2(param)
            passwordLogin2.value = result
        }
    }

    /**
     * 验证码登录
     */
    val smsLogin : MutableLiveData<Result<String?>> = MutableLiveData()
    fun doSmsLogin(param: SmsCodeLoginParams){
        viewModelScope.launch {
            val result = LoginApi.loginSmscode(param)
            smsLogin.value = result
        }
    }
}