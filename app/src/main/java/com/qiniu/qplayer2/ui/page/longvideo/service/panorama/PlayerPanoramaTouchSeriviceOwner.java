package com.qiniu.qplayer2.ui.page.longvideo.service.panorama;

import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.page.longvideo.service.ServiceOwnerType;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerServiceOwner;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerServiceManager;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;

public class PlayerPanoramaTouchSeriviceOwner implements
        IPlayerServiceOwner<LongLogicProvider, LongPlayableParams, LongVideoParams> {

    @Override
    public String getName(){
        return ServiceOwnerType.PLAYER_PANORAMA_TOUCH_SERVICE.type;
    }

private PlayerServiceManager.Client<PlayerPanoramaTouchSerivice, LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerPanoramaTouchServiceClient =
        new PlayerServiceManager.Client();

        @Override
        public void start(IPlayerServiceManager<LongLogicProvider, LongPlayableParams, LongVideoParams> serviceManager) {
        serviceManager.bindService(
        PlayerServiceManager.ServiceDescriptor.obtain(
        PlayerPanoramaTouchSerivice.class
        ), mPlayerPanoramaTouchServiceClient
        );
        }

        @Override
        public void stop(IPlayerServiceManager<LongLogicProvider, LongPlayableParams, LongVideoParams> serviceManager) {
        serviceManager.unbindService(
        PlayerServiceManager.ServiceDescriptor.obtain(
        PlayerPanoramaTouchSerivice.class
        ), mPlayerPanoramaTouchServiceClient
        );
        }

        @Override public  <T> T service() {
        return (T)mPlayerPanoramaTouchServiceClient.getService();
        }
        }