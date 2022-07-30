package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.http.CenterApi
import com.bee.rider.http.LoginApi
import com.bee.rider.params.LoginParams
import com.bee.rider.params.ResetPasswordParams
import com.bee.rider.params.SmsCodeLoginParams
import com.chenchen.base.constants.HttpConstants
import com.chenchen.base.utils.MMKVUtils
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
        viewModelScope.launch {
            val tokenR = LoginApi.login(param)
            putLoginDatas(tokenR)
            passwordLogin.value = tokenR
        }
    }

    /**
     * 验证码登录
     */
    val smsLogin : MutableLiveData<Result<String?>> = MutableLiveData()
    fun doSmsLogin(param: SmsCodeLoginParams){
        viewModelScope.launch {
            val result = LoginApi.loginSmscode(param)
            putLoginDatas(result)
            smsLogin.value = result
        }
    }

    /**
     * 保存登录信息
     */
    private suspend fun putLoginDatas(tokenR: Result<String?>) {
        if(tokenR.isSuccess){
            val token = tokenR.getOrNull()
            if(null != token){
                MMKVUtils.putString(HttpConstants.TOKEN,token)
                val userDetail = CenterApi.getUserDetail()
                if(userDetail.isSuccess){
                    val userBean = userDetail.getOrNull()
                    if(null != userBean){
                        MMKVUtils.putObject(userBean)
                    }
                }
            }
        }
    }

    /**
     * 密码登录
     */
    val resetPassword : MutableLiveData<Result<String?>> = MutableLiveData()
    fun doResetPassword(param: ResetPasswordParams){
        viewModelScope.launch {
            val tokenR = LoginApi.resetPassword(param)
            resetPassword.value = tokenR
        }
    }

    /**
     * 验证码校验
     */
    val checkSmsCode : MutableLiveData<Result<String?>> = MutableLiveData()
    fun doCheckSmsCode(phone:String,code:String){
        viewModelScope.launch {
            val tokenR = LoginApi.checkSmsCode(phone,code)
            checkSmsCode.value = tokenR
        }
    }
}