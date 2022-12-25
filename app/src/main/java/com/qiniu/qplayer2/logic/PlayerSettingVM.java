package com.qiniu.qplayer2.logic;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import com.qiniu.qmedia.component.player.QPlayerSetting;

import com.qiniu.qplayer2.repository.setting.PlayerSettingRespostory;

public class PlayerSettingVM {
        private static final String TAG = "PlayerSettingVM";
        private Lifecycle lifeCycle;
        public PlayerSettingVM(Lifecycle lifeCycle){
                this.lifeCycle=lifeCycle;
                init();
        }
        public MutableLiveData<QPlayerSetting.QPlayerDecoder> decoderTypeLiveData =
                new MutableLiveData<QPlayerSetting.QPlayerDecoder>(PlayerSettingRespostory.getInstance().getDecoderType());
        public MutableLiveData<QPlayerSetting.QPlayerSeek> seekTypeLiveData =
                new MutableLiveData<QPlayerSetting.QPlayerSeek>(PlayerSettingRespostory.getInstance().getSeekMode());
        public MutableLiveData<QPlayerSetting.QPlayerStart> startTypeLiveData =
                new MutableLiveData<QPlayerSetting.QPlayerStart>(PlayerSettingRespostory.getInstance().getStartAction());

        public MutableLiveData<QPlayerSetting.QPlayerRenderRatio> renderRatioLiveData =
                new MutableLiveData<QPlayerSetting.QPlayerRenderRatio>(PlayerSettingRespostory.getInstance().getRatioType());

        public MutableLiveData<Float> speedLiveData =
                new MutableLiveData<Float>(PlayerSettingRespostory.getInstance().getPlaySpeed());

        public MutableLiveData<Long> startPositionLiveData =
                new MutableLiveData<Long>(PlayerSettingRespostory.getInstance().getStartPosition());
        public PlayerSettingVM(){
                init();
        }
        private void init() {
                decoderTypeLiveData.setValue(PlayerSettingRespostory.getInstance().getDecoderType());
                seekTypeLiveData.setValue(PlayerSettingRespostory.getInstance().getSeekMode());
                startTypeLiveData.setValue(PlayerSettingRespostory.getInstance().getStartAction());
                renderRatioLiveData.setValue(PlayerSettingRespostory.getInstance().getRatioType());
                speedLiveData.setValue(PlayerSettingRespostory.getInstance().getPlaySpeed());
                startPositionLiveData.setValue(PlayerSettingRespostory.getInstance().getStartPosition());
        }

        public void setDecoderType(QPlayerSetting.QPlayerDecoder type) {
                PlayerSettingRespostory.getInstance().setDecoderType(type);
        }

        public void setSeekType(QPlayerSetting.QPlayerSeek type) {
                PlayerSettingRespostory.getInstance().setSeekMode(type);
        }

        public void  setStartType(QPlayerSetting.QPlayerStart type) {
                PlayerSettingRespostory.getInstance().setStartAction(type);
        }

        public void  setRenderRatio(QPlayerSetting.QPlayerRenderRatio type) {
                PlayerSettingRespostory.getInstance().setRatioType(type);
        }

        public void  setSpeed(float speed) {
                PlayerSettingRespostory.getInstance().setPlaySpeed(speed);
        }

        public void  setStartPostion(long pos) {
                PlayerSettingRespostory.getInstance().setStartPosition(pos);
        }
}
