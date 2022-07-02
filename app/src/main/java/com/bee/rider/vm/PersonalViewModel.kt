package com.bee.rider.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bee.rider.bean.UserBean
import com.bee.rider.http.CenterApi
import com.bee.rider.http.LoginApi
import com.bee.rider.params.LoginParams
import com.bee.rider.params.SmsCodeLoginParams
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
     * 密码登录
     */
    val lvUserDetail : MutableLiveData<Result<UserBean?>> = MutableLiveData()
    fun getUserDetail(){
        viewModelScope.launch {
            val userDetail = CenterApi.getUserDetail()
            lvUserDetail.value = userDetail
        }
    }
}