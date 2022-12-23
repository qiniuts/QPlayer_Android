package com.qiniu.qplayer2.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;

import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qplayer2.logic.PlayerDownloadVM;

public class PlayerDownloadTextWidget extends AppCompatTextView {
    private PlayerDownloadVM mPlayerDownloadVM=new PlayerDownloadVM();
    private Observer<Long>  mObserver= it -> setText((it/1024/8)+"KB/s");

    public PlayerDownloadTextWidget(@NonNull Context context) {
        super(context);
    }

    public PlayerDownloadTextWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerDownloadTextWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setPlayerControlHandler(QPlayerControlHandler controlHandler){
        mPlayerDownloadVM.setQPlayerControlHandler(controlHandler);
        if (controlHandler != null) {
            init();
        } else {
            uninit();
        }
    }
    private void init() {
        mPlayerDownloadVM.playerDownloadSpeedLiveData.observeForever(mObserver);
    }

    private void uninit() {
        mPlayerDownloadVM.playerDownloadSpeedLiveData.removeObserver(mObserver);
    }
}
