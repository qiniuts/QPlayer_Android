package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.videolist;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.controller.ICommonPlayerVideoSwitcher;
import com.qiniu.qplayer2ext.commonplayer.layer.function.BaseFunctionWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.common.measure.DpUtils;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetConfig;

public class VideoListFunctionWidget extends
        BaseFunctionWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {
        public VideoListFunctionWidget(Context context){
                super(context);
        }

        private TextView mTitleTV;
        private RecyclerView mVideosRV;
        private VideoListAdapter mVideoListAdapter;
        private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
        private ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams> mVideoPlayEventListener=new ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams>() {
                @Override
                public void onVideoParamsStart(@NonNull LongVideoParams longVideoParams) {
                        mVideoListAdapter.setSelectedVideoId(mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams()==null?-1L:mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams().getId());
                }

                @Override
                public void onPlayableParamsStart(@NonNull LongPlayableParams longPlayableParams, @NonNull LongVideoParams longVideoParams) {

                }

                @Override
                public void onPlayableParamsWillChange(@Nullable LongPlayableParams longPlayableParams, @Nullable LongVideoParams longVideoParams, @NonNull LongPlayableParams t31, @NonNull LongVideoParams t41, @Nullable Bundle bundle) {

                }

                @Override
                public void onVideoParamsWillChange(@Nullable LongVideoParams longVideoParams, @NonNull LongVideoParams t41, @Nullable Bundle bundle) {

                }

                @Override
                public void onPlayableParamsCompleted(@NonNull LongPlayableParams longPlayableParams, @NonNull LongVideoParams longVideoParams) {

                }

                @Override
                public void onVideoParamsCompleted(@NonNull LongVideoParams longVideoParams) {

                }

                @Override
                public void onAllVideoParamsCompleted() {

                }

                @Override
                public void onVideoParamsSetChanged() {
                        mVideoListAdapter.setItems(mPlayerCore.getPlayerDataSource().getVideoParamsList());
                        mVideoListAdapter.setSelectedVideoId(mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams()==null?-1L:mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams().getId());
                }
        };
        //private ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams> mVideoPlayEventListener=new ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams>() {
//        @Override
//        public void onVideoParamsStart(@NonNull LongVideoParams longVideoParams) {
//                mVideoListAdapter.setSelectedVideoId(mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams()==null?-1L:mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams().getId());
//        }
//
//        @Override
//        public void onPlayableParamsStart(@NonNull LongPlayableParams longPlayableParams, @NonNull LongVideoParams longVideoParams) {
//
//        }
//
//        @Override
//        public void onPlayableParamsWillChange(@Nullable LongPlayableParams longPlayableParams, @Nullable LongVideoParams longVideoParams, @NonNull LongPlayableParams t31, @NonNull LongVideoParams t41, @Nullable Bundle bundle) {
//
//        }
//
//        @Override
//        public void onVideoParamsWillChange(@Nullable LongVideoParams longVideoParams, @NonNull LongVideoParams t41, @Nullable Bundle bundle) {
//
//        }
//
//        @Override
//        public void onPlayableParamsCompleted(@NonNull LongPlayableParams longPlayableParams, @NonNull LongVideoParams longVideoParams) {
//
//        }
//
//        @Override
//        public void onVideoParamsCompleted(@NonNull LongVideoParams longVideoParams) {
//
//        }
//
//        @Override
//        public void onAllVideoParamsCompleted() {
//
//        }
//
//        @Override
//        public void onVideoParamsSetChanged() {
//                mVideoListAdapter.setItems(mPlayerCore.getPlayerDataSource().getVideoParamsList());
//                mVideoListAdapter.setSelectedVideoId(mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams()==null?-1L:mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams().getId());
//
//        }
//};
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

//        override val tag: String

        @Override
        public String getTag(){
                return "VideoListFunctionWidget";
        }

        @Override
        public void onWidgetShow() {
                mVideoListAdapter.setItems(mPlayerCore.getPlayerDataSource().getVideoParamsList());
                mVideoListAdapter.setSelectedVideoId(mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams()==null?-1L:mPlayerCore.getMCommonPlayerVideoSwitcher().getSwitchVideoParams().getId());

                mPlayerCore.getMCommonPlayerVideoSwitcher().addVideoPlayEventListener(mVideoPlayEventListener);
        }

        @Override public void onWidgetDismiss() {
                mPlayerCore.getMCommonPlayerVideoSwitcher().removeVideoPlayEventListener(mVideoPlayEventListener);
        }

        @Override
        public View createContentView(Context context) {
                View view = LayoutInflater.from(getMContext()).inflate(R.layout.function_video_list, null);
                mTitleTV = view.findViewById(R.id.title_TV);
                mVideosRV = view.findViewById(R.id.videos_RV);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext(), RecyclerView.VERTICAL, false);
                int margin = DpUtils.INSTANCE.dpToPx(16);
                mVideosRV.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                                super.getItemOffsets(outRect, view, parent, state);
                                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
                                layoutParams.setMargins(margin, margin / 2, margin, margin / 2);
                        }
                });
                mVideosRV.setLayoutManager(layoutManager);
                mTitleTV.setText(context.getString(R.string.common_player_video_list_title));


                mVideoListAdapter = new VideoListAdapter(getMContext(), mPlayerCore.getPlayerDataSource().getVideoParamsList());
                mVideosRV.setAdapter(mVideoListAdapter);
                mVideoListAdapter.setItemClickListener(new VideoListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(long id) {
                                Log.e("QIPlayerQualityList=","切换1");
                                mPlayerCore.getMCommonPlayerVideoSwitcher().switchVideo(id,null);
                        }
                });

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