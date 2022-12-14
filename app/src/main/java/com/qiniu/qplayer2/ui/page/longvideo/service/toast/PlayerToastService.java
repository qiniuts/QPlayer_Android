package com.qiniu.qplayer2.ui.page.longvideo.service.toast;

import android.util.Log;

import androidx.annotation.NonNull;

import com.qiniu.qmedia.component.player.*;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.toast.PlayerToast;
import com.qiniu.qplayer2ext.commonplayer.layer.toast.PlayerToastConfig;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerService;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;

public class PlayerToastService
    implements IPlayerService<LongLogicProvider, LongPlayableParams, LongVideoParams>, IPlayerToastService,
        QIPlayerQualityListener, QIPlayerVideoDecodeListener,
        QIPlayerCommandNotAllowListener, QIPlayerFormatListener, QIPlayerSEIDataListener, QIPlayerAuthenticationListener {

private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;

    @Override
    public void onStart() {
        Log.e("test==","PlayerToastService=start");
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerQualityListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerVideoDecodeTypeListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerCommandNotAllowListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerFormatListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerSEIDataListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerAuthenticationListener(this);
            }

            @Override
            public void onStop() {
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerQualityListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerVideoDecodeTypeListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerCommandNotAllowListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerFormatListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerSEIDataListener(this);
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerAuthenticationListener(this);
            }

            @Override public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }

        @Override
        public void onQualitySwitchStart(String userType,QURLType urlType,int newQuality,int oldQuality) {
            PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "????????????????????????"+newQuality+",?????????...")
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

        @Override
        public void onQualitySwitchComplete(String userType,QURLType urlType,int newQuality,int oldQuality
        ) {
            PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "??????????????????????????????"+newQuality+"???")
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

        @Override
        public void onQualitySwitchCanceled(String userType,QURLType urlType,int newQuality,int oldQuality
        ) {
            PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "????????????"+newQuality+"???????????????")
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

        @Override
        public void onQualitySwitchFailed(String userType,QURLType urlType,int newQuality,int oldQuality
        ) {
            PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "?????????????????????"+newQuality+"?????????")
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

    @Override
    public void onQualitySwitchRetryLater(String userType,QURLType urlType,int newQuality) {
        PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "??????????????????????????????")
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

    @Override
    public void onVideoDecodeByType(QPlayerDecodeType type) {
        String typeName;
        if(type==QPlayerDecodeType.NONE){
            typeName="???";
        }else if(type==QPlayerDecodeType.FIRST_FRAME_ACCEL){
            typeName="??????";
        }else if(type==QPlayerDecodeType.HARDWARE_BUFFER){
            typeName="buffer??????";
        }else if(type==QPlayerDecodeType.HARDWARE_SURFACE){
            typeName="surface??????";
        }else if(type==QPlayerDecodeType.SOFTWARE){
            typeName="??????";
        }else{
            typeName="???";
        }
        PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "??????????????????"+typeName)
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

    @Override
    public void notSupportCodecFormat(int codecId) {
        PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "???????????????????????????"+codecId)
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

    @Override
    public void onCommandNotAllow(String commandName,QPlayerState state) {
        PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "not allow $commandName ??????:"+state)
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

    @Override
    public void onFormatNotSupport() {
        PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "???????????????????????????????????????????????????sample??????")
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }
    @Override
    public void onSEIData(@NonNull byte[] data) {
        PlayerToast toast = new PlayerToast.Builder()
                .toastItemType(PlayerToastConfig.TYPE_NORMAL)
                .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
                .setExtraString(PlayerToastConfig.EXTRA_TITLE, "SEI DATA:"+programAnaly(data))
                .duration(PlayerToastConfig.DURATION_3)
                .build();
        Log.i("PlayerToastService", "SEI DATA:"+data);
        mPlayerCore.getPlayerToastContainer().showToast(toast);
    }

    private String programAnaly(byte[] buf) {
        byte[] vsnFileByte = new byte[buf.length];
        System.arraycopy(buf, 0, vsnFileByte, 0, buf.length);
        return new String(vsnFileByte);
    }
    @Override
    public void  on_authentication_failed(QAuthenticationErrorType error_type) {
        PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "Qplayer2????????????-"+error_type)
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        Log.e("PlayerToastService", "Qplayer2????????????-"+error_type);

        mPlayerCore.getPlayerToastContainer().showToast(toast);
        }

    @Override
    public void  on_authentication_success() {
        PlayerToast toast = new PlayerToast.Builder()
        .toastItemType(PlayerToastConfig.TYPE_NORMAL)
        .location(PlayerToastConfig.LOCAT_LEFT_SIDE)
        .setExtraString(PlayerToastConfig.EXTRA_TITLE, "Qplayer2????????????")
        .duration(PlayerToastConfig.DURATION_3)
        .build();
        Log.e("PlayerToastService", "Qplayer2????????????");
        mPlayerCore.getPlayerToastContainer().showToast(toast);
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