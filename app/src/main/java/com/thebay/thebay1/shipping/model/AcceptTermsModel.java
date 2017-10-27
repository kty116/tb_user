package com.thebay.thebay1.shipping.model;

/**
 * Created by kyoungae on 2017-10-20.
 */

public class AcceptTermsModel {
    private String CtrSeq;
    private String CtrCd;
    private String CtrNm;
    private String CtrNmCn;
    private String Addr;

    public AcceptTermsModel(String ctrSeq, String ctrCd, String ctrNm, String ctrNmCn, String addr) {
        CtrSeq = ctrSeq;
        CtrCd = ctrCd;
        CtrNm = ctrNm;
        CtrNmCn = ctrNmCn;
        Addr = addr;
    }

    public String getCtrSeq() {
        return CtrSeq;
    }

    public void setCtrSeq(String ctrSeq) {
        CtrSeq = ctrSeq;
    }

    public String getCtrCd() {
        return CtrCd;
    }

    public void setCtrCd(String ctrCd) {
        CtrCd = ctrCd;
    }

    public String getCtrNm() {
        return CtrNm;
    }

    public void setCtrNm(String ctrNm) {
        CtrNm = ctrNm;
    }

    public String getCtrNmCn() {
        return CtrNmCn;
    }

    public void setCtrNmCn(String ctrNmCn) {
        CtrNmCn = ctrNmCn;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    @Override
    public String toString() {
        return "AcceptTermsModel{" +
                "CtrSeq='" + CtrSeq + '\'' +
                ", CtrCd='" + CtrCd + '\'' +
                ", CtrNm='" + CtrNm + '\'' +
                ", CtrNmCn='" + CtrNmCn + '\'' +
                ", Addr='" + Addr + '\'' +
                '}';
    }
}
