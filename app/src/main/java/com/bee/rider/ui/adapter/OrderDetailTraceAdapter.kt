package com.bee.rider.ui.adapter

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chenchen.base.utils.DisplayUtil
import com.bee.rider.R
import com.bee.rider.bean.OrderDetailTraceBaseBean

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class OrderDetailTraceAdapter :
    BaseQuickAdapter<OrderDetailTraceBaseBean.TrackList, BaseViewHolder>(R.layout.item_order_detail_trace) {
    override fun convert(holder: BaseViewHolder, item: OrderDetailTraceBaseBean.TrackList) {
        val title = holder.getView<TextView>(R.id.step_title)
        title.text = item.title
//        if(holder.layoutPosition == current){
//            title.setTextColor(ContextCompat.getColor(title.context,R.color.color_FF6200))
//        }else{
//            title.setTextColor(ContextCompat.getColor(title.context,R.color.color_222222))
//        }
        title.setTextColor(title.context.resources.getColor(R.color.color_222222))
        holder.getView<TextView>(R.id.step_time).text = item.time

        val dian = holder.getView<ImageView>(R.id.dian)
        val line = holder.getView<View>(R.id.left_line)
        line.visibility = if (holder.layoutPosition == data.size-1) View.GONE else View.VISIBLE
        if(item.status == 1){
            line.setBackgroundColor(line.resources.getColor(R.color.color_FF6200))
            dian.setImageResource(R.drawable.point_yellow)
        }else{
            line.setBackgroundColor(line.resources.getColor(R.color.color_d8d8d8))
            dian.setImageResource(R.drawable.point_grey)
        }
        try {
            if((item.status == 1 && holder.layoutPosition == data.size-1) ||
                (item.status == 1 && holder.layoutPosition-1>=0 && data[holder.layoutPosition-1].status==0)){

                val scaleAnimation = ScaleAnimation(1.0f, 0.3f, 1.0f, 0.3f,DisplayUtil.dip2px(dian.context,8f).toFloat(),DisplayUtil.dip2px(dian.context,8f).toFloat())
                scaleAnimation.repeatCount = -1
                scaleAnimation.repeatMode = Animation.REVERSE
                scaleAnimation.duration = 600
                dian.startAnimation(scaleAnimation)
            }else{
                dian.clearAnimation()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}