package com.bee.rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.rider.bean.MessageBean
import com.bee.rider.databinding.FragmentMessageBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.LoadmoreUtils
import com.bee.rider.R
import com.bee.rider.vm.MessageViewModel
import com.chad.library.adapter.base.module.LoadMoreModule

/**
 * 创建时间：2022/7/17
 * 编写人： 陈陈陈
 * 功能描述：
 */
class MessageFragment : BaseFragment<FragmentMessageBinding>() {
    override fun initOnce(savedInstanceState: Bundle?) {
        viewModel.list.observe(this,{

        })
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentMessageBinding {
        return FragmentMessageBinding.inflate(inflater,container,false)
    }
    var loadmoreUtils: LoadmoreUtils<MessageBean>? = null
    val adapter = MessageAdapter()
    private val viewModel: MessageViewModel by viewModels()


    override fun initViews(savedInstanceState: Bundle?) {
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->

        }
        loadmoreUtils = object : LoadmoreUtils<MessageBean>(adapter, binding.srl) {
            override fun getDatas(page: Int) {
                viewModel.doGetList()
            }
        }
//        loadmoreUtils?.refresh()

        val datas = mutableListOf<MessageBean>()
        datas.add(MessageBean())
        datas.add(MessageBean())
        datas.add(MessageBean())
        datas.add(MessageBean())
        adapter.setList(datas)
    }

    class MessageAdapter : BaseQuickAdapter<MessageBean, BaseViewHolder>(R.layout.item_message),LoadMoreModule {
        override fun convert(holder: BaseViewHolder, item: MessageBean) {

        }
    }
}