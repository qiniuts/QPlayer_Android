package com.qiniu.qplayer2.logic;

import androidx.lifecycle.MutableLiveData;

import com.qiniu.qmedia.component.player.QIPlayerFPSListener;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;

public class PlayerFPSVM extends BasePlayerControlVM implements QIPlayerFPSListener {
    public MutableLiveData<Integer> playerFPSLiveData=new MutableLiveData<Integer>();
    public int fps;
    public long getFps(){
        return playerControlHandler!=null?playerControlHandler.getFps():0;
    }
    @Override
    public void onFPSChanged(int fps) {
        playerFPSLiveData.setValue(fps);
    }

    @Override
    public void onSetPlayerControlHandler(QPlayerControlHandler controlHandler) {
        if(playerControlHandler!=null){
            playerControlHandler.addPlayerFPSChangeListener(this);
        }
    }
}
