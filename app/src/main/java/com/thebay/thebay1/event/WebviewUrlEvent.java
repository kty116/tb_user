package com.thebay.thebay1.event;

/**
 * Created by kyoungae on 2017-10-31.
 */

public class WebviewUrlEvent implements MessageEvent{
    private String url;

    public WebviewUrlEvent(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WebviewUrlEvent{" +
                "url='" + url + '\'' +
                '}';
    }
}
