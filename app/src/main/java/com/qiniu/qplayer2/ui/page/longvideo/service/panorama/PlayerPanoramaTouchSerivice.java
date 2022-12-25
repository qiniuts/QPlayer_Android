package com.qiniu.qplayer2.ui.page.longvideo.service.panorama;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qiniu.qmedia.component.player.QIPlayerStateChangeListener;
import com.qiniu.qmedia.component.player.QPlayerState;
import com.qiniu.qmedia.component.player.QVideoRenderType;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.controller.ICommonPlayerVideoSwitcher;
import com.qiniu.qplayer2ext.commonplayer.layer.gesture.OnTouchListener;
import com.qiniu.qplayer2ext.commonplayer.service.IPlayerService;
import com.qiniu.qplayer2ext.commonplayer.service.PlayerServiceManager;

public class PlayerPanoramaTouchSerivice implements
        IPlayerService<LongLogicProvider, LongPlayableParams, LongVideoParams>,
        QIPlayerStateChangeListener, OnTouchListener,
        ICommonPlayerVideoSwitcher.ICommonVideoPlayEventListener<LongPlayableParams, LongVideoParams> {

        private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
        private boolean PanoramaTouchEnable = false;

        private Pair<Float, Float> mPreTouchPoint = null;
        private float mCurrentRotationX = 0F;
        private float mCurrentRotationY = 0F;
        public static final String TAG = "PanoramaTouchSerivice";
        @Override
        public void onStart() {
                mPlayerCore.getMCommonPlayerVideoSwitcher().addVideoPlayEventListener(this);
        }

        @Override
        public void onStop() {
                mPlayerCore.getMCommonPlayerVideoSwitcher().removeVideoPlayEventListener(this);
                mPlayerCore.getPlayerGestureLayer().setOnTouchListener(null);
        }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
                mPlayerCore = playerCore;
        }

        @Override
        public void onStateChanged(QPlayerState state) {

        }

        @Override
        public void onPlayableParamsStart(LongPlayableParams playableParams,LongVideoParams videoParams
        ) {
                int videoRenderType = playableParams.getMediaModel().getStreamElements()[0].getVideoRenderType();

                if (videoRenderType == QVideoRenderType.PANORAMA_EQUIRECT_ANGULAR.getValue()
//            || videoRenderType == QVideoRenderType.PANORAMA_ANGULAR_CUBEMAP.value
                ) {
                        PanoramaTouchEnable = true;
                        mPlayerCore.getPlayerGestureLayer().setOnTouchListener(this);
                } else {
                        PanoramaTouchEnable = false;
                        mPlayerCore.getPlayerGestureLayer().setOnTouchListener(null);

                }
        }

        @Override public void onTouch(MotionEvent event) {
                if(event==null){
                        return;
                }
                Integer getstureLayerHeight = mPlayerCore.getPlayerGestureLayer().getGestureHeight();
                Integer getstureLayerWidth = mPlayerCore.getPlayerGestureLayer().getGestureWidth();

                if (getstureLayerHeight == null || getstureLayerWidth == null) return;

                switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                                mPreTouchPoint = new Pair(event.getX(), event.getY());
                                break;
                        case MotionEvent.ACTION_MOVE:
                                Pair<Float, Float> prePoint = null;
                                if(mPreTouchPoint!=null){
                                        prePoint=mPreTouchPoint;
                                }else {
                                        return;
                                }
                                float offsetX = event.getX() - prePoint.first;

                                float offsetY = event.getY() - prePoint.second;

                                if (offsetX != 0F || offsetY != 0F) {
                                        float rx = (offsetX / getstureLayerWidth) * 180;
                                        mCurrentRotationX += rx;
                                        mCurrentRotationX %= 360;
                                        float ry = (offsetY / getstureLayerHeight) * 180;
                                        mCurrentRotationY += ry;
                                        mCurrentRotationY %= 360;
                                        boolean positive = mCurrentRotationY >= 0;
                                        if (Math.abs(mCurrentRotationY) > 180) {
                                                mCurrentRotationY = positive ? 180F : -180F;
                                        }
                                        mPlayerCore.getMPlayerContext().getPlayerRenderHandler().setPanoramaViewRotate(mCurrentRotationY, -mCurrentRotationX);
                                        Log.i(TAG, "change panorama roateX="+mCurrentRotationY+", roateY="+mCurrentRotationX+"");
                                }
                                mPreTouchPoint = new Pair(event.getX(), event.getY());
                                break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                                mPreTouchPoint = null;
                                break;
                }

        }

        @Override
        public void onAllVideoParamsCompleted() {

        }

        @Override
        public void onPlayableParamsCompleted(@NonNull LongPlayableParams longPlayableParams, @NonNull LongVideoParams longVideoParams) {

        }

        @Override
        public void onPlayableParamsWillChange(@Nullable LongPlayableParams longPlayableParams, @Nullable LongVideoParams longVideoParams, @NonNull LongPlayableParams t31, @NonNull LongVideoParams t41, @Nullable Bundle bundle) {

        }

        @Override
        public void onVideoParamsCompleted(@NonNull LongVideoParams longVideoParams) {

        }

        @Override
        public void onVideoParamsSetChanged() {

        }

        @Override
        public void onVideoParamsStart(@NonNull LongVideoParams longVideoParams) {

        }

        @Override
        public void onVideoParamsWillChange(@Nullable LongVideoParams longVideoParams, @NonNull LongVideoParams t41, @Nullable Bundle bundle) {

        }

        @Override
        public void onPlayerReset() {

        }

        @NonNull
        @Override
        public PlayerServiceManager.ServiceConfig serviceConfig() {
                return PlayerServiceManager.ServiceConfig.obtain(false);
        }
}