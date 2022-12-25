package com.qiniu.qplayer2.ui.page.shortvideo;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qiniu.qmedia.component.player.QIPlayerRenderListener;
import com.qiniu.qmedia.component.player.QMediaItemContext;
import com.qiniu.qmedia.ui.QSurfacePlayerView;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.ui.widget.*;

import java.net.PortUnreachableException;

class ShortVideoHolder extends RecyclerView.ViewHolder {
private View mItemView;
private QSurfacePlayerView mVideoPlayerView;
public ShortVideoHolder(View mItemView, QSurfacePlayerView mVideoPlayerView){
    super(mItemView);
    this.mItemView=mItemView;
    this.mVideoPlayerView=mVideoPlayerView;
    init();
        }
private FrameLayout mVideoContainerFL;
private ImageView mCoverIV;

private PlayerFPSTextWidget mFPSWidget;
private PlayerDownloadTextWidget mDownloadTextWidget;
private PlayerBiteRateTextWidget mBiteRateTextWidget;
private PlayerSeekWidget mSeekWidget;
private PlayerProgressTextWidget mProgressTextWidget;
private PlayerPlayWidget mPlayWidget;
private PlayerFullScreenPlayClickWidget mFullScreenPlayClickWidget;
private PlayerBufferingWidget mBufferingWidget;


    private void init(){
        mVideoContainerFL = mItemView.findViewById(R.id.video_container_FL);
        mCoverIV = mItemView.findViewById(R.id.cover_IV);

        mFPSWidget = mItemView.findViewById(R.id.fps_TV);
        mDownloadTextWidget = mItemView.findViewById(R.id.download_speed_TV);
        mBiteRateTextWidget = mItemView.findViewById(R.id.biterate_TV);
        mSeekWidget = mItemView.findViewById(R.id.seek_bar);
        mProgressTextWidget = mItemView.findViewById(R.id.progress_TV);
        mPlayWidget = mItemView.findViewById(R.id.player_play_IV);
        mFullScreenPlayClickWidget = mItemView.findViewById(R.id.player_play_click_FL);
        mBufferingWidget = mItemView.findViewById(R.id.buffering_TV);
    }
private PlayItem mPlayItem = null;
private QIPlayerRenderListener mPlayerRenderListener=new QIPlayerRenderListener() {
        @Override
        public void onFirstFrameRendered(long elapsedTime) {
                mCoverIV.setVisibility(View.GONE);
        }
};

        public void bind(PlayItem playItem,int postion) {
        mPlayItem = playItem;
            Glide.with(mCoverIV.getContext())
                    .load(playItem.getCoverUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mCoverIV);
//            ImageLoader.Companion.create(mCoverIV.getContext()).enqueue(request);
//        mCoverIV.load(playItem.coverUrl){
//        listener(onError = { request, _ ->
//        //设置点击事件，点击重新加载
//        Log.i("AAA", "ERROR ")
//
//        }, onSuccess = { _, _ ->
//        Log.i("AAA", "SUCESS ")
//        })
//        }
        mItemView.setTag(postion);
        }

        public void startVideo(QMediaItemContext mediaItem){
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            lp.gravity = Gravity.CENTER;

            if (mVideoPlayerView.getParent() == null) {
                mVideoContainerFL.addView(mVideoPlayerView, lp);
            }

            mFPSWidget.setPlayerControlHandler(mVideoPlayerView.getPlayerControlHandler());
            mDownloadTextWidget.setPlayerControlHandler(mVideoPlayerView.getPlayerControlHandler());
            mBiteRateTextWidget.setPlayerControlHandler(mVideoPlayerView.getPlayerControlHandler());
            mSeekWidget.setPlayerControlHandler(mVideoPlayerView.getPlayerControlHandler());
            mProgressTextWidget.setPlayerControlHandler(mVideoPlayerView.getPlayerControlHandler());
            mPlayWidget.setPlayerControlHandler(mVideoPlayerView.getPlayerControlHandler());
            mFullScreenPlayClickWidget.setPlayerControlHandler(mVideoPlayerView.getPlayerControlHandler());
            mBufferingWidget.setPlayerControlHandler(mVideoPlayerView.getPlayerControlHandler());

            mVideoPlayerView.getPlayerRenderHandler().addPlayerRenderListener(mPlayerRenderListener);
            if (mediaItem != null) {
                new Thread (()->mVideoPlayerView.getPlayerControlHandler().playMediaItem(mediaItem) ).start();

            } else {
                new Thread (()->mVideoPlayerView.getPlayerControlHandler().playMediaModel(mPlayItem.mediaModel, 0) ).start();

            }
        }

        public void stopVideo() {
        mCoverIV.setVisibility(View.VISIBLE);
        mVideoPlayerView.getPlayerRenderHandler().removePlayerRenderListener(mPlayerRenderListener);
        mVideoPlayerView.getPlayerControlHandler().stop();

        mFPSWidget.setPlayerControlHandler(null);
        mDownloadTextWidget.setPlayerControlHandler(null);
        mBiteRateTextWidget.setPlayerControlHandler(null);
        mSeekWidget.setPlayerControlHandler(null);
        mProgressTextWidget.setPlayerControlHandler(null);
        mPlayWidget.setPlayerControlHandler(null);
        mFullScreenPlayClickWidget.setPlayerControlHandler(null);
        mBufferingWidget.setPlayerControlHandler(null);

        mVideoContainerFL.removeView(mVideoPlayerView);
        }
        }