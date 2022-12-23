package com.qiniu.qplayer2.ui.page.longvideo;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatDelegate;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayer;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerConfig;
import com.qiniu.qplayer2ext.commonplayer.data.DisplayOrientation;
import com.qiniu.qplayer2ext.commonplayer.data.CommonPlayerDataSource;
import com.qiniu.qplayer2ext.commonplayer.layer.control.ControlPanelConfig;
import com.qiniu.qplayer2ext.commonplayer.layer.control.ControlPanelConfigElement;
import com.qiniu.qplayer2ext.commonplayer.screen.ScreenType;
import com.qiniu.qplayer2.repository.setting.PlayerSettingRespostory;
import com.qiniu.qplayer2.ui.page.longvideo.service.ServiceOwnerType;
import com.qiniu.qplayer2.ui.page.longvideo.service.buffering.PlayerBufferingService;
import com.qiniu.qplayer2.ui.page.longvideo.service.buffering.PlayerBufferingServiceOwner;
import com.qiniu.qplayer2.ui.page.longvideo.service.controlpanelcontainervisible.PlayerControlPanelContainerVisibleServiceOwner;
import com.qiniu.qplayer2.ui.page.longvideo.service.network.PlayerNetworkServiceOwner;
import com.qiniu.qplayer2.ui.page.longvideo.service.panorama.PlayerPanoramaTouchSeriviceOwner;
import com.qiniu.qplayer2.ui.page.longvideo.service.toast.PlayerToastServiceOwner;

import java.util.ArrayList;
import java.util.Arrays;

public class LongVideoActivity extends AppCompatActivity {

        private CommonPlayer<Object,
                LongLogicProvider, LongPlayableParams, LongVideoParams> mCommonPlayer;

        private CommonPlayerDataSource<LongPlayableParams, LongVideoParams> mPlayerDataSource;
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
                setContentView(R.layout.activity_long_video);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                initCommonPlayer();
        }

        @Override
        public void onDestroy() {
                mCommonPlayer.release();
                super.onDestroy();
        }

        private void initCommonPlayer() {
                mPlayerDataSource = LongPlayerDataSourceFactory.create();
                ArrayList<ScreenType> arrayList1=new ArrayList<>();
                arrayList1.add(ScreenType.HALF_SCREEN);
                ArrayList<ScreenType> arrayList2=new ArrayList<>();
                arrayList2.add(ScreenType.FULL_SCREEN);
                arrayList2.add(ScreenType.REVERSE_FULL_SCREEN);
                CommonPlayerConfig<Object,
                        LongLogicProvider, LongPlayableParams, LongVideoParams> config = new CommonPlayerConfig.Builder<Object,
                        LongLogicProvider, LongPlayableParams, LongVideoParams>()
                        .addControlPanel(
                                new ControlPanelConfig(
                                        LongControlPanelType.Normal.type,
                                        Arrays.asList(
                                                new ControlPanelConfigElement(
                                                        R.layout.control_panel_halfscreen_landscape_normal,
                                                        arrayList1,
                                                        DisplayOrientation.LANDSCAPE),
                                                new ControlPanelConfigElement(
                                                        R.layout.control_panel_fullscreen_landscape_normal,
                                                        arrayList2,
                                                        DisplayOrientation.LANDSCAPE)
                                        )
                                )
                        )
                        .addEnviroment(LongEnviromentType.LONG.type,
                                new LongPlayerEnviroment())
                        .setCommonPlayerScreenChangedListener(new LongCommonPlayerScreenChangedListener(this, findViewById(R.id.video_container_FL)))
                        .setLogicProvider(new LongLogicProvider())
                        .setPlayerDataSource(mPlayerDataSource)
                        .setContext(this)
                        .addServiceOwner(new PlayerControlPanelContainerVisibleServiceOwner())
                        .addServiceOwner(new PlayerToastServiceOwner())
                        .addServiceOwner(new PlayerBufferingServiceOwner())
                        .addServiceOwner(new PlayerNetworkServiceOwner())
                        .addServiceOwner(new PlayerPanoramaTouchSeriviceOwner())
                        .setRootUIContanier(this, findViewById(R.id.video_container_FL))
                        .enableControlPanel()
                        .enableFunctionWidget()
                        .enableGesture()
                        .enableToast()
                        .enableScreenRender(CommonPlayerConfig.ScreenRenderType.SURFACE_VIEW)
                        .setDecodeType(PlayerSettingRespostory.getInstance().getDecoderType())
                        .setSeekMode(PlayerSettingRespostory.playerSettingRespostory.getSeekMode())
                        .setBlindType(PlayerSettingRespostory.playerSettingRespostory.getBlindType())
                        .setStartAction(PlayerSettingRespostory.playerSettingRespostory.getStartAction())
                        .setSpeed(PlayerSettingRespostory.playerSettingRespostory.getPlaySpeed())
                        .setRenderRatio(PlayerSettingRespostory.getInstance().getRatioType())
                        .setSEIEnable(PlayerSettingRespostory.getInstance().getSeiEnable())
                        .build();

                mCommonPlayer = new CommonPlayer(config);
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                mCommonPlayer.getPlayerVideoSwitcher().switchVideo(mPlayerDataSource.getVideoParamsList().get(0).getId(),new Bundle());
                                Log.e("======",mPlayerDataSource.getVideoParamsList().get(0).title+"=播放="+mPlayerDataSource.getVideoParamsList().get(0).getId());
                        }
                },2000);

        }
}