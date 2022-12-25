package com.qiniu.qplayer2.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.logic.PlayerPausePlayVM;

import java.net.PortUnreachableException;

public class PlayerPlayWidget extends AppCompatImageView {
    private PlayerPausePlayVM mPlayerPausePlayVM =new PlayerPausePlayVM();
    private Observer<QPlayerState> mObserver=new Observer<QPlayerState>() {
        @Override
        public void onChanged(QPlayerState qPlayerState) {
            if (qPlayerState == QPlayerState.PAUSED_RENDER) {
                setVisibility(View.VISIBLE);
            } else {
                setVisibility(View.INVISIBLE);
            }
        }
    };

    public PlayerPlayWidget(Context context) {
        super(context);
    }

    public PlayerPlayWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerPlayWidget(Context context,AttributeSet attrs,int defStyleAttr)  {
        super(context, attrs, defStyleAttr);
    }

    public void setPlayerControlHandler(QPlayerControlHandler controlHandler) {
        mPlayerPausePlayVM.setQPlayerControlHandler(controlHandler);
        if (controlHandler != null) {
            init();

        } else {
            uninit();
        }
    }

    private void init() {
        setImageResource(R.drawable.qmedia_player_play_pause_level_list);
        mPlayerPausePlayVM.playerStateLiveData.observeForever(mObserver);
    }

    private void uninit() {
        mPlayerPausePlayVM.playerStateLiveData.removeObserver(mObserver);

    }
}