package com.chenchen.bee_rider.utils

import androidx.navigation.PopUpToBuilder
import androidx.navigation.navOptions
import com.chenchen.bee_rider.R

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：全局变量
 */


/**
 * Fragment 切换动画
 */
val options = navOptions {
    anim {
        enter = R.anim.slide_in_right
        exit = R.anim.slide_out_left
        popEnter = R.anim.slide_in_left
        popExit = R.anim.slide_out_right
    }
}
