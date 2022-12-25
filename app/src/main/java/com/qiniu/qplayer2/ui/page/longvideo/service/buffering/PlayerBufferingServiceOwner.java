package com.qiniu.qplayer2.ui.page.longvideo.service.buffering;

import com.qiniu.qplayer2ext.commonplayer.service.IPlayerServiceOwner;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerServiceManager;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.page.longvideo.service.ServiceOwnerType;

public class PlayerBufferingServiceOwner implements
        IPlayerServiceOwner<LongLogicProvider, LongPlayableParams, LongVideoParams> {

    @Override
    public String getName(){
        return ServiceOwnerType.PLAYER_BUFFERING_SERVICE.type;
    }


    private PlayerServiceManager.Client<PlayerBufferingService, LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerBufferingServiceClient=
            new PlayerServiceManager.Client();

    @Override public void start(IPlayerServiceManager<LongLogicProvider, LongPlayableParams, LongVideoParams> serviceManager) {
        serviceManager.bindService(
                PlayerServiceManager.ServiceDescriptor.obtain(
                        PlayerBufferingService.class
                ), mPlayerBufferingServiceClient
        );
    }

    @Override
    public void stop(IPlayerServiceManager<LongLogicProvider, LongPlayableParams, LongVideoParams> serviceManager) {
        serviceManager.unbindService(
                PlayerServiceManager.ServiceDescriptor.obtain(
                        PlayerBufferingService.class
                ), mPlayerBufferingServiceClient
        );
    }

    @Override
    public <T> T service() {
        return (T)mPlayerBufferingServiceClient.getService();
    }
}