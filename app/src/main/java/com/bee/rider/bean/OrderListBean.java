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
        public String accomplishTime;
        public String buildingName;
        public String deliverySn;
        public String detailAddress;
        public String expectedTime;
        public Integer floor;
        public Integer id;
        public String linkman;
        public Integer orderId;
        public String phone;
        public String storeAddressDetail;
        public String storeContactMobile;
        public Integer storeId;
        public String storeLatitude;
        public String storeLongitude;
        public String storeName;
    }
}
