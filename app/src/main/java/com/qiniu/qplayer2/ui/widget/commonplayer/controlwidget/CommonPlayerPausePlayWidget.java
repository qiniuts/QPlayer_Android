package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import com.qiniu.qmedia.component.player.QIPlayerStateChangeListener;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;

public class CommonPlayerPausePlayWidget extends AppCompatImageView implements View.OnClickListener,
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
private QIPlayerStateChangeListener mPlayerStateChangeListener=new QIPlayerStateChangeListener() {
    @Override
    public void onStateChanged(@NonNull QPlayerState state) {
        updateIcon(state);
    }
};

        public CommonPlayerPausePlayWidget(Context context) {
            super(context);
        }

    public CommonPlayerPausePlayWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
        }

    public CommonPlayerPausePlayWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        }

private void updateIcon(QPlayerState state) {
        if (state == QPlayerState.PLAYING) {
        setImageResource(R.drawable.qmedia_player_pause_vector);
//            setImageLevel(1)
        } else if (state == QPlayerState.PAUSED_RENDER ||
        state == QPlayerState.PREPARE ||
        state == QPlayerState.INIT ||
        state == QPlayerState.NONE
        ) {
        setImageResource(R.drawable.qmedia_player_play_vector);

//            setImageLevel(0)
        } else if( state == QPlayerState.COMPLETED) {
        setImageResource(R.drawable.qmedia_ic_player_replay_vector);

//            setImageLevel(2)
        }
        }

        @Override
        public void onClick(View v) {
            QPlayerState playerState = mPlayerCore.getMPlayerContext().getPlayerControlHandler().getCurrentPlayerState();
        if (playerState == QPlayerState.PLAYING) {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().pauseRender();
        } else if (playerState == QPlayerState.COMPLETED){
        mPlayerCore.getMCommonPlayerVideoSwitcher().replayCurrentVideo(null);
        } else {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().resumeRender();

        }
        }

        @Override
        public void onWidgetActive() {
//        setImageResource(R.drawable.qmedia_player_play_pause_level_list)
        setOnClickListener(this);

        updateIcon(mPlayerCore.getMPlayerContext().getPlayerControlHandler().getCurrentPlayerState());
        mPlayerCore.getMPlayerContext().getPlayerControlHandler()
        .addPlayerStateChangeListener(mPlayerStateChangeListener);


        }

        @Override
        public void onWidgetInactive() {
        setOnClickListener(null);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler()
        .removePlayerStateChangeListener(mPlayerStateChangeListener);
        }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }
        }