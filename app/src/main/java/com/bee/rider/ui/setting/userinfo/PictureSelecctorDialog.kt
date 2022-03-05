package com.bee.rider.ui.setting.userinfo

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.chenchen.base.base.BaseBottomDialog
import com.bee.rider.R
import com.bee.rider.utils.PicassoEngine
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.luck.picture.lib.style.PictureParameterStyle

/**
 * 创建时间：2022/2/26
 * @Author： 陈陈陈
 * 功能描述:选择图片dialog
 */
class PictureSelecctorDialog(val fragment: Fragment,val listener: OnResultCallbackListener<LocalMedia?>) : BaseBottomDialog(fragment.requireContext()) {
    override fun initViews(dialog: BaseBottomDialog) {
        dialog.findViewById<TextView>(R.id.tv_cancel).setOnClickListener(View.OnClickListener {
            if (null != dialog && dialog.isShowing()) {
                dialog.dismiss()
            }
        })
        val tv_1: TextView = dialog.findViewById(R.id.tv_1)
        tv_1.text = "拍照"
        tv_1.setOnClickListener {
            PictureSelector.create(fragment)
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(PicassoEngine.createPicassoEngine())
                .isCompress(true)
                .isEnableCrop(true) // 是否裁剪
                //                                .cropImageWideHigh(DisplayUtil.dip2px(UserInfoActivity.this,36),DisplayUtil.dip2px(UserInfoActivity.this,36))
                .circleDimmedLayer(true)
                .rotateEnabled(false) // 裁剪是否可旋转图片
                .scaleEnabled(true) // 裁剪是否可放大缩小图片
                .setCircleDimmedBorderColor(fragment.resources.getColor(R.color.white)) //设置圆形裁剪边框色值
                .setCircleStrokeWidth(1) //设置圆形裁剪边框粗细
                .setCropDimmedColor(fragment.resources.getColor(R.color.color_FF6200_10))
                .showCropFrame(false) // 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false) //是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .withAspectRatio(1000, 1000)
                .cutOutQuality(60)
                .minimumCompressSize(1024)
                .cropImageWideHigh(1000, 1000)
                .forResult(listener)
            dialog.dismiss()
        }
        val tv_2: TextView = dialog.findViewById(R.id.tv_2)
        tv_2.text = "相册选择"
        tv_2.setOnClickListener {
            PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(PicassoEngine.createPicassoEngine())
                .setPictureStyle(PictureParameterStyle.ofSelectTotalStyle())
                .selectionMode(PictureConfig.SINGLE)
                .isCamera(false)
                .isCompress(true)
                .isSingleDirectReturn(true)
                .isEnableCrop(true) // 是否裁剪
                //                                .cropImageWideHigh(DisplayUtil.dip2px(UserInfoActivity.this,36),DisplayUtil.dip2px(UserInfoActivity.this,36))
                .circleDimmedLayer(true)
                .rotateEnabled(false) // 裁剪是否可旋转图片
                .scaleEnabled(true) // 裁剪是否可放大缩小图片
                .setCircleDimmedBorderColor(fragment.resources.getColor(R.color.white)) //设置圆形裁剪边框色值
                .setCircleStrokeWidth(1) //设置圆形裁剪边框粗细
                .setCropDimmedColor(fragment.resources.getColor(R.color.color_FF6200_10))
                .showCropFrame(false) // 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false) //是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .withAspectRatio(1000, 1000)
                .cutOutQuality(60)
                .minimumCompressSize(1024)
                .cropImageWideHigh(1000, 1000)
                .forResult(listener)
            dialog.dismiss()
        }
    }

    override fun provideContentViewId(): Int {
        return R.layout.dialog_take_phone
    }
}