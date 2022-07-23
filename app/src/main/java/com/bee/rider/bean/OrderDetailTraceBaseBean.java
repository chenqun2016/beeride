package com.bee.rider.bean;

import java.util.List;

/**
 * 创建时间：2022/7/23
 * 编写人： 陈陈陈
 * 功能描述：
 */
public class OrderDetailTraceBaseBean {

    public int orderId;
    public String deliverySn;
    public String deliveryTime;
    public String deliveryType;
    public List<TrackList> trackList;

    public static class TrackList {
        public String title;
        public String time;
        public Object memo;
        public int status;
    }
}
