package com.qiniu.qplayer2.ui.page.simplelongvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qiniu.qmedia.component.player.*;
import com.qiniu.qmedia.ui.QTexturePlayerView;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.logic.PlayerSettingVM;
import com.qiniu.qplayer2.ui.page.simplelongvideo.IVideoHolderClickListener;
import com.qiniu.qplayer2.ui.page.simplelongvideo.VideoListAdapter;
import com.qiniu.qplayer2.ui.widget.*;

import java.util.ArrayList;

public class SimpleLongVideoActivity extends AppCompatActivity implements IVideoHolderClickListener {

        private static final String TAG = "LongVideoActivity";

        private QTexturePlayerView mQTexturePlayerView;
        private PlayerPausePlayWidget mPausePlayWidget;
        private PlayerProgressTextWidget mProgressTextWidget;
        private PlayerSeekWidget mSeekWidget;
        private PlayerFPSTextWidget mFPSWidget;
        private PlayerDownloadTextWidget mDownloadTextWidget;
        private PlayerBiteRateTextWidget mBiteRateTextWidget;

        private RecyclerView mVideoListRecycleView;
        private PlayerBufferingWidget mBufferingWidget;

        private ArrayList<Pair<String, QMediaModel>> mVideoList = new ArrayList<Pair<String, QMediaModel>>();

        private PlayerSettingVM mSettingVM;

        private long testTime = 0L;

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getSupportActionBar().hide();//隐藏标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.activity_simple_long_video);
                mQTexturePlayerView = findViewById(R.id.player_view);
                mPausePlayWidget = findViewById(R.id.pause_play_Btn);
                mProgressTextWidget = findViewById(R.id.progress_TV);
                mBufferingWidget = findViewById(R.id.buffering_TV);
                mSeekWidget = findViewById(R.id.seek_bar);
                mFPSWidget = findViewById(R.id.fps_TV);
                mBiteRateTextWidget = findViewById(R.id.biterate_TV);
                mDownloadTextWidget = findViewById(R.id.download_speed_TV);

                VideoListAdapter videoListAdapter = new VideoListAdapter();
                videoListAdapter.setVideoHolderClickListener(this);
                initMediaModel();
                videoListAdapter.setData(mVideoList);

                mVideoListRecycleView = findViewById(R.id.video_list_RECYCLER_VIEW);
                mVideoListRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


                mVideoListRecycleView.setAdapter(videoListAdapter);
                mPausePlayWidget.setPlayerControlHandler(mQTexturePlayerView.getPlayerControlHandler());
                mProgressTextWidget.setPlayerControlHandler(mQTexturePlayerView.getPlayerControlHandler());
                mSeekWidget.setPlayerControlHandler(mQTexturePlayerView.getPlayerControlHandler());
                mFPSWidget.setPlayerControlHandler(mQTexturePlayerView.getPlayerControlHandler());
                mBiteRateTextWidget.setPlayerControlHandler(mQTexturePlayerView.getPlayerControlHandler());
                mDownloadTextWidget.setPlayerControlHandler(mQTexturePlayerView.getPlayerControlHandler());
                mBufferingWidget.setPlayerControlHandler(mQTexturePlayerView.getPlayerControlHandler());
                mSettingVM = new PlayerSettingVM(getLifecycle());

                mQTexturePlayerView.getPlayerControlHandler().init(this);
                mQTexturePlayerView.getPlayerControlHandler().setDecodeType(
                        mSettingVM.decoderTypeLiveData.getValue() != null ? mSettingVM.decoderTypeLiveData.getValue() : QPlayerSetting.QPlayerDecoder.QPLAYER_DECODER_SETTING_AUTO);

                mQTexturePlayerView.getPlayerControlHandler().setSeekMode(
                        mSettingVM.seekTypeLiveData.getValue() != null ? mSettingVM.seekTypeLiveData.getValue() : QPlayerSetting.QPlayerSeek.QPLAYER_SEEK_SETTING_NORMAL);

                mQTexturePlayerView.getPlayerControlHandler().setStartAction(
                        mSettingVM.startTypeLiveData.getValue() != null ? mSettingVM.startTypeLiveData.getValue() : QPlayerSetting.QPlayerStart.QPLAYER_START_SETTING_PLAYING);

                mQTexturePlayerView.getPlayerControlHandler().setSpeed(
                        mSettingVM.speedLiveData.getValue() != null ? mSettingVM.speedLiveData.getValue() : 1.0f
                );

