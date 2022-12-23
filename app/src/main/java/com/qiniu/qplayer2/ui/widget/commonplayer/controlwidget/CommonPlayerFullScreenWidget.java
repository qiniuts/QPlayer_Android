package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2ext.commonplayer.screen.ScreenType;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;

public class CommonPlayerFullScreenWidget extends AppCompatImageView implements View.OnClickListener,
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

    private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;


    public CommonPlayerFullScreenWidget(Context context) {
        super(context);
        init(context, null);
    }

    public CommonPlayerFullScreenWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CommonPlayerFullScreenWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context,AttributeSet attrs) {
        setContentDescription("bbplayer_halfscreen_expand");
    }

    @Override
    public void onClick(View v) {
        mPlayerCore.getCommonPlayerScreenHandler().switchScreenType(ScreenType.FULL_SCREEN);
    }

    @Override
    public void onWidgetActive() {
        setOnClickListener(this);
    }

    @Override
    public void onWidgetInactive() {
        setOnClickListener(null);
    }

    @Override
    public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
    }
}