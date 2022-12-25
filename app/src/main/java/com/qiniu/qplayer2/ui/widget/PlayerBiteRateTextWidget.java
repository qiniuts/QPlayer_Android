package com.qiniu.qplayer2.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;

import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qplayer2.logic.PlayerBiteRateVM;

public class PlayerBiteRateTextWidget  extends AppCompatTextView {
    private PlayerBiteRateVM mPlayerBiteRateVM=new PlayerBiteRateVM();
    private Observer<Long> mObserver= it -> setText((it/1024)+"kbps");

    public PlayerBiteRateTextWidget(@NonNull Context context) {
        super(context);
    }

    public PlayerBiteRateTextWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerBiteRateTextWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setPlayerControlHandler(QPlayerControlHandler controlHandler){
        mPlayerBiteRateVM.setQPlayerControlHandler(controlHandler);
        if (controlHandler != null) {
            init();
        } else {
            uninit();
        }
    }
    private void init() {
        mPlayerBiteRateVM.playerBiteRateLiveData.observeForever(mObserver);
    }

    private void uninit() {
        mPlayerBiteRateVM.playerBiteRateLiveData.removeObserver(mObserver);
    }
}