                mQTexturePlayerView.getPlayerRenderHandler().setRenderRatio(
                        mSettingVM.renderRatioLiveData.getValue() != null ? mSettingVM.renderRatioLiveData.getValue() : QPlayerSetting.QPlayerRenderRatio.QPLAYER_RATIO_SETTING_AUTO
                );
                mQTexturePlayerView.getPlayerControlHandler().playMediaModel(mVideoList.get(0).second, mSettingVM.startPositionLiveData.getValue() != null ? mSettingVM.startPositionLiveData.getValue() : 0);
//                mQTexturePlayerView.playerControlHandler.setSpeed(2.0f);
        }

        private void initMediaModel() {
                QMediaModelBuilder builder = new QMediaModelBuilder();
                String url = "";

                //音视频分开2个流的视频要用精准seek

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/movies/qiniu.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(false)));

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/shortvideo/nike.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(false)));


                builder = new QMediaModelBuilder();
                url = "http://pili-hdl.qnsdk.com/sdk-live/timestamp-6M.flv";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(true)));

                builder = new QMediaModelBuilder();
                url = "http://pili-hls.qnsdk.com/sdk-live/timestamp-6M.m3u8";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(true)));

                builder = new QMediaModelBuilder();
                url = "rtmp://pili-rtmp.qnsdk.com/sdk-live/timestamp";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(true)));


                builder = new QMediaModelBuilder();
                url = "https://ms-shortvideo-dn.eebbk.net/bbk-n002/stream/2021/07/30/1911/68/357c1b03fa454a9c6b3198c9c6a3b49a.mp4?sign=13eb41043c62c8f462dcb6226fd1a5a6&t=613852bf";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "http://video.eebbk.net", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair("http403", builder.build(false)));
                builder = new QMediaModelBuilder();
                url = "https://ms-shortvideo-dn.eebbk.net/bbk-n002/stream/2021/12/27/1143/14571/e3d3dc6b29afcccb6ff01c8f23e4018b.mp4?sign=6a1d95afe884f9654a6598b076affd00&t=61dd5fa6";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "http://video.eebbk.net", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair("http referer", builder.build(false)));

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/song.mp3";
                builder.addElement("", QURLType.QAUDIO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair("纯音频", builder.build(false)));

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/only-video-1080p-60fps.m4s";
                builder.addElement("", QURLType.QVIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair("纯视频", builder.build(false)));


                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/bbk-bt709.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(false)));

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/bbk-H265-50fps.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(false)));

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/Sync-Footage-V1-H264.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair("音画同步-H264-60FPS", builder.build(false)));

                builder = new QMediaModelBuilder();
                url = "https://img.qunliao.info:443/4oEGX68t_9505974551.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(false)));

                builder = new QMediaModelBuilder();
                url = "https://media.w3.org/2010/05/sintel/trailer.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair(url, builder.build(false)));

                builder = new QMediaModelBuilder();
                url = "http://demo-videos.qnsdk.com/Sony%20Swordsmith%20HDR%20UHD%204K%20Demo.mp4";
                builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, url, true, "", "", QVideoRenderType.NONE);
                mVideoList.add(new Pair("4K-HDR-yuv420p10le(tv, bt2020nc/bt2020/smpte2084)", builder.build(false)));
        }


        @Override
        public void onDestroy() {
                mQTexturePlayerView.getPlayerControlHandler().release();
                super.onDestroy();

        }

        @Override
        public void onClick(QMediaModel mediaModel) {
                mQTexturePlayerView.getPlayerControlHandler().playMediaModel(mediaModel, mSettingVM.startPositionLiveData.getValue() != null ? mSettingVM.startPositionLiveData.getValue() : 0);
//            mBufferingTV.visibility = View.VISIBLE
                mQTexturePlayerView.getPlayerControlHandler().addPlayerQualityListener(playerQualityListener);

        }

        private QIPlayerQualityListener playerQualityListener = new QIPlayerQualityListener() {
                /**
                 * 开始清晰度切换
                 * @param userType 开始切换清晰度的stream element的userType
                 * @param urlType 开始切换清晰度的stream element的 urlType @see[QURLType]
                 * @param newQuality 要切到哪路清晰度
                 * @param oldQuality 切换前的清晰度
                 */
                @Override
                public void onQualitySwitchStart(String userType, QURLType urlType, int newQuality, int oldQuality) {

                        Log.i("playerQualityListener", userType + "=onQualitySwitchStart=" + newQuality);
                        testTime = System.currentTimeMillis();

                }

                /**
                 * 清晰度切换完成
                 * @param userType 开始切换清晰度的stream element的userType
                 * @param urlType 开始切换清晰度的stream element的urlType @see[QURLType]
                 * @param newQuality 要切到哪路清晰度
                 * @param oldQuality 切换前的清晰度
                 */
                @Override
                public void onQualitySwitchComplete(String userType, QURLType urlType, int newQuality, int oldQuality) {

                        Log.i("playerQualityListener", userType + "=onQualitySwitchComplete=" + newQuality + "," + (System.currentTimeMillis() - testTime) + "ms");

                }

                /**
                 * 清晰度切换取消
                 * @param userType 开始切换清晰度的stream element的userType
                 * @param urlType 开始切换清晰度的stream element的 urlType @see[QURLType]
                 * @param newQuality 要切到哪路清晰度
                 * @param oldQuality 切换前的清晰度
                 */
                @Override
                public void onQualitySwitchCanceled(String userType, QURLType urlType, int newQuality, int oldQuality) {
                        Log.i("playerQualityListener", userType + "=onQualitySwitchCanceled=" + newQuality + "," + (System.currentTimeMillis() - testTime) + "ms");
                }

                /**
                 * 清晰度切换失败
                 * @param userType 开始切换清晰度的url流的userType
                 * @param urlType 开始切换清晰度的url流的 urlType @see[QURLType]
                 * @param newQuality 要切到哪路清晰度
                 * @param oldQuality 切换前的清晰度
                 */
                @Override
                public void onQualitySwitchFailed(String userType, QURLType urlType, int newQuality, int oldQuality) {
                        Log.i("playerQualityListener", userType+"=onQualitySwitchFailed=$newQuality,"+(System.currentTimeMillis()-testTime)+"ms");
                }

                /**
                 * 目前仅支持同时有一个清晰度切换，如果前一个还未切换完，再次发起切换 会回调这个函数
                 * @param userType 开始切换清晰度的url流的userType
                 * @param urlType 开始切换清晰度的url流的 urlType @see[QURLType]
                 * @param newQuality 要切到哪路清晰度
                 * **/
                @Override
                public void onQualitySwitchRetryLater(String userType, QURLType urlType, int newQuality) {
                }
        };
}