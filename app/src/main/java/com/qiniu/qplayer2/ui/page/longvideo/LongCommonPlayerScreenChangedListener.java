package com.qiniu.qplayer2.ui.page.longvideo;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import androidx.activity.ComponentActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.qiniu.qplayer2.common.system.RomUtils;
import com.qiniu.qplayer2ext.commonplayer.screen.ICommonPlayerScreenChangedListener;
import com.qiniu.qplayer2ext.commonplayer.screen.ScreenType;
import com.qiniu.qplayer2.ui.common.notch.NotchCompat;
import com.qiniu.qplayer2ext.common.measure.DpUtils;

public class LongCommonPlayerScreenChangedListener implements ICommonPlayerScreenChangedListener {
    private ComponentActivity mActivity;
    private ViewGroup mVideoContainer;
    public LongCommonPlayerScreenChangedListener(ComponentActivity mActivity,ViewGroup mVideoContainer){
        this.mActivity=mActivity;
        this.mVideoContainer=mVideoContainer;
    }
    @Override
    public void onScreenTypeChanged(ScreenType screenType) {

        if (screenType == ScreenType.HALF_SCREEN) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            } else {
                mActivity.getWindow().getInsetsController().show(WindowInsetsCompat.Type.statusBars());
            }
            mVideoContainer.getLayoutParams().height = DpUtils.INSTANCE.dpToPx(200);
            mVideoContainer.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            fitSystemWindow(false);
            NotchCompat.blockDisplayCutout(mActivity.getWindow());
            mVideoContainer.requestLayout();
            restoreVideoContainer();
            if (NotchCompat.hasDisplayCutout(mActivity.getWindow())) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P && !RomUtils.isSamsungRom()) { //samsuang with cutout device excluded
                    setStatusBarColor(Color.BLACK);
                }
            }
        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                mActivity.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
                );
            } else {
                mActivity.getWindow().getInsetsController().hide(WindowInsetsCompat.Type.statusBars());
            }
            mVideoContainer.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoContainer.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            fitSystemWindow(false);
            NotchCompat.immersiveDisplayCutout(mActivity.getWindow());
            mVideoContainer.requestLayout();

            changeVideoContainerToTop();

            if (NotchCompat.hasDisplayCutout(mActivity.getWindow()) && !RomUtils.isSamsungRom()) {
                setStatusBarColor(Color.TRANSPARENT);
            }
        }
    }

    private void changeVideoContainerToTop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setElevation(mVideoContainer, 100f);
        } else {
            mVideoContainer.bringToFront();
        }
    }

    private void restoreVideoContainer() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setElevation(mVideoContainer, 0f);
        } else {
            ViewParent parent = mVideoContainer.getParent();
            if (parent instanceof ViewGroup) {
                int mOldVideoPageIndex = 0;
                if (((ViewGroup)parent).indexOfChild(mVideoContainer) != mOldVideoPageIndex) {
                    ((ViewGroup)parent).removeView(mVideoContainer);
                    ((ViewGroup)parent).addView(mVideoContainer, mOldVideoPageIndex);
                }
            }
        }
    }


    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mActivity.getWindow().setStatusBarColor(color);
        }
    }

    private void fitSystemWindow(boolean landscape) {
        View rootView = mVideoContainer;
        while (rootView != null && rootView.getId() != android.R.id.content) {
            if (rootView instanceof ViewGroup) {
                ((ViewGroup)rootView).setClipToPadding(!landscape);
                ((ViewGroup)rootView).setClipChildren(!landscape);
            }
            rootView = (View)rootView.getParent();
        }
    }
}