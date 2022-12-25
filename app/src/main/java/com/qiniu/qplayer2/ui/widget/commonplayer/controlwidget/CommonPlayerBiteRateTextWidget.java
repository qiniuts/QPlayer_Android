package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.qiniu.qmedia.component.player.QIPlayerBiteRateListener;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;

public class CommonPlayerBiteRateTextWidget extends AppCompatTextView implements
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

    private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
    private QIPlayerBiteRateListener mPlayerBiteRateListener=new QIPlayerBiteRateListener() {
        @Override
        public void onBiteRateChanged(long biteRate) {
            setText((biteRate / 1024)+"kbps");
        }
    };

    public CommonPlayerBiteRateTextWidget(Context context)  {
        super(context);
    }

    public CommonPlayerBiteRateTextWidget(Context context,AttributeSet attrs)  {
        super(context, attrs);
    }

    public CommonPlayerBiteRateTextWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }

    @Override
    public void onWidgetActive() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler()
                .addPlayerBiteRateChangeListener(mPlayerBiteRateListener);
    }

    @Override
    public void onWidgetInactive() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler()
                .removePlayerBiteRateChangeListener(mPlayerBiteRateListener);
    }

    @Override
    public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
    }
}