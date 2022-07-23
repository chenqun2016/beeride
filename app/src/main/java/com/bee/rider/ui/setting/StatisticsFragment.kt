package com.bee.rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.rider.bean.UserBean
import com.bee.rider.databinding.FragmentStatisticBinding
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.MMKVUtils
import com.bee.rider.R
import com.bee.rider.bean.StatisticBean
import com.bee.rider.utils.UIUtils
import com.bee.rider.vm.StatisticsViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 创建时间：2022/7/23
 * 编写人： 陈陈陈
 * 功能描述：
 */
class StatisticsFragment : BaseFragment<FragmentStatisticBinding>(){
    private val viewModel: StatisticsViewModel by viewModels()

    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.lvStatisticsDatas.observe(this,{
            if(it.isSuccess){
                val data = it.getOrNull()
                if(null != data){

                    mAdapter.setNewInstance(data.details)
                    binding.tv1.text = data.disOrderTotal
                    binding.tv2.text = data.praiseRate
                    binding.tv3.text = data.deliveryPraiseRate
                }
            }
        })
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentStatisticBinding {
        return FragmentStatisticBinding.inflate(inflater)
    }

    val mAdapter : MyAdapter by lazy {
        MyAdapter()
    }
    override fun initViews(savedInstanceState: Bundle?) {
        val userBean = MMKVUtils.getObject(UserBean::class.java)
        if(userBean != null) {
            UIUtils.setGradientDrawable(this,null,binding.ivBg,if(userBean.isWork == 1) R.color.color_FF6200 else R.color.color_2c2c2c)
        }

        binding.rvDatas.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        viewModel.getStatisticsDatas()
    }

    class MyAdapter : BaseQuickAdapter<StatisticBean.Details, BaseViewHolder>(R.layout.item_statistic) {
        override fun convert(holder: BaseViewHolder, item: StatisticBean.Details) {
            holder.setText(R.id.tv_raking,item.ranking).
            setText(R.id.tv_1_text,item.successOrder).
            setText(R.id.tv_2_text,item.cancleOrder).
            setText(R.id.tv_3_text,item.arriveLateOrder)
            when(item.dateMark){
                "today" -> {
                    holder.setText(R.id.tv_title,"今日数据")
                }
                "month" -> {
                    holder.setText(R.id.tv_title,"月度数据")
                }
                "year" -> {
                    holder.setText(R.id.tv_title,"年度数据")
                }
            }
        }
    }
}