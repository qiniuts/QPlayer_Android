package com.qiniu.qplayer2.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qplayer2.logic.PlayerProgressVM;

public class PlayerSeekWidget extends AppCompatSeekBar implements
        SeekBar.OnSeekBarChangeListener {
        public static final String TAG = "PlayerSeekWidget";
        PlayerProgressVM mPlayerProgressVM = new PlayerProgressVM();
        private Observer<Long> mProgressObserver=new Observer<Long>() {
                @Override
                public void onChanged(Long it) {
                        Log.e("==",it+"=setProgress=="+mPlayerProgressVM.getDuration());
                        setProgress((int)(it * 1000 / mPlayerProgressVM.getDuration()));
                }
        };
        private Observer<Long> mBufferProgressObserver=new Observer<Long>() {
                @Override
                public void onChanged(Long it) {
                        setSecondaryProgress((int)(it * 1000 / mPlayerProgressVM.getDuration()));
                }
        };
        public PlayerSeekWidget(Context context) {
                super(context);
        }

        public PlayerSeekWidget(Context context,AttributeSet attrs) {
                super(context, attrs);
        }

        public PlayerSeekWidget(Context context,AttributeSet attrs,int defStyleAttr) {
                super(context, attrs, defStyleAttr);
        }

        public void setPlayerControlHandler(QPlayerControlHandler controlHandler) {
                mPlayerProgressVM.setQPlayerControlHandler(controlHandler);
                if (controlHandler != null) {
                        init();
                } else {
                        uninit();
                }
        }

        private void init() {
                mPlayerProgressVM.playerProgressLiveData.observeForever(mProgressObserver);
                mPlayerProgressVM.playBufferProgressLiveData.observeForever(mBufferProgressObserver);
                setOnSeekBarChangeListener(this);
        }

        private void uninit() {
                mPlayerProgressVM.playerProgressLiveData.removeObserver(mProgressObserver);
                mPlayerProgressVM.playBufferProgressLiveData.removeObserver(mBufferProgressObserver);
                setOnSeekBarChangeListener(null);
        }

        @Override
        public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayerProgressVM.seek(getProgress() * mPlayerProgressVM.getDuration() / 1000 );
        }
}