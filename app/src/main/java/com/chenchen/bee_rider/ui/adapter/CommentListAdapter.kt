package com.chenchen.bee_rider.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chenchen.bee_rider.R
import com.chenchen.bee_rider.bean.CommentBean

/**
 * 创建时间：2022/2/4
 * @Author： 陈陈陈
 * 功能描述：
 */
class CommentListAdapter  : BaseQuickAdapter<CommentBean, BaseViewHolder>(R.layout.item_comment),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: CommentBean) {

    }
}