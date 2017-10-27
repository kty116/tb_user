package com.thebay.thebay1.main;

/**
 * Created by kyoungae on 2017-09-12.
 */

public class ScanInStockModel {
    private String orderNumber;  //주문번호
    private String trackingNumber;  //트래킹번호
    private String takeTime;  //수취시간
    private String sectionNumber;  //섹션번호
    private String noDataSectionNumber;  //노데이타 섹션번호
    private String mailboxNumber;  //사서함번호

    public ScanInStockModel(String orderNumber, String trackingNumber, String takeTime, String sectionNumber, String noDataSectionNumber, String mailboxNumber) {
        this.orderNumber = orderNumber;
        this.trackingNumber = trackingNumber;
        this.takeTime = takeTime;
        this.sectionNumber = sectionNumber;
        this.noDataSectionNumber = noDataSectionNumber;
        this.mailboxNumber = mailboxNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getNoDataSectionNumber() {
        return noDataSectionNumber;
    }

    public void setNoDataSectionNumber(String noDataSectionNumber) {
        this.noDataSectionNumber = noDataSectionNumber;
    }

    public String getMailboxNumber() {
        return mailboxNumber;
    }

    public void setMailboxNumber(String mailboxNumber) {
        this.mailboxNumber = mailboxNumber;
    }
}
