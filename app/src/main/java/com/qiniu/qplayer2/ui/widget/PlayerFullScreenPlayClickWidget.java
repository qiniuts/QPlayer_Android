package com.qiniu.qplayer2.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.logic.PlayerPausePlayVM;

public class PlayerFullScreenPlayClickWidget extends FrameLayout implements View.OnClickListener {
    private PlayerPausePlayVM mPlayerPausePlayVM =new PlayerPausePlayVM();



    public PlayerFullScreenPlayClickWidget(Context context) {
        super(context);
    }

    public PlayerFullScreenPlayClickWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerFullScreenPlayClickWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPlayerControlHandler(QPlayerControlHandler controlHandler) {
        mPlayerPausePlayVM.playerControlHandler = controlHandler;
        if (controlHandler != null) {
            init();
        } else {
            uninit();
        }
    }

    private void init() {
        setOnClickListener(this);
    }

    private void uninit() {
        setOnClickListener(null);

    }

    @Override
    public void onClick(View v) {
        QPlayerState playerState = mPlayerPausePlayVM.getCurrentPlayerState();
        if (playerState == QPlayerState.PLAYING) {
            mPlayerPausePlayVM.pause();
        } else {
            mPlayerPausePlayVM.resume();
        }
    }
}