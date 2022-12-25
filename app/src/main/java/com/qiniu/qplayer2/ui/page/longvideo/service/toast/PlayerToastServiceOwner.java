package com.qiniu.qplayer2.ui.page.longvideo.service.toast;

import android.util.Log;

import com.qiniu.qplayer2ext.commonplayer.service.IPlayerServiceOwner;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerServiceManager;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.page.longvideo.service.ServiceOwnerType;

public class PlayerToastServiceOwner implements
        IPlayerServiceOwner<LongLogicProvider, LongPlayableParams, LongVideoParams> {
    @Override
    public String getName(){
        return ServiceOwnerType.PLAYER_TOAST_SERVICE.type;
    }

    private PlayerServiceManager.Client<PlayerToastService, LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerToastServiceClient =
            new PlayerServiceManager.Client();

    @Override
    public void start(IPlayerServiceManager<LongLogicProvider, LongPlayableParams, LongVideoParams> serviceManager) {
        Log.e("test==","toast=start");
        serviceManager.bindService(
                PlayerServiceManager.ServiceDescriptor.obtain(
                        PlayerToastService.class
                ), mPlayerToastServiceClient
        );
    }

    @Override
    public void stop(IPlayerServiceManager<LongLogicProvider, LongPlayableParams, LongVideoParams> serviceManager) {
        serviceManager.unbindService(
                PlayerServiceManager.ServiceDescriptor.obtain(
                        PlayerToastService.class
                ), mPlayerToastServiceClient
        );
    }

    @Override
    public <T> T service(){
        Log.e("test==","toast=service");
        return (T)mPlayerToastServiceClient.getService();
    }
}