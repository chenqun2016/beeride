package com.bee.rider.bean;

import java.util.List;

/**
 * 创建时间：2022/7/23
 * 编写人： 陈陈陈
 * 功能描述：
 */
public class StatisticBean {

    public String disOrderTotal;
    public String praiseRate;
    public String deliveryPraiseRate;
    public String disOrderToday;
    public String totalRate;
    public List<Details> details;

    public static class Details {
        public String successOrder;
        public String cancleOrder;
        public String arriveLateOrder;
        public String ranking;
        //日期类型标识 ，today，month,year
        public String dateMark;
    }
}
