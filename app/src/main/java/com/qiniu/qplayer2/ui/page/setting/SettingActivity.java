package com.qiniu.qplayer2.ui.page.setting;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.lifecycle.Observer;
import com.qiniu.qmedia.component.player.QPlayerSetting;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.logic.PlayerSettingVM;

public class SettingActivity extends AppCompatActivity {

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

        private RadioGroup mSpeedRG;
        private RadioButton m_0_5_0_RB;
        private RadioButton m_0_7_5_RB;
        private RadioButton m_1_0_0_RB;
        private RadioButton m_1_2_5_RB;
        private RadioButton m_1_5_0_RB;
        private RadioButton m_2_0_0_RB;


        private EditText mStartPositionEdit;


        private PlayerSettingVM mPlayerSettingVM;

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                mPlayerSettingVM =new PlayerSettingVM(this.getLifecycle());

                setContentView(R.layout.activity_setting);

                mDecodrRG = findViewById(R.id.decoder_RG);
                mSeekRG = findViewById(R.id.seek_RG);
                mStartRG = findViewById(R.id.start_RG);
                mRenderRatioRG = findViewById(R.id.ratio_RG);
                mSpeedRG = findViewById(R.id.speed_RG);

                mAutoDecodrRB = findViewById(R.id.auto_decoder_RB);
                mSoftDecodrRB = findViewById(R.id.software_decoder_RB);
                mHardDecodrRB = findViewById(R.id.hardware_decoder_RB);
                mMixDecodrRB = findViewById(R.id.mix_decoder_RB);

                mNormalSeekRB = findViewById(R.id.normal_seek_RB);
                mAccurateSeekRB = findViewById(R.id.accurate_seek_RB);

                mPlayStartRB = findViewById(R.id.start_play_RB);
                mPauseStartRB = findViewById(R.id.start_pause_RB);

                mAutoRatioRB = findViewById(R.id.ratio_auto_RB);
                mStretchRatioRB = findViewById(R.id.ratio_stretch_RB);
                mFullScreenRatioRB = findViewById(R.id.ratio_full_screen_RB);
                m16_9RatioRB = findViewById(R.id.ratio_16_9_RB);
                m4_3RatioRB = findViewById(R.id.ratio_4_3_RB);

                m_0_5_0_RB = findViewById(R.id.speed_0_5_0_RB);
                m_0_7_5_RB = findViewById(R.id.speed_0_7_5_RB);
                m_1_0_0_RB = findViewById(R.id.speed_1_0_0_RB);
                m_1_2_5_RB = findViewById(R.id.speed_1_2_5_RB);
                m_1_5_0_RB = findViewById(R.id.speed_1_5_0_RB);
                m_2_0_0_RB = findViewById(R.id.speed_2_0_0_RB);

