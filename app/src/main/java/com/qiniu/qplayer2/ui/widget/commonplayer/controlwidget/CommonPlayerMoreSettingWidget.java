package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2ext.commonplayer.layer.function.PlayerFunctionContainer;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.setting.SettingFunctionWidget;
import com.qiniu.qplayer2ext.common.measure.DpUtils;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetLayoutParams;

public class CommonPlayerMoreSettingWidget extends AppCompatImageView implements View.OnClickListener,
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {

    private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
    private PlayerFunctionContainer.FunctionWidgetToken<LongLogicProvider, LongPlayableParams, LongVideoParams> mSettingToken;

    public CommonPlayerMoreSettingWidget(Context context) {
        super(context);
        init(context, null);
    }

    public CommonPlayerMoreSettingWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CommonPlayerMoreSettingWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        Drawable drawableCompat = ContextCompat.getDrawable(context, R.drawable.qmedia_ic_player_setting_vector);
        if (drawableCompat != null) {
            setImageDrawable(drawableCompat);
        }
    }

    @Override
    public void onClick(View v) {
        FunctionWidgetLayoutParams layoutParams = new FunctionWidgetLayoutParams(DpUtils.INSTANCE.dpToPx(360), ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setLayoutType(FunctionWidgetLayoutParams.LayoutAlignType.RIGHT);
        mSettingToken = mPlayerCore.getPlayerFunctionWidgetContainer().showWidget(SettingFunctionWidget.class, layoutParams);
    }

    @Override
    public void onWidgetActive() {
        setOnClickListener(this);

    }

    @Override
    public void onWidgetInactive() {
        setOnClickListener(null);

    }

    @Override public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
    }
}