package com.qiniu.qplayer2.ui.page.longvideo.service.buffering;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.qiniu.qmedia.component.player.QIPlayerBufferingListener;
import com.qiniu.qmedia.component.player.QIPlayerStateChangeListener;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.function.PlayerFunctionContainer;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerService;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.buffering.BufferingFunctionWidget;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetLayoutParams;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;

public class PlayerBufferingService implements
        IPlayerService<LongLogicProvider, LongPlayableParams, LongVideoParams>, IPlayerBufferingService,
        QIPlayerBufferingListener, QIPlayerStateChangeListener{

private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
private PlayerFunctionContainer.FunctionWidgetToken<LongLogicProvider, LongPlayableParams, LongVideoParams> mBufferingToken = null;

private boolean mIsBuffering = false;
private boolean mIsPrepared = false;
private boolean mIsSeeking = false;
        @Override
        public void onStart() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerBufferingChangeListener(this);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerStateChangeListener(this);

        }

        @Override
        public void onStop() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerBufferingChangeListener(this);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerStateChangeListener(this);

        }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }

        @Override
        public void onBufferingStart() {


        mIsBuffering =true;
        updateBufferingWidget();
        }

        @Override
        public void onBufferingEnd() {
        mIsBuffering =false;
        updateBufferingWidget();
        }


private void updateBufferingWidget() {

        //隐藏
        if (mBufferingToken != null) {
                mPlayerCore.getPlayerFunctionWidgetContainer().hideWidget(mBufferingToken);
        mBufferingToken = null;
        }

        //显示
        if (!mIsPrepared || mIsBuffering || mIsSeeking) {
                FunctionWidgetLayoutParams layoutParams = new FunctionWidgetLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setLayoutType(FunctionWidgetLayoutParams.LayoutAlignType.CENTER);
        layoutParams.setFunctionType(FunctionWidgetLayoutParams.FunctionType.EMBEDDED_VIEW);
        layoutParams.setEnterAnim(FunctionWidgetLayoutParams.NO_ANIMATION);
        layoutParams.setExitAnim(FunctionWidgetLayoutParams.NO_ANIMATION);
        layoutParams.touchOutsideDismiss(false);
        mBufferingToken = mPlayerCore.getPlayerFunctionWidgetContainer().showWidget(BufferingFunctionWidget.class, layoutParams);
        }
        }

        @Override
        public void onStateChanged(QPlayerState state) {
               if(state==QPlayerState.INIT&&state==QPlayerState.PREPARE){
                       mIsPrepared = false;
                       mIsSeeking = false;
               }else if(state==QPlayerState.SEEKING){
                       mIsSeeking = true;
                       mIsPrepared = true;
               }else if(state==QPlayerState.COMPLETED){
                       mIsPrepared = true;
                       mIsSeeking = false;
                       mIsBuffering = false;
               }else{
                       mIsPrepared = true;
                       mIsSeeking = false;
               }
        updateBufferingWidget();
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
