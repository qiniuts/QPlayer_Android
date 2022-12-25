package com.qiniu.qplayer2.ui.page.shortvideo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.qiniu.qmedia.component.player.QIPlayerRenderListener;
import com.qiniu.qmedia.component.player.QIPlayerStateChangeListener;
import com.qiniu.qmedia.component.player.QLogLevel;
import com.qiniu.qmedia.component.player.QMediaItemContext;
import com.qiniu.qmedia.component.player.QMediaModelBuilder;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qmedia.component.player.QURLType;
import com.qiniu.qmedia.component.player.QVideoRenderType;
import com.qiniu.qmedia.ui.QSurfacePlayerView;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.repository.shortvideo.ModelFactory;
import com.qiniu.qplayer2.repository.shortvideo.VideoItem;

import java.util.ArrayList;

public class ShortVideoActivity extends AppCompatActivity {

    private RecyclerView mShortVideoRV;
    private QSurfacePlayerView mVideoView;
    private ShortVideoListAdapter mShortVideoListAdapter;
    private int mCurrentPosition = -1;
    private ShortVideoHolder mCurrentVideoHolder = null;
    private ArrayList<PlayItem> mPlayItemList =new ArrayList<PlayItem>();
    private MediaItemContextManager mMediaItemContextManager =new MediaItemContextManager();
    private boolean mFirstPlay = true;
    private String TAG = "ShortVideoActivity";
    private String SHORT_VIDEO_PATH_PREFIX =
            "https://api-demo.qnsdk.com/v1/kodo/bucket/demo-videos?prefix=shortvideo";

    private int LOAD_FORWARD_POS = 1;
    private int LOAD_BACKWARD_POS = 2;
    private RecyclerView.OnChildAttachStateChangeListener mOnChildAttachStateChangeListener=new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {
            startPlay();
        }

        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {

        }
    };
    private RecyclerView.OnScrollListener mOnScrollListener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                changeVideo();
            }
        }
    };
    private QIPlayerStateChangeListener mPlayerStateChangeListener=new QIPlayerStateChangeListener() {
        @Override
        public void onStateChanged(@NonNull QPlayerState state) {
            if (state == QPlayerState.COMPLETED) {
                //循环播放
                mVideoView.getPlayerControlHandler().seek(0);
            }
        }
    };

    private QIPlayerRenderListener mPlayerRenderListener=new QIPlayerRenderListener() {
        @Override
        public void onFirstFrameRendered(long elapsedTime) {
            //                updateMediaItemContext()
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setContentView(R.layout.activity_short_video);
        mShortVideoRV = findViewById(R.id.short_video_RV);

        LinearLayoutManager layoutManager =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mShortVideoRV.setLayoutManager(layoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mShortVideoRV);

        mVideoView =new QSurfacePlayerView(this);
        mVideoView.getPlayerControlHandler().addPlayerStateChangeListener(mPlayerStateChangeListener);
        mVideoView.getPlayerRenderHandler().addPlayerRenderListener(mPlayerRenderListener);
        mVideoView.getPlayerControlHandler().init(this);
        fetchVideoList();

    }

    @Override
    public void onDestroy() {
        mMediaItemContextManager.discardAllMediaItemContexts();
        mVideoView.getPlayerControlHandler().release();
        super.onDestroy();
    }

    private void fetchVideoList() {
        ModelFactory.createVideoItemListByURL(SHORT_VIDEO_PATH_PREFIX, new ModelFactory.OnResultListener() {
            @Override
            public void onSuccess(int statusCode, Object data) {
                ArrayList<VideoItem> items = (ArrayList<VideoItem>)data;
                for(VideoItem it:items){
                    QMediaModelBuilder builder =new QMediaModelBuilder();
                    builder.addElement("", QURLType.QAUDIO_AND_VIDEO, 0, it.getVideoPath(), true,"","", QVideoRenderType.NONE);

                    PlayItem playItem =new PlayItem(it.getVideoPath().hashCode(),
                            builder.build(false),
                            it.getCoverPath());

                    mPlayItemList.add(playItem);
                }
                runOnUiThread(()-> initView());
            }

            @Override
            public void onFailure() {
                runOnUiThread(()->{
                    Toast.makeText(
                            ShortVideoActivity.this,
                            "获得短视频列表失败",
                            Toast.LENGTH_LONG
                    ).show();
                });
            }

        });
    }

    private void initView() {
        mShortVideoListAdapter =new ShortVideoListAdapter(mPlayItemList, mVideoView);
        mShortVideoRV.setAdapter(mShortVideoListAdapter);
        mShortVideoRV.addOnScrollListener(mOnScrollListener);
        mShortVideoRV.addOnChildAttachStateChangeListener(mOnChildAttachStateChangeListener);
    }

    private void startPlay() {
        if (mFirstPlay) {
            mFirstPlay = false;
            changeVideo();
        }
    }


    private void changeVideo() {
        LinearLayoutManager layoutManager = (LinearLayoutManager)mShortVideoRV.getLayoutManager();
        int visibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();

        if (visibleItemPosition >= 0 && mCurrentPosition != visibleItemPosition) {
            if(mCurrentVideoHolder!=null){
                mCurrentVideoHolder.stopVideo();
            }
            mCurrentPosition = visibleItemPosition;
            View it= mShortVideoRV.findViewWithTag(mCurrentPosition);
            mCurrentVideoHolder = (ShortVideoHolder)mShortVideoRV.getChildViewHolder(it);
            updateMediaItemContext();
            QMediaItemContext mediaItemContext = mMediaItemContextManager.fetchMediaItemContext(mPlayItemList.get(mCurrentPosition).getId());
            mCurrentVideoHolder.startVideo(mediaItemContext);
        }
    }

    private void updateMediaItemContext() {

        int start = mCurrentPosition - LOAD_FORWARD_POS;
        int end = mCurrentPosition - 1;
        //当前pos的视频 不加载
        for(int i = start; i<=end; i++){
            if(i>=0&&i<mPlayItemList.size()){
                PlayItem it=mPlayItemList.get(i);
                mMediaItemContextManager.load(it.id, it.mediaModel, 0, QLogLevel.LOG_VERBOSE, getExternalFilesDir(null).getPath()==null ?"":getExternalFilesDir(null).getPath() );

            }
        }

        start = mCurrentPosition + 1;
        end = mCurrentPosition + LOAD_BACKWARD_POS;
        for(int i = start; i<=end; i++){
            if(i>=0&&i<mPlayItemList.size()){
                PlayItem it=mPlayItemList.get(i);
                mMediaItemContextManager.load(it.id, it.mediaModel, 0, QLogLevel.LOG_VERBOSE, getExternalFilesDir(null).getPath()==null ?"":getExternalFilesDir(null).getPath() );

            }        }
    }
}