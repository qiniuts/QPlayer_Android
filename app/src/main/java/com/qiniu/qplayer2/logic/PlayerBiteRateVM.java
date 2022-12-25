package com.qiniu.qplayer2.logic;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.qiniu.qmedia.component.player.QIPlayerBiteRateListener;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;

public class PlayerBiteRateVM extends BasePlayerControlVM implements QIPlayerBiteRateListener {
    public MutableLiveData<Long> playerBiteRateLiveData=new MutableLiveData<Long>();
    public long bitRate;
    public long getBitRate(){
        return playerControlHandler!=null?playerControlHandler.getBiteRate():0L;
    }
    @Override
    public void onBiteRateChanged(long biteRate) {
        playerBiteRateLiveData.setValue(biteRate);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onSetPlayerControlHandler(QPlayerControlHandler controlHandler) {
        if(playerControlHandler!=null)
        playerControlHandler.addPlayerBiteRateChangeListener(this);
    }
}
