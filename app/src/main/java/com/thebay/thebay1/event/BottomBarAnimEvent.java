package com.thebay.thebay1.event;

/**
 * Created by kyoungae on 2017-10-18.
 */

public class BottomBarAnimEvent implements MessageEvent{
    private boolean isShow;

    public BottomBarAnimEvent(boolean isShow) {
        this.isShow = isShow;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
