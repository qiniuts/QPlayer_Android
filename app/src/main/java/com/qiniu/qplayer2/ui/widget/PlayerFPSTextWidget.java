package com.qiniu.qplayer2.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;

import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qplayer2.logic.PlayerFPSVM;

public class PlayerFPSTextWidget extends AppCompatTextView {
    private PlayerFPSVM mPlayerFPSVM=new PlayerFPSVM();
    private Observer<Integer> mObserver= it -> setText("FPS:"+it);

    public PlayerFPSTextWidget(@NonNull Context context) {
        super(context);
    }

    public PlayerFPSTextWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerFPSTextWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setPlayerControlHandler(QPlayerControlHandler controlHandler){
        mPlayerFPSVM.setQPlayerControlHandler(controlHandler);
        if (controlHandler != null) {
            init();
        } else {
            uninit();
        }
    }
    private void init() {
        mPlayerFPSVM.playerFPSLiveData.observeForever(mObserver);
    }

    private void uninit() {
        mPlayerFPSVM.playerFPSLiveData.removeObserver(mObserver);
    }
}
