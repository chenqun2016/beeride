package com.bee.rider.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chenchen.base.base.BaseFragment
import com.chenchen.base.utils.LoadmoreUtils
import com.bee.rider.bean.CommentBean
import com.bee.rider.databinding.ModelRecyclerviewBinding
import com.bee.rider.ui.adapter.CommentListAdapter

/**
 * 创建时间：2022/2/4
 * @Author： 陈陈陈
 * 功能描述：
 */
class CommentsListFragment :BaseFragment<ModelRecyclerviewBinding>() {
    companion object{
        fun newInstance(type: Int): CommentsListFragment {
            val args = Bundle()
            args.putInt("type",type)
            val fragment = CommentsListFragment()
            fragment.arguments = args
            return fragment
        }
    }
    val adapter = CommentListAdapter()
    var loadmoreUtils: LoadmoreUtils<CommentBean>? = null

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ModelRecyclerviewBinding {
        return ModelRecyclerviewBinding.inflate(inflater, container, false)
    }

    override fun initViews(savedInstanceState: Bundle?) {
        val type = arguments?.getInt("type")

        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = adapter
        loadmoreUtils = object :LoadmoreUtils<CommentBean>(adapter,binding.srl){
            override fun getDatas(page: Int) {
                val list = mutableListOf<CommentBean>()
                list.add(CommentBean())
                list.add(CommentBean())
                list.add(CommentBean())
                list.add(CommentBean())

                loadmoreUtils?.onSuccess(list)
//                loadmoreUtils?.onFail(adapter,"")
            }
        }
        loadmoreUtils?.refresh()
    }

    fun reflushDatas() {
        loadmoreUtils?.refresh()
    }

}