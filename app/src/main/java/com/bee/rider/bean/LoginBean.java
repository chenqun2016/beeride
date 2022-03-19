package com.bee.rider.bean;

import java.util.List;

/**
 * 创建时间：2022/3/5
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
public class LoginBean {

    public String token;
    public int id;
    public String accountNo;
    public Object levelId;
    public String password;
    public int type;
    public int authType;
    public int isWork;
    public String name;
    public Boolean sex;
    public String linkPhone;
    public String idCard;
    public String buildingArea;
    public Object aid;
    public Object buildingAreaId;
    public Object areaId;
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
    public List<PictureListBean> pictureList;

    public static class PictureListBean {
        public int id;
        public int horsemanId;
        public int fileId;
        public String type;
    }
}
