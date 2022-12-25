package com.qiniu.qplayer2.repository.setting;

import android.content.SharedPreferences;
import com.qiniu.qmedia.component.player.QPlayerSetting;
import com.qiniu.qplayer2.application.QPlayerApplicationContext;
import com.qiniu.qplayer2.repository.common.SharedPreferencesHelper;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class PlayerSettingRespostory implements SharedPreferences.OnSharedPreferenceChangeListener {


    private static final String INIT_SETTING_SP_NAME = "InitSettingSP";
    private static final String DECODER_SETTING_KEY_NAME = "DecoderSetting";
    private static final String  SEEK_SETTING_KEY_NAME = "SeekSetting";
    private static final String  START_SETTING_KEY_NAME = "StartSetting";
    private static final String  RENDER_RATIO_SETTING_KEY_NAME = "RenderRatioSetting";
    private static final String  SPEED_SETTING_KEY_NAME = "SpeedSetting";
    private static final String  START_POSITION_SETTING_KEY_NAME = "StartPositionSetting";
    private static final String  BLIND_SETTING_KEY_NAME = "BlindSetting";
    private static final String  SEI_SETTING_KEY_NAME = "SEISetting";


    public PublishSubject<QPlayerSetting.QPlayerDecoder> decoderTypeSubject =  PublishSubject.create();
    public PublishSubject<QPlayerSetting.QPlayerSeek> seekTypeSubject = PublishSubject.create();
    public PublishSubject<QPlayerSetting.QPlayerStart> startTypeSubject = PublishSubject.create();
    public PublishSubject<QPlayerSetting.QPlayerRenderRatio> renderRatioSubject = PublishSubject.create();
    public PublishSubject<QPlayerSetting.QPlayerBlind> blindTypeSubject = PublishSubject.create();
    public PublishSubject<Float> speedSubject = PublishSubject.create();
    public PublishSubject<Long> startPositionSubject = PublishSubject.create();


    private SharedPreferencesHelper mSharedPreferencesHelper =
            new SharedPreferencesHelper(QPlayerApplicationContext.applicationContext, INIT_SETTING_SP_NAME);
    public PlayerSettingRespostory(){
        init();
    }
    public static PlayerSettingRespostory playerSettingRespostory;
    public static PlayerSettingRespostory getInstance(){
        if(playerSettingRespostory==null){
            playerSettingRespostory=new PlayerSettingRespostory();
        }
        return playerSettingRespostory;
    }
    private void init()   {
        mSharedPreferencesHelper.registerOnSharedPreferenceChangeListener(this);
    }

    private QPlayerSetting.QPlayerBlind blindType;
    public QPlayerSetting.QPlayerBlind getBlindType(){
        int type = mSharedPreferencesHelper.optInteger(
                BLIND_SETTING_KEY_NAME,
                QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_NONE.getValue()
        );
        for(QPlayerSetting.QPlayerBlind item:QPlayerSetting.QPlayerBlind.values()){
            if(item.getValue()==type){
                return item;
            }
        }
        return QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_NONE;
    }
    public void setBlindType(QPlayerSetting.QPlayerBlind value) {
        mSharedPreferencesHelper.setInteger(BLIND_SETTING_KEY_NAME, value.getValue());
    }

    private QPlayerSetting.QPlayerDecoder decoderType;
    public QPlayerSetting.QPlayerDecoder getDecoderType(){
        int type= mSharedPreferencesHelper.optInteger(
                DECODER_SETTING_KEY_NAME,
                QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_AUTO.getValue()
        );
        for(QPlayerSetting.QPlayerDecoder item:QPlayerSetting.QPlayerDecoder.values()){
            if(item.getValue()==type){
                return item;
            }
        }
        return QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_AUTO;
    }
    public void setDecoderType(QPlayerSetting.QPlayerDecoder value) {
        mSharedPreferencesHelper.setInteger(DECODER_SETTING_KEY_NAME, value.getValue());
    }


    private QPlayerSetting.QPlayerSeek seekMode;
    public QPlayerSetting.QPlayerSeek getSeekMode(){
        int type = mSharedPreferencesHelper.optInteger(
                START_SETTING_KEY_NAME,
                QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_NORMAL.getValue()
        );
        for(QPlayerSetting.QPlayerSeek item:QPlayerSetting.QPlayerSeek.values()){
            if(item.getValue()==type){
                return item;
            }
        }
        return QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_NORMAL;
    }

    public void setSeekMode(QPlayerSetting.QPlayerSeek value) {
        mSharedPreferencesHelper.setInteger(START_SETTING_KEY_NAME, value.getValue());
    }

    private QPlayerSetting.QPlayerStart startAction;

    public QPlayerSetting.QPlayerStart getStartAction() {
        int type = mSharedPreferencesHelper.optInteger(
                SEEK_SETTING_KEY_NAME,
                QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PLAYING.getValue()
        );
        for(QPlayerSetting.QPlayerStart item:QPlayerSetting.QPlayerStart.values()){
            if(item.getValue()==type){
                return item;
            }
        }
        return QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PLAYING;

    }
    public void setStartAction(QPlayerSetting.QPlayerStart value) {
        mSharedPreferencesHelper.setInteger(SEEK_SETTING_KEY_NAME, value.getValue());
    }

    private QPlayerSetting.QPlayerRenderRatio ratioType;
    public QPlayerSetting.QPlayerRenderRatio getRatioType() {
        int type = mSharedPreferencesHelper.optInteger(
                RENDER_RATIO_SETTING_KEY_NAME,
                QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_AUTO.getValue()
        );
        for(QPlayerSetting.QPlayerRenderRatio item:QPlayerSetting.QPlayerRenderRatio.values()){
            if(item.getValue()==type){
                return item;
            }
        }
        return QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_AUTO;

    }
    public void setRatioType(QPlayerSetting.QPlayerRenderRatio value) {
        mSharedPreferencesHelper.setInteger(RENDER_RATIO_SETTING_KEY_NAME, value.getValue());
    }

    private float playSpeed;
    public float getPlaySpeed() {
        return mSharedPreferencesHelper.optFloat(
                SPEED_SETTING_KEY_NAME,
                1.0f
        );
    }
    public void setPlaySpeed(float value) {
        mSharedPreferencesHelper.setBoolean(SPEED_SETTING_KEY_NAME, value);
    }

    long startPosition;
    public long getStartPosition() {
        return mSharedPreferencesHelper.optLong(
                START_POSITION_SETTING_KEY_NAME,
                0
        );
    }
    public void setStartPosition(long value) {
        mSharedPreferencesHelper.setLong(START_POSITION_SETTING_KEY_NAME, value);
    }

    public boolean seiEnable;
    public boolean getSeiEnable() {
        return mSharedPreferencesHelper.optBoolean(
                SEI_SETTING_KEY_NAME,
                false
        );
    }
    public void setSeiEnable(boolean value) {
        mSharedPreferencesHelper.setBoolean(SEI_SETTING_KEY_NAME, value);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) {
//        if (key.equals(DECODER_SETTING_KEY_NAME)) {
//            decoderTypeSubject.onNext(decoderType);
//        } else if (key.equals(SEEK_SETTING_KEY_NAME)) {
//            seekTypeSubject.onNext(seekMode);
//        } else if (key.equals(START_SETTING_KEY_NAME)) {
//            startTypeSubject.onNext(startAction);
//        } else if (key.equals(RENDER_RATIO_SETTING_KEY_NAME)) {
//            renderRatioSubject.onNext(ratioType);
//        } else if (key.equals(SPEED_SETTING_KEY_NAME)) {
//            speedSubject.onNext(playSpeed);
//        } else if (key.equals(START_POSITION_SETTING_KEY_NAME)) {
//            startPositionSubject.onNext(startPosition);
//        }
    }
}