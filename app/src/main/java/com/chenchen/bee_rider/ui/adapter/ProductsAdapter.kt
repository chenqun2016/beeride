package com.chenchen.bee_rider.ui.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chenchen.bee_rider.R

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class ProductsAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_ordering_food){
    override fun convert(holder: BaseViewHolder, item: String) {

        holder.getView<TextView>(R.id.iv_goods_name).text = "唐师傅金牌牛肉面"
    }
}