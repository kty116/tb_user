package com.thebay.thebay1.shipping.model;

/**
 * Created by kyoungae on 2017-09-12.
 */

public class ShippingProductModel {
    private String shoppingMall; //쇼핑몰 url
    private String orderNumber;  //주문번호
    private String buyer;  //구매자
    private int categoryIndex;  //품목 카테고리
    //상품명 빠짐 넣어야됨
    private String productName;
    private String trackingNumber;  //트래킹번호
    private String price;  //금액
    private String quantity;  //수량
    private String color;  //색상
    private String size;  //사이즈
    private String goodsUrl;  //상품 url
    private String imageUrl;  //이미지 url

    public ShippingProductModel(String shoppingMall, String orderNumber, String buyer, int categoryIndex, String productName, String trackingNumber, String price, String quantity, String color, String size, String goodsUrl, String imageUrl) {
        this.shoppingMall = shoppingMall;
        this.orderNumber = orderNumber;
        this.buyer = buyer;
        this.categoryIndex = categoryIndex;
        this.productName = productName;
        this.trackingNumber = trackingNumber;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
        this.goodsUrl = goodsUrl;
        this.imageUrl = imageUrl;
    }

    public String getShoppingMall() {
        return shoppingMall;
    }

    public void setShoppingMall(String shoppingMall) {
        this.shoppingMall = shoppingMall;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public void setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ShippingProductModel{" +
                "shoppingMall='" + shoppingMall + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", buyer='" + buyer + '\'' +
                ", categoryIndex=" + categoryIndex +
                ", productName='" + productName + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", goodsUrl='" + goodsUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
