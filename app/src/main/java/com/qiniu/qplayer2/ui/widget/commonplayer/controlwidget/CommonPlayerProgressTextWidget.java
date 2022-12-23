package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.qiniu.qmedia.component.player.QIPlayerProgressListener;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import java.util.*;

public class CommonPlayerProgressTextWidget extends AppCompatTextView implements
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
private QIPlayerProgressListener mPlayerProgressListener=new QIPlayerProgressListener() {
    @Override
    public void onProgressChanged(long duration, long progress) {
        updateTime(progress, duration);
    }
};
        public CommonPlayerProgressTextWidget(Context context) {
            super(context);
        }

    public CommonPlayerProgressTextWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
        }

    public CommonPlayerProgressTextWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        }

private String formatTime(long position)  {
        // 毫秒转秒，向上取整
        long totalSeconds = (position + 999) / 1000;

        long seconds = totalSeconds % 60;
    long minutes = totalSeconds / 60;

        return String.format(Locale.US, "%02d:%02d", minutes, seconds);
        }

@SuppressLint("SetTextI18n")
private void updateTime(long currentPosition,long duration) {
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

        @Override
        public void onWidgetActive() {
            updateTime(mPlayerCore.getMPlayerContext().getPlayerControlHandler().getCurrentPosition(), mPlayerCore.getMPlayerContext().getPlayerControlHandler().getDuration());

        mPlayerCore.getMPlayerContext().getPlayerControlHandler()
        .addPlayerProgressChangeListener(mPlayerProgressListener);
        }

        @Override
        public void onWidgetInactive() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler()
        .removePlayerProgressChangeListener(mPlayerProgressListener);
        }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }
        }