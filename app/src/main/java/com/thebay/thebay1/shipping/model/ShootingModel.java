package com.thebay.thebay1.shipping.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kyoungae on 2017-09-12.
 */

public class ShootingModel {
    private String shoppingMall; //쇼핑몰 url
    private String orderNumber;  //주문번호
    private String buyer;  //구매자
    private String category;  //품목 카테고리
    //상품명 빠짐 넣어야됨
    private String trackingNumber;  //트래킹번호
    private boolean isTrackingNumberNotEntered;  //트래킹번호 나중에 입력 체크박스
    private String price;  //금액
    private String quantity;  //수량
    private String color;  //색상
    private String size;  //사이즈
    private String goodsUrl;  //상품 url
    private String imageUrl;  //이미지 url
    private String bringImage; //핸드폰 이미지 등록
    private HashMap<Integer, String> newImageFileMap;
    private ArrayList<String> existingImageFileList;

    public ShootingModel(String shoppingMall, String orderNumber, String buyer, String category, String trackingNumber, boolean isTrackingNumberNotEntered, String price, String quantity, String color, String size, String goodsUrl, String imageUrl, String bringImage, HashMap<Integer, String> newImageFileMap, ArrayList<String> existingImageFileList) {
        this.shoppingMall = shoppingMall;
        this.orderNumber = orderNumber;
        this.buyer = buyer;
        this.category = category;
        this.trackingNumber = trackingNumber;
        this.isTrackingNumberNotEntered = isTrackingNumberNotEntered;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
        this.goodsUrl = goodsUrl;
        this.imageUrl = imageUrl;
        this.bringImage = bringImage;
        this.newImageFileMap = newImageFileMap;
        this.existingImageFileList = existingImageFileList;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public boolean isTrackingNumberNotEntered() {
        return isTrackingNumberNotEntered;
    }

    public void setTrackingNumberNotEntered(boolean trackingNumberNotEntered) {
        isTrackingNumberNotEntered = trackingNumberNotEntered;
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

    public String getBringImage() {
        return bringImage;
    }

    public void setBringImage(String bringImage) {
        this.bringImage = bringImage;
    }

    public HashMap<Integer, String> getNewImageFileMap() {
        return newImageFileMap;
    }

    public void setNewImageFileMap(HashMap<Integer, String> newImageFileMap) {
        this.newImageFileMap = newImageFileMap;
    }

    public ArrayList<String> getExistingImageFileList() {
        return existingImageFileList;
    }

    public void setExistingImageFileList(ArrayList<String> existingImageFileList) {
        this.existingImageFileList = existingImageFileList;
    }

    @Override
    public String toString() {
        return "ShootingModel{" +
                "shoppingMall='" + shoppingMall + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", buyer='" + buyer + '\'' +
                ", category='" + category + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", isTrackingNumberNotEntered=" + isTrackingNumberNotEntered +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", goodsUrl='" + goodsUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", bringImage='" + bringImage + '\'' +
                ", newImageFileMap=" + newImageFileMap +
                ", existingImageFileList=" + existingImageFileList +
                '}';
    }
}
