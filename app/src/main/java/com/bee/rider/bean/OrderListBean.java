package com.bee.rider.bean;

import java.util.List;

/**
 * 创建时间：2022/3/5
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
public class OrderListBean {

    public Integer current;
    public Boolean hitCount;
    public Integer pages;
    public List<RecordsBean> records;
    public Boolean searchCount;
    public Integer size;
    public Integer total;

    public static class RecordsBean {
        public Integer id;
        public Integer horsemanId;
        public Integer takeoutId;
        public Integer orderId;
        public String deliverySn;
        public Object expectedTime;
        public Object accomplishTime;
        public Integer storeId;
        public String storeName;
        public String storeContactMobile;
        public String storeAddressDetail;
        public String storeLongitude;
        public String storeLatitude;
        public String linkman;
        public String phone;
        public String buildingName;
        public Integer floor;
        public String detailAddress;
    }
}
