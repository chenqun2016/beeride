package com.bee.rider.bean;

import java.util.List;

/**
 * 创建时间：2022/2/20
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
public class UserBean {

    public int id;
    public String accountNo;
    public int levelId;
    public String password;
    public int type;
    public int authType;
    public int isWork;
    public String name;
    public int sex;
    public String linkPhone;
    public String idCard;
    public String buildingArea;
    public Object aid;
    public int province;
    public int city;
    public int district;
    public Object buildingId;
    public int buildingAreaId;
    public int areaId;
    public String emergencyContactName;
    public String emergencyContactPhone;
    public String emergencyContactAddress;
    public String emergencyContactRelationship;
    public Object status;
    public String createTime;
    public String levelName;
    public Object transactionAmount;
    public int orderCount;
    public int userData;
    public int returnCount;
    public int loginTimes;
    public List<PictureList> pictureList;
    public String token;

    public static class PictureList {
        public int id;
        public int horsemanId;
        public int fileId;
        public String type;
    }
}

