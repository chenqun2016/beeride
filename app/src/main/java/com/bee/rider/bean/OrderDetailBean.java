package com.bee.rider.bean;

import java.util.List;

/**
 * 创建时间：2022/3/5
 *
 * @Author： 陈陈陈
 * 功能描述：
 */
public class OrderDetailBean {

    public Integer autoConfirmDay;
    public String billContent;
    public String billHeader;
    public String billReceiverEmail;
    public String billReceiverPhone;
    public Integer billType;
    public Integer blance;
    public Integer buildingId;
    public String buildingName;
    public Boolean comment;
    public String commentTime;
    public Integer confirmStatus;
    public Integer couponAmount;
    public Integer couponId;
    public Integer createBy;
    public String createName;
    public String createTime;
    public Integer deleteStatus;
    public String deliverySn;
    public String deliveryTime;
    public Integer discountAmount;
    public Integer feightTemplateDetailId;
    public Integer freightAmount;
    public Integer growth;
    public List<HistoryListBean> historyList;
    public Integer id;
    public Integer integration;
    public Integer integrationAmount;
    public Integer isPay;
    public Integer memberId;
    public String modifyTime;
    public String note;
    public List<OrderItemListBean> orderItemList;
    public String orderSn;
    public Integer orderType;
    public Integer payAmount;
    public Integer payType;
    public String paymentTime;
    public Integer pickupWay;
    public Integer promotionAmount;
    public String promotionInfo;
    public String receiveTime;
    public String receiverCity;
    public String receiverDetailAddress;
    public String receiverName;
    public String receiverPhone;
    public String receiverPostCode;
    public String receiverProvince;
    public String receiverRegion;
    public ShopStoreDetailVOBean shopStoreDetailVO;
    public Integer sourceType;
    public String status;
    public Integer storeId;
    public String storeName;
    public Integer totalAmount;
    public Integer updateBy;
    public String updateName;
    public String updateTime;
    public Integer useIntegration;

    public static class ShopStoreDetailVOBean {
        public String addressDetail;
        public String appBackgroudUrl;
        public Integer areaId;
        public Integer brandId;
        public String brandName;
        public Integer buildingAreaId;
        public String businessTimes;
        public String contactMobile;
        public String contactName;
        public String description;
        public Integer feightTemplateId;
        public Integer id;
        public List<IndustryCategoryVOSBean> industryCategoryVOS;
        public Integer isShow;
        public String latitude;
        public String loginname;
        public String logoUrl;
        public String longitude;
        public String name;
        public String password;
        public List<PermissionCategoryVOSBean> permissionCategoryVOS;
        public List<ShopStoreCertVOSBean> shopStoreCertVOS;
        public ShopStoreExtVOBean shopStoreExtVO;
        public List<ShopStorePhotoAlbumVOSBean> shopStorePhotoAlbumVOS;
        public Integer status;
        public String storeLabel;
        public Integer storeLevel;
        public String storeType;
        public String subtitle;
        public Integer userStatus;

        public static class ShopStoreExtVOBean {
            public String businessLicenseNo;
            public String companyName;
            public Integer id;
            public String idNumber;
            public String legalPerson;
            public String licenseKey;
            public String registeredAddress;
            public Integer storeId;
        }

        public static class IndustryCategoryVOSBean {
            public Integer id;
            public Integer industryCategoryId;
            public Integer showStatus;
            public Integer sort;
            public Integer storeId;
        }

        public static class PermissionCategoryVOSBean {
            public Integer id;
            public Integer productCategoryId;
            public String productCategoryName;
            public Integer storeId;
        }

        public static class ShopStoreCertVOSBean {
            public Integer fileId;
            public String filename;
            public Integer id;
            public String isMainPic;
            public Integer sort;
            public Integer storeId;
            public String type;
            public String url;
        }

        public static class ShopStorePhotoAlbumVOSBean {
            public Integer fileId;
            public String filename;
            public Integer id;
            public String isMainPic;
            public Integer sort;
            public Integer storeId;
            public String type;
            public String url;
        }
    }

    public static class HistoryListBean {
        public Integer createBy;
        public String createName;
        public String createTime;
        public Integer id;
        public String note;
        public String operateMan;
        public Integer orderId;
        public Integer orderItemId;
        public String orderStatus;
        public String preStatus;
    }

    public static class OrderItemListBean {
        public Integer couponAmount;
        public Integer createBy;
        public String createName;
        public String createTime;
        public Integer giftGrowth;
        public Integer giftIntegration;
        public Integer id;
        public Integer integrationAmount;
        public Integer orderId;
        public String orderSn;
        public String productAttr;
        public String productBrand;
        public Integer productCategoryId;
        public Integer productId;
        public String productName;
        public String productPic;
        public Integer productPrice;
        public Integer productQuantity;
        public String productSkuCode;
        public Integer productSkuId;
        public String productSn;
        public Integer promotionAmount;
        public String promotionName;
        public Integer realAmount;
        public String sp1;
        public String sp2;
        public String sp3;
        public Integer status;
        public Integer storeId;
        public String storeName;
        public Integer type;
        public Integer updateBy;
        public String updateName;
        public String updateTime;
    }
}
