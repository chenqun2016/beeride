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

    public Integer id;
    public Integer memberId;
    public String orderSn;
    public Integer sourceType;
    public Integer orderType;
    public Integer totalAmount;
    public Integer payAmount;
    public Integer feightTemplateDetailId;
    public Integer freightAmount;
    public Object packingFeeAmount;
    public String status;
    public Integer isPay;
    public Object paymentTime;
    public Integer payType;
    public Object promotionInfo;
    public Object couponId;
    public Integer promotionAmount;
    public Integer integrationAmount;
    public Integer couponAmount;
    public Integer discountAmount;
    public Object autoConfirmDay;
    public Integer integration;
    public Integer growth;
    public Integer billType;
    public Object billHeader;
    public Object billContent;
    public Object billReceiverPhone;
    public Object billReceiverEmail;
    public String receiverName;
    public String receiverPhone;
    public String receiverPostCode;
    public String receiverDetailAddress;
    public String note;
    public Integer deleteStatus;
    public Object useIntegration;
    public Object deliverySn;
    public String deliveryTime;
    public Integer confirmStatus;
    public Object receiveTime;
    public Object commentTime;
    public Object modifyTime;
    public Integer buildingId;
    public String buildingName;
    public Integer storeId;
    public String storeName;
    public Boolean comment;
    public String receiverProvince;
    public String receiverCity;
    public String receiverRegion;
    public Integer pickupWay;
    public Integer createBy;
    public String createName;
    public Date createTime;
    public Integer updateBy;
    public String updateName;
    public String updateTime;
    public Object blance;
    public List<OrderItemListBean> orderItemList;
    public List<HistoryListBean> historyList;
    public ShopStoreDetailVOBean shopStoreDetailVO;
    public Object disHorseman;
    public DisTakeoutBean disTakeout;

    public static class ShopStoreDetailVOBean {
        public Integer id;
        public String loginname;
        public Object password;
        public String name;
        public String subtitle;
        public Integer brandId;
        public String brandName;
        public String logoUrl;
        public String appBackgroudUrl;
        public String businessTimes;
        public Integer storeLevel;
        public String storeType;
        public String storeLabel;
        public String contactName;
        public String contactMobile;
        public Integer areaId;
        public Integer buildingAreaId;
        public String addressDetail;
        public String longitude;
        public String latitude;
        public Object userStatus;
        public Integer isShow;
        public String description;
        public Integer status;
        public Integer feightTemplateId;
        public ShopStoreExtVOBean shopStoreExtVO;
        public Object industryCategoryVOS;
        public Object permissionCategoryVOS;
        public List<?> shopStorePhotoAlbumVOS;
        public List<?> shopStoreCertVOS;

        public static class ShopStoreExtVOBean {
            public Integer id;
            public Integer storeId;
            public String companyName;
            public String legalPerson;
            public String idNumber;
            public String businessLicenseNo;
            public String licenseKey;
            public String registeredAddress;
        }
    }

    public static class DisTakeoutBean {
        public Integer id;
        public String allocationRuleId;
        public String sorterSn;
        public Integer orderId;
        public Object storeId;
        public Object storeName;
        public Integer deliveryAddressId;
        public Integer status;
        public Boolean type;
        public String deliverySn;
        public Object expectedTime;
        public Object accomplishTime;
        public Integer delFlag;
        public Object createBy;
        public Object createName;
        public String createTime;
        public Object updateBy;
        public Object updateName;
        public Object updateTime;
    }

    public static class OrderItemListBean {
        public Integer id;
        public Integer orderId;
        public String orderSn;
        public Integer productId;
        public Integer flashSaleGoodsId;
        public String productPic;
        public String productName;
        public String productBrand;
        public String productSn;
        public Integer productPrice;
        public Integer productQuantity;
        public Integer productSkuId;
        public String productSkuCode;
        public Integer productCategoryId;
        public Object sp1;
        public Object sp2;
        public Object sp3;
        public String productAttr;
        public Object promotionName;
        public Integer promotionAmount;
        public Integer couponAmount;
        public Integer integrationAmount;
        public Integer realAmount;
        public Integer giftIntegration;
        public Integer giftGrowth;
        public Integer storeId;
        public String storeName;
        public Object status;
        public Integer type;
        public Integer createBy;
        public String createName;
        public String createTime;
        public Integer updateBy;
        public String updateName;
        public String updateTime;
    }

    public static class HistoryListBean {
        public Integer id;
        public Integer orderId;
        public Integer orderItemId;
        public String operateMan;
        public String orderStatus;
        public Object note;
        public Object preStatus;
        public Integer createBy;
        public String createName;
        public String createTime;
    }
}