                mStartPositionEdit = findViewById(R.id.start_pos_ET);
                mSpeedRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if(checkedId==m_0_5_0_RB.getId()){
                                        mPlayerSettingVM.setSpeed(0.5f);
                                }else if(checkedId==m_0_7_5_RB.getId()){
                                        mPlayerSettingVM.setSpeed(0.75f);
                                }else if(checkedId==m_1_0_0_RB.getId()){
                                        mPlayerSettingVM.setSpeed(1.0f);
                                }else if(checkedId==m_1_2_5_RB.getId()){
                                        mPlayerSettingVM.setSpeed(1.25f);
                                }else if(checkedId==m_1_5_0_RB.getId()){
                                        mPlayerSettingVM.setSpeed(1.5f);
                                }else if(checkedId==m_2_0_0_RB.getId()){
                                        mPlayerSettingVM.setSpeed(2.0f);
                                }
                        }
                });


                mDecodrRG.setOnCheckedChangeListener ( (group, checkedId) ->{
                                if(checkedId==mAutoDecodrRB.getId()){
                                        mPlayerSettingVM.setDecoderType(QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_AUTO);
                                }else if(checkedId==mSoftDecodrRB.getId()){
                                        mPlayerSettingVM.setDecoderType(QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_SOFT_PRIORITY);
                                }else if(checkedId==mHardDecodrRB.getId()){
                                        mPlayerSettingVM.setDecoderType(QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_HARDWARE_PRIORITY);
                                }else if(checkedId==mMixDecodrRB.getId()){
                                        mPlayerSettingVM.setDecoderType(QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_FIRST_FRAME_ACCEL_PRIORITY);
                                }
                        }

                );

                mSeekRG.setOnCheckedChangeListener ((group, checkedId) ->{
                                if(checkedId==mNormalSeekRB.getId()){
                                        mPlayerSettingVM.setSeekType(QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_NORMAL);
                                }else if(checkedId==mAccurateSeekRB.getId()){
                                        mPlayerSettingVM.setSeekType(QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_ACCURATE);
                                }
                        }

                );

                mStartRG.setOnCheckedChangeListener ( (group, checkedId) ->{
                                if(checkedId==mPlayStartRB.getId()){
                                        mPlayerSettingVM.setStartType(QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PLAYING);
                                }else if(checkedId==mPauseStartRB.getId()){
                                        mPlayerSettingVM.setStartType(QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PAUSE);
                                }
                        }
                );

                mRenderRatioRG.setOnCheckedChangeListener ( (group, checkedId) ->{
                                if(checkedId==mAutoRatioRB.getId()){
                                        mPlayerSettingVM.setRenderRatio(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_AUTO);
                                }else if(checkedId==mStretchRatioRB.getId()){
                                        mPlayerSettingVM.setRenderRatio(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_STRETCH);
                                }else if(checkedId==mFullScreenRatioRB.getId()){
                                        mPlayerSettingVM.setRenderRatio(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_FULL_SCREEN);
                                }else if(checkedId==m16_9RatioRB.getId()){
                                        mPlayerSettingVM.setRenderRatio(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_16_9);
                                }else if(checkedId==m4_3RatioRB.getId()){
                                        mPlayerSettingVM.setRenderRatio(QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_4_3);
                                }
                        }
                );



                mStartPositionEdit.setOnFocusChangeListener ( (v, hasFocus) ->{
                                if (!hasFocus) {
                                        if(mStartPositionEdit.getEditableText().toString().isEmpty()) {
                                                mPlayerSettingVM.setStartPostion(0);
                                        } else {
                                                mPlayerSettingVM.setStartPostion(Long.valueOf(mStartPositionEdit.getEditableText().toString()));
                                        }

                                }
                        }

                );


                registerVM();
        }

        private void registerVM() {
                mPlayerSettingVM.decoderTypeLiveData.observe(this, it-> {
                        switch (it){
                                case QPLAYER_DECODER_SETTING_AUTO:
                                        mAutoDecodrRB.setChecked(true);
                                        break;
                                case QPLAYER_DECODER_SETTING_SOFT_PRIORITY:
                                        mSoftDecodrRB.setChecked(true);
                                        break;
                                case QPLAYER_DECODER_SETTING_HARDWARE_PRIORITY:
                                        mHardDecodrRB.setChecked(true);
                                        break;
                                case QPLAYER_DECODER_SETTING_FIRST_FRAME_ACCEL_PRIORITY:
                                        mMixDecodrRB.setChecked(true);
                                        break;
                        }
                });

                mPlayerSettingVM.seekTypeLiveData.observe(this, it -> {
                        switch (it){
                                case QPLAYER_SEEK_SETTING_NORMAL:
                                        mNormalSeekRB.setChecked(true);
                                        break;
                                case QPLAYER_SEEK_SETTING_ACCURATE:
                                        mAccurateSeekRB.setChecked(true);
                                        break;
                        }
                });

                mPlayerSettingVM.startTypeLiveData.observe(this, it -> {
                        switch (it){
                                case QPLAYER_START_SETTING_PLAYING:
                                        mPlayStartRB.setChecked(true);
                                        break;
                                case QPLAYER_START_SETTING_PAUSE:
                                        mPauseStartRB.setChecked(true);
                                        break;
                        }
                });

                mPlayerSettingVM.renderRatioLiveData.observe(this, it -> {
                        switch (it){
                                case QPLAYER_RATIO_SETTING_AUTO:
                                        mAutoRatioRB.setChecked(true);
                                        break;
                                case QPLAYER_RATIO_SETTING_STRETCH:
                                        mStretchRatioRB.setChecked(true);
                                        break;
                                case QPLAYER_RATIO_SETTING_FULL_SCREEN:
                                        mFullScreenRatioRB.setChecked(true);
                                        break;
                                case QPLAYER_RATIO_SETTING_16_9:
                                        m16_9RatioRB.setChecked(true);
                                        break;
                                case QPLAYER_RATIO_SETTING_4_3:
                                        m4_3RatioRB.setChecked(true);
                                        break;
                        }
                });

                mPlayerSettingVM.speedLiveData.observe(this, it -> {

                        if (it < 0.51f) {
                                m_0_5_0_RB.setChecked(true);
                        } else if (it < 0.76f)
                                m_0_7_5_RB.setChecked(true);
                        else if (it < 1.01f)
                                m_1_0_0_RB.setChecked(true);
                        else if (it < 1.26f)
                                m_1_2_5_RB.setChecked(true);
                        else if (it < 1.51)
                                m_1_5_0_RB.setChecked(true);
                        else
                                m_2_0_0_RB.setChecked(true);
                });

                mPlayerSettingVM.startPositionLiveData.observe(this, it -> {
                        mStartPositionEdit.setText(it.toString());
                });


        }

        private void unregisterVM() {
                mPlayerSettingVM.decoderTypeLiveData.removeObservers(this);
                mPlayerSettingVM.seekTypeLiveData.removeObservers(this);
                mPlayerSettingVM.startTypeLiveData.removeObservers(this);
                mPlayerSettingVM.startPositionLiveData.removeObservers(this);
                mPlayerSettingVM.speedLiveData.removeObservers(this);
        }

        @Override
        public void onPause() {
                super.onPause();


                if(mStartPositionEdit.getEditableText().toString().isEmpty()) {
                        mPlayerSettingVM.setStartPostion(0);
                } else {
                        mPlayerSettingVM.setStartPostion(Long.valueOf(mStartPositionEdit.getEditableText().toString()));
                }
        }
        @Override
        public void onDestroy() {
                super.onDestroy();
                unregisterVM();
        }
}