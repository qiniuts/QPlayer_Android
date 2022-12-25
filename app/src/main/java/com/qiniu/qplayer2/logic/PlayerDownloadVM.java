package com.qiniu.qplayer2.logic;

import androidx.lifecycle.MutableLiveData;

import com.qiniu.qmedia.component.player.QIPlayerDownloadListener;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;

public class PlayerDownloadVM extends BasePlayerControlVM implements QIPlayerDownloadListener {
    public MutableLiveData<Long> playerDownloadSpeedLiveData = new MutableLiveData<Long>();
    public Long downloadSpeed;
    public Long getDownloadSpeed(){
        return playerControlHandler!=null?playerControlHandler.getDownloadSpeed():0L;
    }
    @Override
    public void onDownloadChanged(long speed, long bufferProgress) {
        playerDownloadSpeedLiveData.setValue(speed);
    }

    @Override
    public void onSetPlayerControlHandler(QPlayerControlHandler controlHandler) {
        if(playerControlHandler!=null)
        playerControlHandler.addPlayerDownloadChangeListener(this);
    }
}
