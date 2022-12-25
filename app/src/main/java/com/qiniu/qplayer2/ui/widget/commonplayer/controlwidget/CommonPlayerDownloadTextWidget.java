package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.qiniu.qmedia.component.player.QIPlayerDownloadListener;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;

public class CommonPlayerDownloadTextWidget extends AppCompatTextView implements
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;

private QIPlayerDownloadListener mPlayerDownloadListener=new QIPlayerDownloadListener() {
    @Override
    public void onDownloadChanged(long speed, long bufferProgress) {
            setText((speed / 1024 / 8)+"KB/s");
    }
};

                public CommonPlayerDownloadTextWidget(Context context){
                        super(context);
                }

        public CommonPlayerDownloadTextWidget(Context context,AttributeSet attrs) {
                super(context, attrs);
                }

        public CommonPlayerDownloadTextWidget(Context context,AttributeSet attrs,int defStyleAttr) {
                super(context,attrs,defStyleAttr);
        }

                @Override
                public void onWidgetActive() {
                mPlayerCore.getMPlayerContext().getPlayerControlHandler()
                .addPlayerDownloadChangeListener(mPlayerDownloadListener);
                }

        @Override
        public void onWidgetInactive() {
                mPlayerCore.getMPlayerContext().getPlayerControlHandler()
                .removePlayerDownloadChangeListener(mPlayerDownloadListener);

                }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }
        }