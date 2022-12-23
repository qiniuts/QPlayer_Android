package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.buffering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.function.BaseFunctionWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetConfig;

public class BufferingFunctionWidget extends BaseFunctionWidget<LongLogicProvider, LongPlayableParams, LongVideoParams>
{
    public BufferingFunctionWidget(Context context){
        super(context);

    }
    private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
    @Override
    public String getTag(){
        return "BufferingFunctionWidget";
    }
    @Override
    public FunctionWidgetConfig getFunctionWidgetConfig(){
        FunctionWidgetConfig.Builder builder = new FunctionWidgetConfig.Builder();
        builder.persistent(true);
        builder.dismissWhenVideoCompleted(true);
        builder.dismissWhenVideoChange(false);
        builder.dismissWhenScreenTypeChange(false);
        builder.dismissWhenActivityStop(false);
        builder.launchType(FunctionWidgetConfig.LaunchType.Normal);
        return builder.build();
    }

    @Override
    public void onWidgetShow() {
    }

    @Override
    public void onWidgetDismiss() {
    }

    @Override
    public View createContentView(Context context) {
        return LayoutInflater.from(getMContext()).inflate(R.layout.function_buffering, null);
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override
    public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
    }
}