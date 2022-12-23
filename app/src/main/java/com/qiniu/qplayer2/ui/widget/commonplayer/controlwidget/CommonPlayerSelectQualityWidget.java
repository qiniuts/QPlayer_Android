package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2ext.commonplayer.layer.function.PlayerFunctionContainer;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.videoquality.VideoQualityFunctionWidget;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetLayoutParams;

public class CommonPlayerSelectQualityWidget extends AppCompatTextView implements
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams>, View.OnClickListener {

    private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
    private PlayerFunctionContainer.FunctionWidgetToken<LongLogicProvider, LongPlayableParams, LongVideoParams> mQualityToken;


    public CommonPlayerSelectQualityWidget(Context context) {
        super(context);
        init(context, null);

    }

    public CommonPlayerSelectQualityWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public CommonPlayerSelectQualityWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        setText("清晰度");
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

    @Override
    public void onClick(View v) {
        FunctionWidgetLayoutParams layoutParams = new FunctionWidgetLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setLayoutType(FunctionWidgetLayoutParams.LayoutAlignType.RIGHT);
        mQualityToken = mPlayerCore.getPlayerFunctionWidgetContainer().showWidget(VideoQualityFunctionWidget.class, layoutParams);
    }
}