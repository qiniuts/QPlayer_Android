package com.qiniu.qplayer2.ui.page.longvideo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.controller.ICommonPlayerVideoSwitcher;
import com.qiniu.qplayer2ext.commonplayer.enviroment.ICommonPlayerEnvironment;
import com.qiniu.qplayer2.repository.setting.PlayerSettingRespostory;
import com.qiniu.qplayer2.ui.page.longvideo.service.ServiceOwnerType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongPlayerEnviroment implements
        ICommonPlayerEnvironment<LongLogicProvider, LongPlayableParams, LongVideoParams> {

    private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;

    //    override val name: String
    @Override
    public String getName(){
        return LongEnviromentType.LONG.type;
    }
    @Override
    public Set<String> getServiceTypes(){
        Set<String> serviceTypes=new HashSet<>();
        serviceTypes.add(ServiceOwnerType.PLAYER_CONTROL_PANEL_CONTATINER_VISIBLE_SERVICE.type);
        serviceTypes.add(ServiceOwnerType.PLAYER_TOAST_SERVICE.type);
        serviceTypes.add(ServiceOwnerType.PLAYER_BUFFERING_SERVICE.type);
        serviceTypes.add(ServiceOwnerType.PLAYER_NETWORK_SERVICE.type);
        serviceTypes.add(ServiceOwnerType.PLAYER_PANORAMA_TOUCH_SERVICE.type);
        return serviceTypes;
    }
    private ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams> mVideoPlayEventListener=new ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams>() {
        @Override
        public void onVideoParamsStart(@NonNull LongVideoParams longVideoParams) {

        }

        @Override
        public void onPlayableParamsStart(@NonNull LongPlayableParams longPlayableParams, @NonNull LongVideoParams longVideoParams) {

        }

        @Override
        public void onPlayableParamsWillChange(@Nullable LongPlayableParams oldPlayableParams, @Nullable LongVideoParams oldVideoParams, @NonNull LongPlayableParams newPlayableParams, @NonNull LongVideoParams newVideoParams, @Nullable Bundle switchVideoParams) {
            newPlayableParams.setStartPos(PlayerSettingRespostory.getInstance().getStartPosition());
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
    @Override
    public void start() {
        mPlayerCore.getMCommonPlayerVideoSwitcher().addVideoPlayEventListener(mVideoPlayEventListener);

    }

    @Override
    public void stop() {
        mPlayerCore.getMCommonPlayerVideoSwitcher().removeVideoPlayEventListener(mVideoPlayEventListener);
    }

    @Override
    public boolean authentication(LongPlayableParams playableParams, LongVideoParams videoParams) {
        return true;
    }


    @Override
    public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
    }
}