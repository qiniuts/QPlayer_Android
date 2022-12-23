package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import com.qiniu.qmedia.component.player.QPlayerSetting;
import com.qiniu.qplayer2.R;

import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.data.CommonPlayerDataSource;
import com.qiniu.qplayer2ext.commonplayer.layer.function.BaseFunctionWidget;
import com.qiniu.qplayer2.repository.setting.PlayerSettingRespostory;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetConfig;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class SettingFunctionWidget extends
        BaseFunctionWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

private RadioGroup mDecodrRG;
private RadioButton mAutoDecodrRB;
private RadioButton mSoftDecodrRB;
private RadioButton mHardDecodrRB;
private RadioButton mMixDecodrRB;

private RadioGroup mSeekRG;
private RadioButton mNormalSeekRB;
private RadioButton mAccurateSeekRB;

private RadioGroup mStartRG;
private RadioButton mPlayStartRB;
private RadioButton mPauseStartRB;

private RadioGroup mRenderRatioRG;
private RadioButton mAutoRatioRB;
private RadioButton mStretchRatioRB;
private RadioButton mFullScreenRatioRB;
private RadioButton m16_9RatioRB;
private RadioButton m4_3RatioRB;

private RadioGroup mBlindRG;
private RadioButton mNoneBlindRB;
private RadioButton mRedBlindRB;
private RadioButton mGreenBlindRB;
private RadioButton mBlueBlindRB;

private EditText mStartPositionEdit;

private CheckBox mForceFlushAuthenticationCB;

private CheckBox mSEIEnableCB;
public SettingFunctionWidget(Context context){
    super(context);

}
private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;


        @NonNull
        @Override
        public String getTag() {
                return "SettingFunctionWidget";
        }
        @Override
        public FunctionWidgetConfig getFunctionWidgetConfig(){
                FunctionWidgetConfig.Builder builder =new FunctionWidgetConfig.Builder();
                builder.dismissWhenActivityStop(true);
                builder.dismissWhenScreenTypeChange(true);
                builder.dismissWhenVideoChange(true);
                builder.dismissWhenVideoCompleted(true);
                builder.persistent(true);
                builder.changeOrientationDisableWhenShow(true);
                return builder.build();
        }

            @Override
            public void onWidgetShow() {
                    updateDecoder(PlayerSettingRespostory.getInstance().getDecoderType());
                    updateStartType(PlayerSettingRespostory.getInstance().getStartAction());
                    updateSeekType(PlayerSettingRespostory.getInstance().getSeekMode());
                    updateRenderRatio(PlayerSettingRespostory.getInstance().getRatioType());
                    updateStartPosition(PlayerSettingRespostory.getInstance().getStartPosition());
                    updateBlindType(PlayerSettingRespostory.getInstance().getBlindType());
                    updateSEIEnable(PlayerSettingRespostory.getInstance().getSeiEnable());
            updateForceFlushAuthentication();
//        registerSubjects()
            registerClickListeners();
            }

            @Override
            public void onWidgetDismiss() {
//        unregisterSubjects()

            if(mStartPositionEdit.getEditableText().toString().isEmpty()) {
            updateDataSourceStartPos(0);
            } else {
            updateDataSourceStartPos(Long.valueOf(mStartPositionEdit.getEditableText().toString()));
            }
            unRegisterClickListeners();
            }

            @Override
            public View createContentView( Context context) {
            View view = LayoutInflater.from(getMContext()).inflate(R.layout.function_setting, null);
            mDecodrRG = view.findViewById(R.id.decoder_RG);
            mSeekRG = view.findViewById(R.id.seek_RG);
            mStartRG = view.findViewById(R.id.start_RG);
            mRenderRatioRG = view.findViewById(R.id.ratio_RG);

            mAutoDecodrRB = view.findViewById(R.id.auto_decoder_RB);
            mSoftDecodrRB = view.findViewById(R.id.software_decoder_RB);
            mHardDecodrRB = view.findViewById(R.id.hardware_decoder_RB);
            mMixDecodrRB = view.findViewById(R.id.mix_decoder_RB);

            mNormalSeekRB = view.findViewById(R.id.normal_seek_RB);
            mAccurateSeekRB = view.findViewById(R.id.accurate_seek_RB);

            mPlayStartRB = view.findViewById(R.id.start_play_RB);
            mPauseStartRB = view.findViewById(R.id.start_pause_RB);

            mAutoRatioRB = view.findViewById(R.id.ratio_auto_RB);
            mStretchRatioRB = view.findViewById(R.id.ratio_stretch_RB);
            mFullScreenRatioRB = view.findViewById(R.id.ratio_full_screen_RB);
            m16_9RatioRB = view.findViewById(R.id.ratio_16_9_RB);
            m4_3RatioRB = view.findViewById(R.id.ratio_4_3_RB);

            mBlindRG = view.findViewById(R.id.blind_RG);
            mNoneBlindRB = view.findViewById(R.id.blind_none_RB);
            mRedBlindRB = view.findViewById(R.id.blind_red_RB);
            mGreenBlindRB = view.findViewById(R.id.blind_green_RB);
            mBlueBlindRB = view.findViewById(R.id.blind_blue_RB);

            mStartPositionEdit = view.findViewById(R.id.start_pos_ET);


            mForceFlushAuthenticationCB = view.findViewById(R.id.flush_authentication_CB);

            mSEIEnableCB = view.findViewById(R.id.sei_CB);


            return view;
            }

