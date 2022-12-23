package com.qiniu.qplayer2.ui.page.longvideo.service.network;

import android.text.TextUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.qiniu.qmedia.component.player.QIPlayerMediaNetworkListener;
import com.qiniu.qmedia.component.player.QIPlayerStateChangeListener;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qmedia.component.player.QURLType;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.function.PlayerFunctionContainer;
import com.qiniu.qplayer2ext.commonplayer.layer.toast.PlayerToast;
import com.qiniu.qplayer2ext.commonplayer.layer.toast.PlayerToastConfig;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerService;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.error.ErrorFunctionWidget;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetLayoutParams;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;

public class PlayerNetworkService implements
        IPlayerService<LongLogicProvider, LongPlayableParams, LongVideoParams>, QIPlayerMediaNetworkListener,
        QIPlayerStateChangeListener {

private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
private PlayerFunctionContainer.FunctionWidgetToken<LongLogicProvider, LongPlayableParams, LongVideoParams> mErrorToken = null;

private int mNotifyTime = 0;
        @Override
        public void onStart() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerMediaNetworkListener(this);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerStateChangeListener(this);
        }

        @Override
        public void onStop() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerMediaNetworkListener(this);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerStateChangeListener(this);

        }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }

        @Override
        public void onReconnectStart(String userType,QURLType urlType,String url,int retryTime
        ) {
        if (mNotifyTime % 5 == 0) {
                PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "开始重连...")
        .duration(PlayerToastConfig.DURATION_3)
        .build();

        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

        }

        @Override
        public void onReconnectEnd(String userType,QURLType urlType,String url,int retryTime,QIPlayerMediaNetworkListener.OpenError error
        ) {
        if (error == QIPlayerMediaNetworkListener.OpenError.NONE || mNotifyTime % 5 == 0) {
                PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, error == QIPlayerMediaNetworkListener.OpenError.NONE?"重连成功." : "重连失败")
        .duration(PlayerToastConfig.DURATION_3)
        .build();

        mPlayerCore.getPlayerToastContainer().showToast(toast);
        mNotifyTime = 0;
        }


        mNotifyTime++;
        }

        @Override
        public void onOpenFailed(String userType,QURLType urlType,String url,QIPlayerMediaNetworkListener.OpenError error
        ) {
        if (error != QIPlayerMediaNetworkListener.OpenError.INTERRUPT) {
                FunctionWidgetLayoutParams layoutParams = new FunctionWidgetLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setFunctionType(FunctionWidgetLayoutParams.FunctionType.EMBEDDED_VIEW);

        layoutParams.setLayoutType(FunctionWidgetLayoutParams.LayoutAlignType.CENTER);
        layoutParams.setEnterAnim(FunctionWidgetLayoutParams.NO_ANIMATION);
        layoutParams.setExitAnim(FunctionWidgetLayoutParams.NO_ANIMATION);
        layoutParams.touchOutsideDismiss(false);

        mErrorToken = mPlayerCore.getPlayerFunctionWidgetContainer().showWidget(
        ErrorFunctionWidget.class, layoutParams);
        }
        }

        @Override
        public void onStateChanged(QPlayerState state) {
        if (state == QPlayerState.PREPARE) {
                if(mErrorToken!=null)
                mPlayerCore.getPlayerFunctionWidgetContainer().hideWidget(mErrorToken);
        mErrorToken = null;
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