package com.bee.rider.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import com.bee.rider.R
import com.bee.rider.bean.OrderDetailBean
import com.bee.rider.utils.PicassoRoundTransform
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chenchen.base.utils.DisplayUtil
import com.squareup.picasso.Picasso

/**
 * 创建时间：2022/1/9
 * @Author： 陈陈陈
 * 功能描述：
 */
class ProductsAdapter : BaseQuickAdapter<OrderDetailBean.OrderItemListBean, BaseViewHolder>(R.layout.item_ordering_food){
    override fun convert(holder: BaseViewHolder, item: OrderDetailBean.OrderItemListBean) {
        Picasso.with(context)
            .load(item.productPic)
            .fit()
            .transform(
                PicassoRoundTransform(
                    DisplayUtil.dip2px(context, 10f),
                    0,
                    PicassoRoundTransform.CornerType.ALL
                )
            )
            .into(holder.getView<ImageView>(R.id.iv_goods_img))
        holder.getView<TextView>(R.id.iv_goods_name).text = item.productName

//        val iv_goods_price_past =holder.getView<TextView>(R.id.iv_goods_price_past)
//        DisplayUtil.setXiexian(iv_goods_price_past)
        holder.setText(R.id.iv_goods_price,"¥${item.productPrice}")
        holder.setText(R.id.iv_goods_num,"x${item.productQuantity}")
        holder.setText(R.id.iv_goods_detail,item.productAttr)

    }
}