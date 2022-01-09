package com.chenchen.bee_rider.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chenchen.bee_rider.R

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailTraceAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_order_detail_trace) {
    override fun convert(holder: BaseViewHolder, item: String) {
        val current = 2
        val title = holder.getView<TextView>(R.id.step_title)
        title.text = item
        if(holder.layoutPosition == current){
            title.context.resources.getColor(R.color.color_FF6200)
        }else{
            title.context.resources.getColor(R.color.color_222222)
        }
        title.setTextColor(title.context.resources.getColor(R.color.color_222222))
        holder.getView<TextView>(R.id.step_time).text = "2020-1-9"

        val dian = holder.getView<ImageView>(R.id.dian)
        val line = holder.getView<View>(R.id.left_line)
        line.visibility = if (holder.layoutPosition == 0) View.GONE else View.VISIBLE
        if(holder.layoutPosition <= current){
            line.setBackgroundColor(line.resources.getColor(R.color.color_FF6200))
            dian.setImageResource(R.drawable.point_yellow)
        }else{
            line.setBackgroundColor(line.resources.getColor(R.color.color_d8d8d8))
            dian.setImageResource(R.drawable.point_grey)
        }

    }
}