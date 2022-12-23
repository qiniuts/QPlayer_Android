package com.qiniu.qplayer2.logic;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.qiniu.qmedia.component.player.QIPlayerDownloadListener;
import com.qiniu.qmedia.component.player.QIPlayerProgressListener;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;

public class PlayerProgressVM extends BasePlayerControlVM implements QIPlayerProgressListener, QIPlayerDownloadListener {
        public MutableLiveData<Long> playerProgressLiveData = new MutableLiveData<Long>();
        public MutableLiveData<Long> playBufferProgressLiveData = new MutableLiveData<Long>();

        private static final String   TAG = "PlayerProgressVM";
        public long getDuration() {
                return playerControlHandler!=null?playerControlHandler.getDuration():0L;
        }
        private long deltaProgress = -1L;
        @Override
        public void onProgressChanged(long duration, long progress) {

                if (deltaProgress == -1L) {
//                        deltaProgress = progress;
                        deltaProgress = 0;
                        if(playerControlHandler!=null){
                                Log.i(TAG, "onProgressChanged(duration = "+duration+", progress = "+progress+"), deltaProgress = "+deltaProgress+", currentState = "+playerControlHandler.getCurrentPlayerState());

                        }
               }
                long realProgress = progress - deltaProgress;
                if(playerControlHandler!=null){
                        Log.i(TAG, "onProgressChanged(duration = "+duration+", progress = "+progress+"), deltaProgress = "+deltaProgress+", realProgress = "+realProgress+", position = "+playerControlHandler.getCurrentPosition()+", currentState = "+playerControlHandler.getCurrentPlayerState());
               }
                playerProgressLiveData.setValue(realProgress);
        }

        @Override
        public void onSetPlayerControlHandler(QPlayerControlHandler controlHandler) {
                Log.e("==","PlayerProgressVM="+playerControlHandler);
                if(playerControlHandler!=null){
                        playerControlHandler.addPlayerProgressChangeListener(this);
                        playerControlHandler.addPlayerDownloadChangeListener(this);
                }

        }

        @SuppressLint("SuspiciousIndentation")
        public void seek(long position) {
                if(playerControlHandler!=null)
                playerControlHandler.seek(position);
        }

        @Override
        public void onDownloadChanged(long speed, long bufferProgress) {
                playBufferProgressLiveData.setValue(bufferProgress);
        }
}