package com.qiniu.qplayer2.ui.page.longvideo.service.videoquality;

import androidx.annotation.NonNull;

import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerService;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;

class PlayerVideoQualityService implements IPlayerService<LongLogicProvider, LongPlayableParams, LongVideoParams>{
        @Override public void onStart() {
//        TODO("Not yet implemented");
        }

        @Override public void onStop() {
//        TODO("Not yet implemented")
        }

        @Override public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
//        TODO("Not yet implemented")
        }

    @Override
    public void onPlayerReset() {

    }

    @NonNull
    @Override
    public PlayerServiceManager.ServiceConfig serviceConfig() {
        return null;
    }
}