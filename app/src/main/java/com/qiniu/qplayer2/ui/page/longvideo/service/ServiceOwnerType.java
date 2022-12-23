package com.qiniu.qplayer2.ui.page.longvideo.service;

public enum  ServiceOwnerType {
    PLAYER_CONTROL_PANEL_CONTATINER_VISIBLE_SERVICE("PlayerControlPanelContainerVisibleService"),
    PLAYER_TOAST_SERVICE("PlayerToastService"),
    PLAYER_BUFFERING_SERVICE("PlayerBufferingService"),
    PLAYER_NETWORK_SERVICE("PlayerNetworkService"),
    PLAYER_PANORAMA_TOUCH_SERVICE("PlayerPanoramaTouchSerivice");
    public String type;
    ServiceOwnerType(String type) {
        this.type=type;
    }
}
