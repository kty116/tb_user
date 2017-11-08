package com.thebay.thebay1.event;

/**
 * Created by kyoungae on 2017-10-27.
 */

public class SignUpSelectedAddressEvent implements MessageEvent {
    private String post;
    private String address;

    public SignUpSelectedAddressEvent(String post, String address) {
        this.post = post;
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
