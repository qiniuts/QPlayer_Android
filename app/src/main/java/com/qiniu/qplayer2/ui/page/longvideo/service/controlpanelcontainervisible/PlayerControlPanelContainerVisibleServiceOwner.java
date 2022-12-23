package com.qiniu.qplayer2.ui.page.longvideo.service.controlpanelcontainervisible;

import com.qiniu.qplayer2ext.commonplayer.service.IPlayerServiceOwner;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerServiceManager;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.page.longvideo.service.ServiceOwnerType;

public class PlayerControlPanelContainerVisibleServiceOwner
    implements IPlayerServiceOwner<LongLogicProvider, LongPlayableParams, LongVideoParams> {
    @Override
    public String getName(){
        return ServiceOwnerType.PLAYER_CONTROL_PANEL_CONTATINER_VISIBLE_SERVICE.type;
    }

private PlayerServiceManager.Client<PlayerControlPanelContainerVisibleService, LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerControlPanelContainerVisibleServiceClient =
        new PlayerServiceManager.Client();

        @Override
        public void start(IPlayerServiceManager<LongLogicProvider, LongPlayableParams, LongVideoParams> serviceManager) {
        serviceManager.bindService(PlayerServiceManager.ServiceDescriptor.obtain(
        PlayerControlPanelContainerVisibleService.class), mPlayerControlPanelContainerVisibleServiceClient);
        }

        @Override
        public void stop(IPlayerServiceManager<LongLogicProvider, LongPlayableParams, LongVideoParams> serviceManager) {
        serviceManager.unbindService(PlayerServiceManager.ServiceDescriptor.obtain(
        PlayerControlPanelContainerVisibleService.class), mPlayerControlPanelContainerVisibleServiceClient);
        }

        @Override
        public  <T> T service() {
        return (T)mPlayerControlPanelContainerVisibleServiceClient.getService();
        }
        }