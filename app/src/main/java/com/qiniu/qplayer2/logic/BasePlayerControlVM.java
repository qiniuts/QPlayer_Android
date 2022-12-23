package com.qiniu.qplayer2.logic;

import com.qiniu.qmedia.component.player.QPlayerControlHandler;

public abstract class BasePlayerControlVM {
    public QPlayerControlHandler playerControlHandler;
    public void setQPlayerControlHandler(QPlayerControlHandler playerControlHandler){
        this.playerControlHandler=playerControlHandler;
        onSetPlayerControlHandler(this.playerControlHandler);
    }
    public abstract void onSetPlayerControlHandler(QPlayerControlHandler controlHandler);
}
