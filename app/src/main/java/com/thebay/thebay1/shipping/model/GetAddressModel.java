package com.thebay.thebay1.shipping.model;

/**
 * Created by kyoungae on 2017-10-20.
 */

public class GetAddressModel {
    private String AddrSeq;
    private String AdrsKr;
    private String AdrsEn;
    private String Zip;
    private String Addr1;
    private String Addr2;
    private String Addr1En;
    private String Addr2En;
    private String MobNo;
    private String RrnNo;
    private String RrnCd;
    private String MainYn;

    public GetAddressModel(String addrSeq, String adrsKr, String adrsEn, String zip, String addr1, String addr2, String addr1En, String addr2En, String mobNo, String rrnNo, String rrnCd, String mainYn) {
        AddrSeq = addrSeq;
        AdrsKr = adrsKr;
        AdrsEn = adrsEn;
        Zip = zip;
        Addr1 = addr1;
        Addr2 = addr2;
        Addr1En = addr1En;
        Addr2En = addr2En;
        MobNo = mobNo;
        RrnNo = rrnNo;
        RrnCd = rrnCd;
        MainYn = mainYn;
    }

    public String getAddrSeq() {
        return AddrSeq;
    }

    public void setAddrSeq(String addrSeq) {
        AddrSeq = addrSeq;
    }

    public String getAdrsKr() {
        return AdrsKr;
    }

    public void setAdrsKr(String adrsKr) {
        AdrsKr = adrsKr;
    }

    public String getAdrsEn() {
        return AdrsEn;
    }

    public void setAdrsEn(String adrsEn) {
        AdrsEn = adrsEn;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getAddr1() {
        return Addr1;
    }

    public void setAddr1(String addr1) {
        Addr1 = addr1;
    }

    public String getAddr2() {
        return Addr2;
    }

    public void setAddr2(String addr2) {
        Addr2 = addr2;
    }

    public String getAddr1En() {
        return Addr1En;
    }

    public void setAddr1En(String addr1En) {
        Addr1En = addr1En;
    }

    public String getAddr2En() {
        return Addr2En;
    }

    public void setAddr2En(String addr2En) {
        Addr2En = addr2En;
    }

    public String getMobNo() {
        return MobNo;
    }

    public void setMobNo(String mobNo) {
        MobNo = mobNo;
    }

    public String getRrnNo() {
        return RrnNo;
    }

    public void setRrnNo(String rrnNo) {
        RrnNo = rrnNo;
    }

    public String getRrnCd() {
        return RrnCd;
    }

    public void setRrnCd(String rrnCd) {
        RrnCd = rrnCd;
    }

    public String getMainYn() {
        return MainYn;
    }

    public void setMainYn(String mainYn) {
        MainYn = mainYn;
    }

    @Override
    public String toString() {
        return "GetAddressModel{" +
                "AddrSeq='" + AddrSeq + '\'' +
                ", AdrsKr='" + AdrsKr + '\'' +
                ", AdrsEn='" + AdrsEn + '\'' +
                ", Zip='" + Zip + '\'' +
                ", Addr1='" + Addr1 + '\'' +
                ", Addr2='" + Addr2 + '\'' +
                ", Addr1En='" + Addr1En + '\'' +
                ", Addr2En='" + Addr2En + '\'' +
                ", MobNo='" + MobNo + '\'' +
                ", RrnNo='" + RrnNo + '\'' +
                ", RrnCd='" + RrnCd + '\'' +
                ", MainYn='" + MainYn + '\'' +
                '}';
    }
}
