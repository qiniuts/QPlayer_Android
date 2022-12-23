package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.videoquality;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qiniu.qmedia.component.player.*;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.controller.ICommonPlayerVideoSwitcher;
import com.qiniu.qplayer2ext.commonplayer.layer.function.BaseFunctionWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.common.measure.DpUtils;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetConfig;

import java.util.ArrayList;
import java.util.List;

public class VideoQualityFunctionWidget extends
        BaseFunctionWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {
    public VideoQualityFunctionWidget (Context context){
        super(context);
    }

private RecyclerView mQualitysRV;
private VideoQualityListAdapter mQualityListAdapter;
private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
private QIPlayerQualityListener mVideoQualityListener=new QIPlayerQualityListener() {
    @Override
    public void onQualitySwitchStart(String userType, QURLType urlType, int newQuality, int oldQuality) {
        updateQualitys(mPlayerCore.getMCommonPlayerVideoSwitcher().getCurrentPlayableParams());
    }

    @Override
    public void onQualitySwitchComplete(String userType, QURLType urlType, int newQuality, int oldQuality) {
        updateQualitys(mPlayerCore.getMCommonPlayerVideoSwitcher().getCurrentPlayableParams());
    }

    @Override
    public void onQualitySwitchCanceled(String userType, QURLType urlType, int newQuality, int oldQuality
    ) {
        updateQualitys(mPlayerCore.getMCommonPlayerVideoSwitcher().getCurrentPlayableParams());
    }

    @Override
    public void onQualitySwitchFailed(String userType, QURLType urlType, int newQuality, int oldQuality) {
        updateQualitys(mPlayerCore.getMCommonPlayerVideoSwitcher().getCurrentPlayableParams());
    }

    @Override
    public void onQualitySwitchRetryLater(String userType, QURLType urlType, int newQuality) {
        updateQualitys(mPlayerCore.getMCommonPlayerVideoSwitcher().getCurrentPlayableParams());
    }
};
private ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams> mVideoPlayEventListener=new ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams>() {
    @Override
    public void onVideoParamsStart(@NonNull LongVideoParams longVideoParams) {

    }

    @Override
    public void onPlayableParamsStart(@NonNull LongPlayableParams playableParams, @NonNull LongVideoParams videoParams) {
//        super.onPlayableParamsStart(playableParams, videoParams)
        updateQualitys(playableParams);
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

    }
};

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
        public String getTag(){
        return "QualityFunctionWidget";
            }
    public List<QQuality> getVideoQualityList(QMediaModel qMediaModel){
        List<QQuality> qualitys = new ArrayList<QQuality>();
        for(QStreamElement it:qMediaModel.getStreamElements()){
            if (it.getUrlType() == QURLType.QVIDEO.getValue() || it.getUrlType() == QURLType.QAUDIO_AND_VIDEO.getValue()){
                qualitys.add(new QQuality(it.getUserType(), QURLType.Companion.valueOf(it.getUrlType()), it.getQuality()));
            }

        }
        return qualitys;
    }

    public List<QQuality> getAudioQualityList(QMediaModel qMediaModel) {
        List<QQuality> qualitys = new ArrayList<QQuality>();
        for(QStreamElement it:qMediaModel.getStreamElements()){
            if (it.getUrlType() == QURLType.QAUDIO.getValue() || it.getUrlType() == QURLType.QAUDIO_AND_VIDEO.getValue()){
                qualitys.add(new QQuality(it.getUserType(), QURLType.Companion.valueOf(it.getUrlType()), it.getQuality()));
            }

        }
        return qualitys;
    }
private void  updateQualitys(LongPlayableParams playableParams) {
        mQualityListAdapter.setQualities(getVideoQualityList(playableParams.getMediaModel()));
        int selectedQualityId = mPlayerCore.getMPlayerContext().getPlayerControlHandler().getCurrentQuality("", QURLType.QVIDEO);
        int switchingQualityId = QPlayerControlHandler.INVALID_QUALITY_ID;
    QURLType urlType = QURLType.QVIDEO;
        if (selectedQualityId == QPlayerControlHandler.INVALID_QUALITY_ID) {
        selectedQualityId = mPlayerCore.getMPlayerContext().getPlayerControlHandler().getCurrentQuality("", QURLType.QAUDIO_AND_VIDEO);
        switchingQualityId = mPlayerCore.getMPlayerContext().getPlayerControlHandler().getSwitchingQuality("", QURLType.QAUDIO_AND_VIDEO);
        urlType = QURLType.QAUDIO_AND_VIDEO;
        } else {
        mQualityListAdapter.setSelectedQuality(new QQuality("", QURLType.QVIDEO, selectedQualityId));
        switchingQualityId = mPlayerCore.getMPlayerContext().getPlayerControlHandler().getSwitchingQuality("", QURLType.QVIDEO);

        }

        if (switchingQualityId != QPlayerControlHandler.INVALID_QUALITY_ID) {
        mQualityListAdapter.setSelectedQuality(new QQuality("", urlType, switchingQualityId));
        } else if (selectedQualityId != QPlayerControlHandler.INVALID_QUALITY_ID) {
        mQualityListAdapter.setSelectedQuality(new QQuality("", urlType, selectedQualityId));
        }
        }
        @Override
        public void onWidgetShow() {
            updateQualitys( mPlayerCore.getMCommonPlayerVideoSwitcher().getCurrentPlayableParams());
        mPlayerCore.getMCommonPlayerVideoSwitcher().addVideoPlayEventListener(mVideoPlayEventListener);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerQualityListener(mVideoQualityListener);
        }

        @Override
        public void onWidgetDismiss() {
        mPlayerCore.getMCommonPlayerVideoSwitcher().removeVideoPlayEventListener(mVideoPlayEventListener);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerQualityListener(mVideoQualityListener);

        }

        @Override
        public View createContentView(Context context) {
        View view = LayoutInflater.from(getMContext()).inflate(R.layout.function_video_quality_list, null);
        mQualitysRV = view.findViewById(R.id.videos_RV);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext(), RecyclerView.VERTICAL, false);
        int margin = DpUtils.INSTANCE.dpToPx(16);
            mQualitysRV.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
                    layoutParams.setMargins(margin, margin / 2, margin, margin / 2);
                }
            });
        mQualitysRV.setLayoutManager(layoutManager);


        mQualityListAdapter =new VideoQualityListAdapter(getMContext(),new ArrayList<QQuality>());
        mQualitysRV.setAdapter(mQualityListAdapter);
            mQualityListAdapter.setItemClickListener(new VideoQualityListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(QQuality quality) {
                    boolean isLive=mPlayerCore.getMCommonPlayerVideoSwitcher().getCurrentPlayableParams().getMediaModel().isLive();
                    mPlayerCore.getMPlayerContext().getPlayerControlHandler().switchQuality(
                            quality.getUserType(),
                            quality.getUrlType(),
                            quality.getQuality(), isLive);
                    mPlayerCore.getPlayerFunctionWidgetContainer().hideWidget(token);
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