private void updateDecoder(QPlayerSetting.QPlayerDecoder decoderType) {
            if(decoderType==QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_AUTO){
                mAutoDecodrRB.setChecked(true);
            }else if(decoderType==QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_SOFT_PRIORITY){
                mSoftDecodrRB.setChecked(true);
            }else if(decoderType==QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_HARDWARE_PRIORITY){
                mHardDecodrRB.setChecked(true);
            }else if(decoderType==QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_FIRST_FRAME_ACCEL_PRIORITY){
                mMixDecodrRB.setChecked(true);
            }
        }

private void updateSEIEnable(boolean enable) {
        mSEIEnableCB.setChecked(enable);
        }

private void updateForceFlushAuthentication() {
        mForceFlushAuthenticationCB.setChecked(false);
        }
private void updateStartType(QPlayerSetting.QPlayerStart startType) {
    if(startType==QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PLAYING){
        mPlayStartRB.setChecked(true);
    }else if(startType==QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PAUSE){
        mPauseStartRB.setChecked(true);
    }
        }

private void updateSeekType(QPlayerSetting.QPlayerSeek seekType) {
    if(seekType==QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_NORMAL){
        mNormalSeekRB.setChecked(true);
    }else if(seekType==QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_ACCURATE){
        mAccurateSeekRB.setChecked(true);
    }
        }

private void updateRenderRatio(QPlayerSetting.QPlayerRenderRatio renderRatio) {
    if(renderRatio==QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_AUTO){
        mAutoRatioRB.setChecked(true);
    }else if(renderRatio==QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_STRETCH){
        mStretchRatioRB.setChecked(true);
    }else if(renderRatio==QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_FULL_SCREEN){
        mFullScreenRatioRB.setChecked(true);
    }else if(renderRatio==QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_16_9){
        m16_9RatioRB.setChecked(true);
    }else if(renderRatio==QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_4_3){
        m4_3RatioRB.setChecked(true);
    }
        }



private void updateBlindType(QPlayerSetting.QPlayerBlind blindType) {
    if(blindType==QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_NONE){
        mNoneBlindRB.setChecked(true);
    }else if(blindType==QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_RED){
        mRedBlindRB.setChecked(true);
    }else if(blindType==QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_GREEN){
        mGreenBlindRB.setChecked(true);
    }else if(blindType==QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_BLUE){
        mBlueBlindRB.setChecked(true);
    }
        }

private void updateStartPosition(long startPos) {
        mStartPositionEdit.setText(String.valueOf(startPos));
        }
