package com.qiniu.qplayer2.logic;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.qiniu.qmedia.component.player.QIPlayerBufferingListener;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;

public class PlayerBufferingVM extends BasePlayerControlVM implements QIPlayerBufferingListener {
        private static final String TAG = "PlayerBufferingVM";

        public MutableLiveData<Boolean> playerBufferingLiveData = new MutableLiveData<Boolean>();

        @Override
        public void onBufferingStart() {
                Log.i(TAG, "onBufferingStart()");
                playerBufferingLiveData.setValue(true);
        }

        @Override
        public void onBufferingEnd() {
                Log.i(TAG, "onBufferingEnd()");
                playerBufferingLiveData.setValue(false);
        }

        @Override
        public void onSetPlayerControlHandler(QPlayerControlHandler controlHandler) {
                if(playerControlHandler!=null)
                playerControlHandler.addPlayerBufferingChangeListener(this);
        }
}