package com.chenchen.bee_rider.ui.setting.userinfo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.chenchen.base.base.BaseBottomDialog
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.DisplayUtil
import com.chenchen.base.utils.MMKVUtils
import com.chenchen.bee_rider.Constants
import com.chenchen.bee_rider.Constants.ITEMRESULT
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.bean.UserBean
import com.chenchen.bee_rider.databinding.FragmentUserinfoBinding
import com.chenchen.bee_rider.utils.PicassoRoundTransform
import com.chenchen.bee_rider.utils.options
import com.google.gson.Gson
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.squareup.picasso.Picasso

/**
 * 创建时间：2022/2/20
 * @Author： 陈陈陈
 * 功能描述：个人资料
 */
class UserInfoFragment : BaseFragment<FragmentUserinfoBinding>(), View.OnClickListener {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentUserinfoBinding {
        return FragmentUserinfoBinding.inflate(inflater)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val gString = MMKVUtils.getString(Constants.USER_INFO, "")
        val userInfo = Gson().fromJson(gString, UserBean::class.java)
        if (null != userInfo) {
            if (!TextUtils.isEmpty(userInfo.icon)) {
                Picasso.with(context)
                    .load(userInfo.icon)
                    .fit()
                    .transform(
                        PicassoRoundTransform(
                            DisplayUtil.dip2px(context, 100f),
                            0,
                            PicassoRoundTransform.CornerType.ALL
                        )
                    )
                    .into(binding.tvIcon)
            }
            binding.tvMingchengText.text = ""
            binding.tvGonghaoText.text = ""
            binding.tvXingbieText.text = ""
            binding.tvPhoneText.text = ""
            binding.tvIdcardText.text = ""
            binding.tvWorkTypeText.text = ""
            binding.tvDizhiText.text = ""
        }

        binding.tvIcon.setOnClickListener(this)
        binding.tvMingchengText.setOnClickListener(this)
        binding.tvGonghaoText.setOnClickListener(this)
        binding.tvXingbieText.setOnClickListener(this)
        binding.tvPhoneText.setOnClickListener(this)
        binding.tvIdcardText.setOnClickListener(this)
        binding.tvWorkTypeText.setOnClickListener(this)
        binding.tvDizhiText.setOnClickListener(this)
        binding.tvAboutCardText.setOnClickListener(this)
        binding.tvAreaText.setOnClickListener(this)
        binding.tvContactsText.setOnClickListener(this)


        parentFragmentManager.setFragmentResultListener(ITEMRESULT, this,
            { requestKey, result ->
                val type: Int? = result.getInt("type",0)
                val text: String? = result.getString("text","")
                when(type){
                    0 -> {
                        binding.tvMingchengText.text=text
                    }
                }
            })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_icon -> {
                showTakePhoneDialog()
            }
            R.id.tv_mingcheng_text -> {
                val args = Bundle()
                args.putInt("type", 0)
                args.putString("str1", "修改名称")
                args.putString("str2", "昵称为2～32位字符，支持中文、英文、数字")
                args.putString("str3", binding.tvMingchengText.text.toString())
                findNavController().navigate(R.id.userinfo_iten_dest,args, options)
            }
            R.id.tv_gonghao_text -> {

            }
            R.id.tv_xingbie_text -> {
                showXingBieDialog()
            }
            R.id.tv_phone_text -> {

            }
            R.id.tv_idcard_text -> {

            }
            R.id.tv_work_type_text -> {

            }
            R.id.tv_dizhi_text -> {

            }
            R.id.tv_about_card_text -> {
                findNavController().navigate(R.id.userinfo_certi_dest,null, options)
            }
            R.id.tv_area_text -> {
                findNavController().navigate(R.id.userinfo_areas_dest,null, options)
            }
            R.id.tv_contacts_text -> {
                findNavController().navigate(R.id.userinfo_contacts_dest,null, options)
            }
        }
    }

    private fun showXingBieDialog() {
        val dialog = object  :BaseBottomDialog(requireContext()){
            override fun initViews(dialog: BaseBottomDialog) {
                dialog.findViewById<TextView>(R.id.tv_cancel).setOnClickListener(View.OnClickListener {
                    if (null != dialog && dialog.isShowing) {
                        dialog.dismiss()
                    }
                })

                val tv_1: TextView = dialog.findViewById(R.id.tv_1)
                tv_1.text = "男性"
                tv_1.setOnClickListener {
                    binding.tvXingbieText.text = "男"
                    dialog.dismiss()
//                    setUserDatas()
                }

                val tv_2: TextView = dialog.findViewById(R.id.tv_2)
                tv_2.text = "女性"
                tv_2.setOnClickListener {
                    binding.tvXingbieText.text ="女"
                    dialog.dismiss()
//                    setUserDatas()
                }
            }

            override fun provideContentViewId(): Int {
                return R.layout.dialog_take_phone
            }
        }
        dialog.show()
    }

    private fun showTakePhoneDialog() {
        val bottomSheetDialog = PictureSelecctorDialog(this,object :OnResultCallbackListener<LocalMedia?>{
            override fun onResult(result: MutableList<LocalMedia?>?) {
                // 结果回调
                if (null != result && result.size > 0) {
                    val localMedia = result[0]
//                            upImageToOss(localMedia)
                }
            }

            override fun onCancel() {

            }
        })
        bottomSheetDialog.show()
    }


    /**
     * 上传图片
     * @param media
     */
    private fun upImageToOss(media: LocalMedia?) {

    }
}