package com.bee.rider.bean;

import java.util.Date;
import java.util.List;

/**
 * 创建时间：2022/3/5
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
public class OrderListBean {

    public int current;
    public Boolean hitCount;
    public int pages;
    public List<RecordsBean> records;
    public Boolean searchCount;
    public int size;
    public int total;

    public static class RecordsBean {
        public int id;
        public int horsemanId;
        public int takeoutId;
        public long orderId;
        public String deliverySn;
        public Date expectedTime;
        public Object accomplishTime;
        public int storeId;
        public String storeName;
        public String storeContactMobile;
        public String storeAddressDetail;
        public String storeLongitude;
        public String storeLatitude;
        public String linkman;
        public String phone;
        public String buildingName;
        public int floor;
        public String detailAddress;
    }
}
