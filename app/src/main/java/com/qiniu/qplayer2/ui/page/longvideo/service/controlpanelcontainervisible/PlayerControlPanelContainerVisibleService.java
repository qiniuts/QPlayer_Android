package com.qiniu.qplayer2.ui.page.longvideo.service.controlpanelcontainervisible;

import android.view.MotionEvent;

import androidx.annotation.NonNull;

import com.qiniu.qmedia.component.player.QIPlayerStateChangeListener;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.function.PlayerFunctionContainer;
import com.qiniu.qplayer2ext.commonplayer.layer.gesture.OnSingleTapListener;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerService;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.buffering.BufferingFunctionWidget;
import com.qiniu.qplayer2ext.common.thread.HandlerThreads;
import com.qiniu.qplayer2ext.commonplayer.layer.function.IOnFunctionWidgetVisibilityChangeListener;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;

public class PlayerControlPanelContainerVisibleService
    implements IPlayerService<LongLogicProvider, LongPlayableParams, LongVideoParams>,
        IPlayerControlPanelContainerVisibleService,
        OnSingleTapListener,
        IOnFunctionWidgetVisibilityChangeListener<LongLogicProvider, LongPlayableParams, LongVideoParams>,
        QIPlayerStateChangeListener {


//playing时 Panel 显示一定时间后隐藏
private boolean mIsPlaying = false;
private boolean mIsAutoHideEnable = true;
private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
    private Runnable mHideRunnable = () -> mPlayerCore.getPlayerControlPanelContainer().hide();

        @Override
        public void onStart() {
        mPlayerCore.getPlayerGestureLayer().addOnSingleTapListener(this,0);
        mPlayerCore.getPlayerFunctionWidgetContainer().addOnFunctionWidgetVisibilityChangeListener(this);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerStateChangeListener(this);

        }

        @Override
        public void onStop() {
        mPlayerCore.getPlayerGestureLayer().removeOnSingleTapListener(this);
        mPlayerCore.getPlayerFunctionWidgetContainer().removeOnFunctionWidgetVisibilityChangeListener(this);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerStateChangeListener(this);
        }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }

        @Override
        public boolean onSingleTap(MotionEvent event) {
        if (mPlayerCore.getPlayerControlPanelContainer().isShow() == true) {
        hide();
        } else {
        show();
        }

        return true;
        }

        @Override public void onWidgetShow(PlayerFunctionContainer.FunctionWidgetToken<LongLogicProvider, LongPlayableParams, LongVideoParams> token) {
        if (!token.getClazz().getName().equals(BufferingFunctionWidget.class.getName())) {
        mPlayerCore.getPlayerControlPanelContainer().hide();
        }
        }

        @Override
        public void onWidgetDismiss(PlayerFunctionContainer.FunctionWidgetToken<LongLogicProvider, LongPlayableParams, LongVideoParams> token) {
        }

        @Override public void onStateChanged(QPlayerState state) {
        if (state == QPlayerState.PLAYING) {
        mIsPlaying = true;
        if (mPlayerCore.getPlayerControlPanelContainer().isShow() == true) {
        show();
        }
        } else {
        mIsPlaying = false;
        show();
        }
        }

private void hide() {
        mPlayerCore.getPlayerControlPanelContainer().hide();
        HandlerThreads.remove(HandlerThreads.THREAD_UI, mHideRunnable);
        }

private void show() {
        mPlayerCore.getPlayerControlPanelContainer().show();
        if (mIsPlaying && mIsAutoHideEnable) {
        HandlerThreads.remove(HandlerThreads.THREAD_UI, mHideRunnable);
        HandlerThreads.postDelayed(HandlerThreads.THREAD_UI, mHideRunnable, 5000);
        }
        }

        @Override
        public void setAutoHideEnable(boolean enable) {
        mIsAutoHideEnable = enable;
        if (!mIsAutoHideEnable) {
        HandlerThreads.remove(HandlerThreads.THREAD_UI, mHideRunnable);
        }
        }

    @Override
    public void onPlayerReset() {

    }

    @NonNull
    @Override
    public PlayerServiceManager.ServiceConfig serviceConfig() {
        return PlayerServiceManager.ServiceConfig.obtain(false);
    }
}

