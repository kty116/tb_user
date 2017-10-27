package com.thebay.thebay1.frag_my_home;

/**
 * Created by kyoungae on 2017-10-19.
 */

public class homeModel {
    private String MemNmKr;
    private String MemLvl;
    private String MemLvlNm;
    private String MyPost;
    private String MyDpst;
    private String PaymentCnt;
    private String PaymentMny;
    private String NtCnt;

    public homeModel(String memNmKr, String memLvl, String memLvlNm, String myPost, String myDpst, String paymentCnt, String paymentMny, String ntCnt) {
        MemNmKr = memNmKr;
        MemLvl = memLvl;
        MemLvlNm = memLvlNm;
        MyPost = myPost;
        MyDpst = myDpst;
        PaymentCnt = paymentCnt;
        PaymentMny = paymentMny;
        NtCnt = ntCnt;
    }

    public String getMemNmKr() {
        return MemNmKr;
    }

    public void setMemNmKr(String memNmKr) {
        MemNmKr = memNmKr;
    }

    public String getMemLvl() {
        return MemLvl;
    }

    public void setMemLvl(String memLvl) {
        MemLvl = memLvl;
    }

    public String getMemLvlNm() {
        return MemLvlNm;
    }

    public void setMemLvlNm(String memLvlNm) {
        MemLvlNm = memLvlNm;
    }

    public String getMyPost() {
        return MyPost;
    }

    public void setMyPost(String myPost) {
        MyPost = myPost;
    }

    public String getMyDpst() {
        return MyDpst;
    }

    public void setMyDpst(String myDpst) {
        MyDpst = myDpst;
    }

    public String getPaymentCnt() {
        return PaymentCnt;
    }

    public void setPaymentCnt(String paymentCnt) {
        PaymentCnt = paymentCnt;
    }

    public String getPaymentMny() {
        return PaymentMny;
    }

    public void setPaymentMny(String paymentMny) {
        PaymentMny = paymentMny;
    }

    public String getNtCnt() {
        return NtCnt;
    }

    public void setNtCnt(String ntCnt) {
        NtCnt = ntCnt;
    }

    @Override
    public String toString() {
        return "homeModel{" +
                "MemNmKr='" + MemNmKr + '\'' +
                ", MemLvl='" + MemLvl + '\'' +
                ", MemLvlNm='" + MemLvlNm + '\'' +
                ", MyPost='" + MyPost + '\'' +
                ", MyDpst='" + MyDpst + '\'' +
                ", PaymentCnt='" + PaymentCnt + '\'' +
                ", PaymentMny='" + PaymentMny + '\'' +
                ", NtCnt='" + NtCnt + '\'' +
                '}';
    }
}
