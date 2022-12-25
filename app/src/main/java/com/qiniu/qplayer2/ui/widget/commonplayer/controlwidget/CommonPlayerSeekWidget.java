package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.qiniu.qmedia.component.player.QIPlayerDownloadListener;
import com.qiniu.qmedia.component.player.QIPlayerProgressListener;
import com.qiniu.qmedia.component.player.QIPlayerStateChangeListener;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.controller.ICommonPlayerVideoSwitcher;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.page.longvideo.service.ServiceOwnerType;
import com.qiniu.qplayer2.ui.page.longvideo.service.controlpanelcontainervisible.IPlayerControlPanelContainerVisibleService;


public class CommonPlayerSeekWidget extends AppCompatSeekBar implements
        SeekBar.OnSeekBarChangeListener,
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

    private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;

    private boolean mIsNeedUpdateProgress = false;
    private boolean mIsTrackingTouch = false;
    private QIPlayerProgressListener mPlayerProgressListener=new QIPlayerProgressListener() {
        @Override
        public void onProgressChanged(long duration, long progress) {
            if (mIsNeedUpdateProgress && !mIsTrackingTouch) {
                if (duration > 0) {
                    CommonPlayerSeekWidget.this.setProgress((int)(progress * 1000 / duration));
                }
            }
        }
    };
    private QIPlayerDownloadListener mPlayerDownloadListener=new QIPlayerDownloadListener() {
        @Override
        public void onDownloadChanged(long speed, long bufferProgress) {
            if (mPlayerCore.getMPlayerContext().getPlayerControlHandler().getDuration() > 0) {
                CommonPlayerSeekWidget.this.setSecondaryProgress((int)(bufferProgress * 1000 / mPlayerCore.getMPlayerContext().getPlayerControlHandler().getDuration()));
            }
        }
    };
    private QIPlayerStateChangeListener mPlayerStateChangeListener=new QIPlayerStateChangeListener() {
        @Override
        public void onStateChanged(@NonNull QPlayerState state) {
            mIsNeedUpdateProgress = state == QPlayerState.PLAYING;
        }
    };
    private ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams> mVideoPlayEventListener=new ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams>() {
        @Override
        public void onVideoParamsStart(@NonNull LongVideoParams longVideoParams) {

        }

        @Override
        public void onPlayableParamsStart(@NonNull LongPlayableParams playableParams, @NonNull LongVideoParams videoParams) {
//            super.onPlayableParamsStart(playableParams, videoParams);
            setSeekBarClickable(!playableParams.getMediaModel().isLive());
            setProgress(0);
            setSecondaryProgress(0);
        }

        @Override
        public void onPlayableParamsWillChange(@Nullable LongPlayableParams longPlayableParams, @Nullable LongVideoParams longVideoParams, @NonNull LongPlayableParams t31, @NonNull LongVideoParams t41, @Nullable Bundle bundle) {

        }

        @Override
        public void onVideoParamsWillChange(@Nullable LongVideoParams longVideoParams, @NonNull LongVideoParams t41, @Nullable Bundle bundle) {

        }

        @Override
        public void onPlayableParamsCompleted(@NonNull LongPlayableParams longPlayableParams, @NonNull LongVideoParams longVideoParams) {

        }

        @Override
        public void onVideoParamsCompleted(@NonNull LongVideoParams longVideoParams) {

        }

        @Override
        public void onAllVideoParamsCompleted() {

        }

        @Override
        public void onVideoParamsSetChanged() {

        }
    };

    public CommonPlayerSeekWidget(Context context) {
        super(context);
    }

    public CommonPlayerSeekWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonPlayerSeekWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setSeekBarClickable(boolean enable) {
        if (enable) {
            //启用状态
            setClickable(true);
            setEnabled(true);
            setSelected(true);
            setFocusable(true);
//            val drawable = resources.getDrawable(R.drawable.yellow_mid_img_40)
//            thumb = drawable
        } else {
            //禁用状态
            setClickable(false);
            setEnabled(false);
            setSelected(false);
            setFocusable(false);
            setProgress(0);
            setSecondaryProgress(0);
////            val drawable = resources.getDrawable(R.drawable.seek_bar_grey_img_40)
//            thumb = drawable
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        IPlayerControlPanelContainerVisibleService service = mPlayerCore.getPlayerEnviromentServiceManager().getPlayerService(
                ServiceOwnerType.PLAYER_CONTROL_PANEL_CONTATINER_VISIBLE_SERVICE.type
        );
        service.setAutoHideEnable(false);
        mIsTrackingTouch = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().seek(getProgress()
                *mPlayerCore.getMPlayerContext().getPlayerControlHandler().getDuration()/1000);

        IPlayerControlPanelContainerVisibleService service = mPlayerCore.getPlayerEnviromentServiceManager().getPlayerService(
                ServiceOwnerType.PLAYER_CONTROL_PANEL_CONTATINER_VISIBLE_SERVICE.type
        );
        service.setAutoHideEnable(true);
        mIsTrackingTouch = false;
        mIsNeedUpdateProgress = false;
    }

    @Override
    public void onWidgetActive() {
        LongPlayableParams playableParams=  mPlayerCore.getMCommonPlayerVideoSwitcher().getCurrentPlayableParams();
        QPlayerControlHandler it=mPlayerCore.getMPlayerContext().getPlayerControlHandler();
        if (playableParams.getMediaModel().isLive() == false) {
            mIsNeedUpdateProgress = it.getCurrentPlayerState() == QPlayerState.PLAYING;
            if (it.getDuration() > 0) {
                setProgress((int)(it.getCurrentPosition() * 1000 / it.getDuration()));
                setSecondaryProgress((int)(it.getBufferPositon() * 1000 / it.getDuration()));
            }
            setSeekBarClickable(true);
        } else {
            setSeekBarClickable(false);
        }
        it.addPlayerDownloadChangeListener(mPlayerDownloadListener);
        it.addPlayerProgressChangeListener(mPlayerProgressListener);
        it.addPlayerStateChangeListener(mPlayerStateChangeListener);
        setOnSeekBarChangeListener(this);
        mPlayerCore.getMCommonPlayerVideoSwitcher().addVideoPlayEventListener(mVideoPlayEventListener);

    }

    @Override
    public void onWidgetInactive() {
        QPlayerControlHandler it=mPlayerCore.getMPlayerContext().getPlayerControlHandler();
        it.removePlayerProgressChangeListener(mPlayerProgressListener);
        it.removePlayerDownloadChangeListener(mPlayerDownloadListener);
        it.removePlayerStateChangeListener(mPlayerStateChangeListener);
        mPlayerCore.getMCommonPlayerVideoSwitcher().removeVideoPlayEventListener(mVideoPlayEventListener);
        setOnSeekBarChangeListener(null);

    }

    @Override
    public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
    }
}