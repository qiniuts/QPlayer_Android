package com.qiniu.qplayer2.logic;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.qiniu.qmedia.component.player.QIPlayerStateChangeListener;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qmedia.component.player.QPlayerState;

public class PlayerPausePlayVM extends BasePlayerControlVM implements QIPlayerStateChangeListener {
    public MutableLiveData<QPlayerState> playerStateLiveData=new MutableLiveData<>();
    @Override
    public void onStateChanged(QPlayerState state) {
        playerStateLiveData.setValue(state);
    }
    public QPlayerState getCurrentPlayerState() {
        return playerControlHandler!=null?playerControlHandler.getCurrentPlayerState() : QPlayerState.NONE;
    }
    public void resume() {
        playerControlHandler.resumeRender();
    }

    public void pause() {
        playerControlHandler.pauseRender();
    }
    @Override
    public void onSetPlayerControlHandler(QPlayerControlHandler controlHandler) {
        Log.e("==","PlayerPausePlayVM="+playerControlHandler);
        if(playerControlHandler!=null){
            playerControlHandler.addPlayerStateChangeListener(this);
        }

    }
}
