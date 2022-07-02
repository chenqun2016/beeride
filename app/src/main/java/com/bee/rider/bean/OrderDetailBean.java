package com.bee.rider.bean;

import java.util.Date;
import java.util.List;

/**
 * 创建时间：2022/3/5
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
public class OrderDetailBean {

    public int queryStatus;
    public int id;
    public int memberId;
    public String orderSn;
    public int sourceType;
    public int orderType;
    public int totalAmount;
    public int payAmount;
    public int feightTemplateDetailId;
    public int freightAmount;
    public Object packingFeeAmount;
    public String status;
    public int isPay;
    public Object paymentTime;
    public int payType;
    public Object promotionInfo;
    public Object couponId;
    public int promotionAmount;
    public int integrationAmount;
    public int couponAmount;
    public int discountAmount;
    public Object autoConfirmDay;
    public int integration;
    public int growth;
    public int billType;
    public Object billHeader;
    public Object billContent;
    public Object billReceiverPhone;
    public Object billReceiverEmail;
    public String receiverName;
    public String receiverPhone;
    public String receiverPostCode;
    public String receiverDetailAddress;
    public String note;
    public int deleteStatus;
    public Object useIntegration;
    public Object deliverySn;
    public String deliveryTime;
    public int confirmStatus;
    public Date receiveTime;
    public Object commentTime;
    public Object modifyTime;
    public int buildingId;
    public String buildingName;
    public int storeId;
    public String storeName;
    public Boolean comment;
    public String receiverProvince;
    public String receiverCity;
    public String receiverRegion;
    public int pickupWay;
    public int createBy;
    public String createName;
    public Date createTime;
    public int updateBy;
    public String updateName;
    public String updateTime;
    public Object blance;
    public List<OrderItemListBean> orderItemList;
    public List<HistoryListBean> historyList;
    public ShopStoreDetailVOBean shopStoreDetailVO;
    public Object disHorseman;
    public DisTakeoutBean disTakeout;

    public static class ShopStoreDetailVOBean {
        public int id;
        public String loginname;
        public Object password;
        public String name;
        public String subtitle;
        public int brandId;
        public String brandName;
        public String logoUrl;
        public String appBackgroudUrl;
        public String businessTimes;
        public int storeLevel;
        public String storeType;
        public String storeLabel;
        public String contactName;
        public String contactMobile;
        public int areaId;
        public int buildingAreaId;
        public String addressDetail;
        public String longitude;
        public String latitude;
        public Object userStatus;
        public int isShow;
        public String description;
        public int status;
        public int feightTemplateId;
        public ShopStoreExtVOBean shopStoreExtVO;
        public Object industryCategoryVOS;
        public Object permissionCategoryVOS;
        public List<?> shopStorePhotoAlbumVOS;
        public List<?> shopStoreCertVOS;

        public static class ShopStoreExtVOBean {
            public int id;
            public int storeId;
            public String companyName;
            public String legalPerson;
            public String idNumber;
            public String businessLicenseNo;
            public String licenseKey;
            public String registeredAddress;
        }
    }

    public static class DisTakeoutBean {
        public int id;
        public String allocationRuleId;
        public String sorterSn;
        public long orderId;
        public Object storeId;
        public Object storeName;
        public int deliveryAddressId;
        public int status;
        public Boolean type;
        public String deliverySn;
        public Date expectedTime;
        public Object accomplishTime;
        public int delFlag;
        public Object createBy;
        public Object createName;
        public String createTime;
        public Object updateBy;
        public Object updateName;
        public Object updateTime;
    }

    public static class OrderItemListBean {
        public int id;
        public long orderId;
        public String orderSn;
        public int productId;
        public int flashSaleGoodsId;
        public String productPic;
        public String productName;
        public String productBrand;
        public String productSn;
        public int productPrice;
        public int productQuantity;
        public int productSkuId;
        public String productSkuCode;
        public int productCategoryId;
        public Object sp1;
        public Object sp2;
        public Object sp3;
        public String productAttr;
        public Object promotionName;
        public int promotionAmount;
        public int couponAmount;
        public int integrationAmount;
        public int realAmount;
        public int giftIntegration;
        public int giftGrowth;
        public int storeId;
        public String storeName;
        public Object status;
        public int type;
        public int createBy;
        public String createName;
        public String createTime;
        public int updateBy;
        public String updateName;
        public String updateTime;
    }

    public static class HistoryListBean {
        public int id;
        public long orderId;
        public int orderItemId;
        public String operateMan;
        public String orderStatus;
        public Object note;
        public Object preStatus;
        public int createBy;
        public String createName;
        public String createTime;
    }
}
