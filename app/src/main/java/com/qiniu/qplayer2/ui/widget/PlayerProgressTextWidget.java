package com.qiniu.qplayer2.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;

import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qplayer2.logic.PlayerPausePlayVM;
import com.qiniu.qplayer2.logic.PlayerProgressVM;

import java.util.*;

public class PlayerProgressTextWidget extends AppCompatTextView {

private PlayerProgressVM mPlayerProgressVM=new PlayerProgressVM();

        public PlayerProgressTextWidget(Context context) {
                super(context);
        }

        public PlayerProgressTextWidget(Context context,AttributeSet attrs)  {
                super(context, attrs);
        }

        public PlayerProgressTextWidget(Context context,AttributeSet attrs,int defStyleAttr) {
                super(context,attrs,defStyleAttr);
        }

        public void setPlayerControlHandler(QPlayerControlHandler controlHandler) {
        mPlayerProgressVM.setQPlayerControlHandler(controlHandler);
        init();
        }

private void init() {
        updateTime(0, 0);
        mPlayerProgressVM.playerProgressLiveData.observeForever(new Observer<Long>() {
                @Override
                public void onChanged(Long it) {
                        updateTime(mPlayerProgressVM.getDuration(), it);
                }
        });
        }

@SuppressLint("SetTextI18n")
private void updateTime(long currentPosition ,long duration) {
        String proStr = formatTime(currentPosition);
        if (TextUtils.isEmpty(proStr)) {
        proStr = "00:00";
        }
        String durStr = formatTime(duration);
        if (TextUtils.isEmpty(durStr)) {
        durStr = "00:00";
        }
         setText(proStr+"/"+durStr);
        }

private String formatTime(long position) {
        // 毫秒转秒，向上取整
        long totalSeconds = (position + 999) / 1000;

        long seconds = totalSeconds % 60;
        long minutes = totalSeconds / 60;

        return String.format(Locale.US, "%02d:%02d", minutes, seconds);
        }
        }