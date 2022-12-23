package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.qiniu.qmedia.component.player.QIPlayerFPSListener;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;

public class CommonPlayerFPSTextWidget extends AppCompatTextView implements
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

    private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
    private QIPlayerFPSListener mPlayerFPSListener=new QIPlayerFPSListener() {
        @Override
        public void onFPSChanged(int fps) {
            setText("FPS:"+fps);
        }
    };

    public CommonPlayerFPSTextWidget(Context context){
        super(context);
    }

    public CommonPlayerFPSTextWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonPlayerFPSTextWidget(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    @Override
    public void onWidgetActive() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler()
                .addPlayerFPSChangeListener(mPlayerFPSListener);
    }

    @Override
    public void onWidgetInactive() {
        mPlayerCore.getMPlayerContext().getPlayerControlHandler()
                .removePlayerFPSChangeListener(mPlayerFPSListener);
    }

    @Override
    public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
    }
}