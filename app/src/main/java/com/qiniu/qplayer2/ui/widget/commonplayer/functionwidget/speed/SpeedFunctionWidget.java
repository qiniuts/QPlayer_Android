package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.speed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.function.BaseFunctionWidget;
import com.qiniu.qplayer2.repository.setting.PlayerSettingRespostory;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetConfig;

public class SpeedFunctionWidget extends
        BaseFunctionWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {
        public SpeedFunctionWidget(Context context){
            super(context);
        }
private RecyclerView mRecyclerView;
private SpeedListAdapter mSpeedAdapter =new SpeedListAdapter();
private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
                @Override
            public String getTag(){
                        return "SpeedFunctionWidget";
                }

//            override val functionWidgetConfig: FunctionWidgetConfig
        @Override
            public FunctionWidgetConfig getFunctionWidgetConfig() {
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
            mRecyclerView.setAdapter(mSpeedAdapter);
                mSpeedAdapter.setItemSelectListener(new SpeedListAdapter.OnItemSelectListener() {
                    @Override
                    public void onItemSelected(float speed) {
                        mPlayerCore.getMPlayerContext().getPlayerControlHandler().setSpeed(speed);
                        mPlayerCore.getPlayerFunctionWidgetContainer().hideWidget(token);
                        PlayerSettingRespostory.getInstance().setPlaySpeed(speed);
                    }
                });
            float speed = mPlayerCore.getMPlayerContext().getPlayerControlHandler().getSpeed();
            mSpeedAdapter.setData(speed);
            mSpeedAdapter.notifyDataSetChanged();
            }

            @Override public void onWidgetDismiss() {
            }

            @Override public View createContentView(Context context) {
            View view = LayoutInflater.from(getMContext()).inflate(R.layout.function_speed_list, null);
            mRecyclerView = view.findViewById(R.id.recycler);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext(), RecyclerView.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
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