//
//    private fun registerSubjects() {
//        PlayerSettingRespostory.decoderTypeSubject
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy {
//                onNext {
//                    updateDecoder(it)
//                }
//                onError { }
//            } into mCompositeDisposable
//
//
//        PlayerSettingRespostory.seekTypeSubject
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy {
//                onNext {
//                    updateSeekType(it)
//
//                }
//                onError { }
//            } into mCompositeDisposable
//
//        PlayerSettingRespostory.startTypeSubject
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy {
//                onNext {
//                    updateStartType(it)
//                }
//                onError { }
//            } into mCompositeDisposable
//
//
//        PlayerSettingRespostory.renderRatioSubject
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy {
//                onNext {
//                    updateRenderRatio(it)
//                }
//                onError { }
//            } into mCompositeDisposable
//
//
//
//        PlayerSettingRespostory.startPositionSubject
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy {
//                onNext {
//                    updateStartPosition(it)
//                }
//                onError { }
//            } into mCompositeDisposable
//
//
//        PlayerSettingRespostory.blindTypeSubject
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeBy {
//                onNext {
//                    updateBlindType(it)
//                }
//                onError { }
//            } into mCompositeDisposable
//
//    }
//
//    private fun unregisterSubjects() {
//        mCompositeDisposable.clear()
//    }

private void unRegisterClickListeners() {
        mDecodrRG.setOnCheckedChangeListener(null);

        mSeekRG.setOnCheckedChangeListener(null);

        mStartRG.setOnCheckedChangeListener(null);

        mRenderRatioRG.setOnCheckedChangeListener(null);

        mBlindRG.setOnCheckedChangeListener(null);

        mStartPositionEdit.setOnFocusChangeListener(null);

        mForceFlushAuthenticationCB.setOnCheckedChangeListener(null);

        mSEIEnableCB.setOnCheckedChangeListener(null);
        }
private void registerClickListeners() {
    mDecodrRG.setOnCheckedChangeListener((group, checkedId) ->{
        if(checkedId==mAutoDecodrRB.getId()){
            PlayerSettingRespostory.getInstance().setDecoderType(QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_AUTO);
        }else if(checkedId==mSoftDecodrRB.getId()){
            PlayerSettingRespostory.getInstance().setDecoderType(QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_SOFT_PRIORITY);
        }else if(checkedId==mHardDecodrRB.getId()){
            PlayerSettingRespostory.getInstance().setDecoderType(QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_HARDWARE_PRIORITY);
        }else if(checkedId==mMixDecodrRB.getId()){
            PlayerSettingRespostory.getInstance().setDecoderType(QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_FIRST_FRAME_ACCEL_PRIORITY);
        }
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().setDecodeType(PlayerSettingRespostory.getInstance().getDecoderType());
    });
    mSeekRG.setOnCheckedChangeListener((group, checkedId) ->{
        if(checkedId==mNormalSeekRB.getId()){
            PlayerSettingRespostory.getInstance().setSeekMode(QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_NORMAL);
        }else if(checkedId==mAccurateSeekRB.getId()){
            PlayerSettingRespostory.getInstance().setSeekMode(QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_ACCURATE);
        }
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().setSeekMode(PlayerSettingRespostory.getInstance().getSeekMode());
    });
    mStartRG.setOnCheckedChangeListener((group, checkedId) ->{
        if(checkedId==mPlayStartRB.getId()){
            PlayerSettingRespostory.getInstance().setStartAction(QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PLAYING);
        }else if(checkedId==mPauseStartRB.getId()){
            PlayerSettingRespostory.getInstance().setStartAction(QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PAUSE);
        }
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().setStartAction(PlayerSettingRespostory.getInstance().getStartAction());
    });

    mRenderRatioRG.setOnCheckedChangeListener((group, checkedId) ->{
        if(checkedId==mAutoRatioRB.getId()){
            PlayerSettingRespostory.getInstance().setRatioType(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_AUTO);
        }else if(checkedId==mStretchRatioRB.getId()){
            PlayerSettingRespostory.getInstance().setRatioType(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_STRETCH);
        }else if(checkedId==mFullScreenRatioRB.getId()){
            PlayerSettingRespostory.getInstance().setRatioType(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_FULL_SCREEN);
        }else if(checkedId==m16_9RatioRB.getId()){
            PlayerSettingRespostory.getInstance().setRatioType(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_16_9);
        }else if(checkedId==m4_3RatioRB.getId()){
            PlayerSettingRespostory.getInstance().setRatioType(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_4_3);
        }
        mPlayerCore.getMPlayerContext().getPlayerRenderHandler().setRenderRatio(PlayerSettingRespostory.getInstance().getRatioType());
    });
    mBlindRG.setOnCheckedChangeListener((group, checkedId) ->{
        if(checkedId==mNoneBlindRB.getId()){
            PlayerSettingRespostory.getInstance().setBlindType(QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_NONE);
        }else if(checkedId==mRedBlindRB.getId()){
            PlayerSettingRespostory.getInstance().setBlindType(QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_RED);
        }else if(checkedId==mGreenBlindRB.getId()){
            PlayerSettingRespostory.getInstance().setBlindType(QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_GREEN);
        }else if(checkedId==mBlueBlindRB.getId()){
            PlayerSettingRespostory.getInstance().setBlindType(QPlayerSetting.QPlayerBlind.QPLAYER_BLIND_SETTING_BLUE);
        }
        mPlayerCore.getMPlayerContext().getPlayerRenderHandler().setBlindType(PlayerSettingRespostory.getInstance().getBlindType());
    });

    mStartPositionEdit.setOnFocusChangeListener((v, hasFocus) ->{
        if (!hasFocus) {
            updateDataSourceStartPos(Long.valueOf(mStartPositionEdit.getEditableText().toString()));
        }
    });
    mSEIEnableCB.setOnCheckedChangeListener((cb, is_checked) ->{
        PlayerSettingRespostory.getInstance().setSeiEnable(is_checked);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().setSEIEnable(is_checked);
    });
    mForceFlushAuthenticationCB.setOnCheckedChangeListener((cb, is_checked) ->{
        if (is_checked) {
            mPlayerCore.getMPlayerContext().getPlayerControlHandler().forceAuthenticationFromNetwork();
        }
    });
        }

private void updateDataSourceStartPos(long startPos) {
    PlayerSettingRespostory.getInstance().setStartPosition(startPos);
    mPlayerCore.getMCommonPlayerVideoSwitcher().updateDataSource(new CommonPlayerDataSource.CommonPlayableParamsUpdater<LongPlayableParams, LongVideoParams>() {
        @Override
        public void update(@NonNull LongPlayableParams playableParams) {
            if (!playableParams.getMediaModel().isLive()) {
                playableParams.setStartPos(PlayerSettingRespostory.getInstance().getStartPosition());
            }
        }

        @Override
        public void update(@NonNull LongVideoParams videoParams) {

        }

        @Override
        public boolean filter(@NonNull LongVideoParams videoParams, @NonNull List<? extends LongPlayableParams> playableParamsList) {
            return false;
        }
    });

                    }

                    @Override
                    public void onRelease() {
                    }

                    @Override
                    public void onConfigurationChanged(Configuration configuration) {
                    }

                    @Override
                    public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }
        }