package com.qiniu.qplayer2.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;

import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.logic.PlayerPausePlayVM;

public class PlayerPausePlayWidget extends AppCompatImageView implements View.OnClickListener{
    private PlayerPausePlayVM mPlayerPausePlayVM=new PlayerPausePlayVM();
    private Observer<QPlayerState> mObserver= it -> {
        if (it == QPlayerState.PLAYING) {
            setImageLevel(1);
        } else {
            setImageLevel(0);
        }
        Toast.makeText(getContext(), "state: "+it, Toast.LENGTH_SHORT).show();
    };

    public PlayerPausePlayWidget(@NonNull Context context) {
        super(context);
    }

    public PlayerPausePlayWidget(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerPausePlayWidget(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setPlayerControlHandler(QPlayerControlHandler controlHandler){
        mPlayerPausePlayVM.setQPlayerControlHandler(controlHandler);
        if (controlHandler != null) {
            init();
        } else {
            uninit();
        }
    }
    private void init() {
        setImageResource(R.drawable.qmedia_player_play_pause_level_list);
        setOnClickListener(this);
        mPlayerPausePlayVM.playerStateLiveData.observeForever(mObserver);
    }

    private void uninit() {
        setOnClickListener(null);
        mPlayerPausePlayVM.playerStateLiveData.observeForever(mObserver);
    }

    @Override
    public void onClick(View view) {
        QPlayerState playerState = mPlayerPausePlayVM.getCurrentPlayerState();
        if (playerState == QPlayerState.PLAYING) {
            mPlayerPausePlayVM.pause();
        } else {
            mPlayerPausePlayVM.resume();
        }
    }
}
