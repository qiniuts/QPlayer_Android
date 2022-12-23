package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.error;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.function.BaseFunctionWidget;
import com.qiniu.qplayer2ext.commonplayer.screen.ICommonPlayerScreenChangedListener;
import com.qiniu.qplayer2ext.commonplayer.screen.ScreenType;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetConfig;

public class ErrorFunctionWidget extends
        BaseFunctionWidget<LongLogicProvider, LongPlayableParams, LongVideoParams>
        {
            public ErrorFunctionWidget (Context context){
                super(context);

            }
private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
private Button mButton;
//    private lateinit var mBackIV: ImageView

            private View.OnClickListener mRetryClickLister=view ->  mPlayerCore.getMCommonPlayerVideoSwitcher().replayCurrentVideo(new Bundle());
                private View.OnClickListener mBackClickListener=view ->  mPlayerCore.getCommonPlayerScreenHandler().switchScreenType(ScreenType.HALF_SCREEN);

                private ICommonPlayerScreenChangedListener mCommonPlayerScreenChangedListener=screenType -> {
                        //            if (screenType == ScreenType.HALF_SCREEN) {
//                mBackIV.visibility = View.INVISIBLE
//            } else {
//                mBackIV.visibility = View.VISIBLE
//            }
                };

                @Override
                public String getTag(){
                        return "ErrorFunctionWidget";
                }

                @Override
                public FunctionWidgetConfig getFunctionWidgetConfig(){
                        FunctionWidgetConfig.Builder builder = new FunctionWidgetConfig.Builder();
                        builder.dismissWhenActivityStop(true);
                        builder.dismissWhenScreenTypeChange(true);
                        builder.dismissWhenVideoChange(true);
                        builder.dismissWhenVideoCompleted(true);
                        builder.persistent(true);
                        builder.changeOrientationDisableWhenShow(true);
                        return builder.build();
                }

        @Override
        public void onWidgetShow() {
        mButton.setOnClickListener(mRetryClickLister);
//        mBackIV.setOnClickListener(mBackClickListener)
        mPlayerCore.getCommonPlayerScreenHandler().addScreenChangedListener(mCommonPlayerScreenChangedListener);
        }

        @Override
        public void onWidgetDismiss() {
        mButton.setOnClickListener(null);
//        mBackIV.setOnClickListener(null)
        mPlayerCore.getCommonPlayerScreenHandler().removeScreenChangedListener(mCommonPlayerScreenChangedListener);


        }

        @Override
        public View createContentView(Context context) {
        View view = LayoutInflater.from(getMContext()).inflate(R.layout.function_error, null);
        mButton = view.findViewById(R.id.button);
//        mBackIV = view.findViewById<ImageView>(R.id.back_IV)
        return view;
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