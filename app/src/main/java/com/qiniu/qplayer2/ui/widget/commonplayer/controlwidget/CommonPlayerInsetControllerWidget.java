package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;

import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;


public class CommonPlayerInsetControllerWidget extends ConstraintLayout implements
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams> {
        private Drawable mTopBackground = null;
        private Drawable mBottomBackground = null;
        private int mTopBackgroundHeight = 0;
        private int mBottomBackgroundHeight = 0;
        private int mContentTopPadding = 0;
        private int mContentBottomPadding = 0;
        private int mContentLeftPadding = 0;
        private int mContentRightPadding = 0;

        public CommonPlayerInsetControllerWidget(Context context){
                this(context, null, 0);
        }

        public CommonPlayerInsetControllerWidget(Context context,AttributeSet attrs){
                this(context, attrs, 0);
        }

        public CommonPlayerInsetControllerWidget(Context context,AttributeSet attrs,int defStyleAttr){
                super(context, attrs, defStyleAttr);
                init(context, attrs, defStyleAttr);

        }

//    private val mWindowInsetObserver = object : IWindowInsetObserver {
//        override fun onWindowInsetChanged(windowInset: WindowInset) {
//            val left = if (windowInset.leftPadding > 0) {
//                if (windowInset.leftPadding > mContentLeftPadding) windowInset.leftPadding - mContentLeftPadding else windowInset.leftPadding
//            } else {
//                0
//            }
//            val top = if (windowInset.topPadding > 0) {
//                if (windowInset.topPadding > mContentTopPadding) windowInset.topPadding - mContentTopPadding else windowInset.topPadding
//            } else {
//                0
//            }
//            val right = if (windowInset.rightPadding > 0) {
//                if (windowInset.rightPadding > mContentRightPadding) windowInset.rightPadding - mContentRightPadding else windowInset.rightPadding
//            } else {
//                0
//            }
//            val bottom = if (windowInset.bottomPadding > 0) {
//                if (windowInset.bottomPadding > mContentBottomPadding) windowInset.bottomPadding - mContentBottomPadding else windowInset.bottomPadding
//            } else {
//                0
//            }
//            setPadding(left, top, right, bottom)
//        }
//    }

        @SuppressLint("CustomViewStyleable")
        private void init(Context context,AttributeSet attrs,int defStyleAttr) {
                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonPlayerWindowInset, defStyleAttr, 0);
                mTopBackground = a.getDrawable(R.styleable.CommonPlayerWindowInset_topBackground);
                mBottomBackground = a.getDrawable(R.styleable.CommonPlayerWindowInset_bottomBackground);
                if (mTopBackground != null) {
                        mTopBackgroundHeight = a.getDimensionPixelOffset(R.styleable.CommonPlayerWindowInset_topBackgroundHeight, 0);
                }
                if (mBottomBackground != null) {
                        mBottomBackgroundHeight = a.getDimensionPixelOffset(R.styleable.CommonPlayerWindowInset_bottomBackgroundHeight, 0);
                }
                mContentTopPadding = a.getDimensionPixelOffset(R.styleable.CommonPlayerWindowInset_contentTopPadding, 0);
                mContentBottomPadding = a.getDimensionPixelOffset(R.styleable.CommonPlayerWindowInset_contentBottomPadding, 0);
                mContentLeftPadding = a.getDimensionPixelOffset(R.styleable.CommonPlayerWindowInset_contentLeftPadding, 0);
                mContentRightPadding = a.getDimensionPixelOffset(R.styleable.CommonPlayerWindowInset_contentRightPadding, 0);

                a.recycle();
        }

        @Override
        public void onWidgetActive() {
//        mPlayerContainer?.getActivityStateService()?.registerWindowInset(mWindowInsetObserver)
//        val windowInset = mPlayerContainer?.getActivityStateService()?.getWindowInset()
//        if (windowInset != null) {
//            mWindowInsetObserver.onWindowInsetChanged(windowInset)
//        } else {
//            mWindowInsetObserver.onWindowInsetChanged(WindowInset())
//        }
        }

        @Override
        public void onWidgetInactive() {
//        mPlayerContainer?.getActivityStateService()?.unregisterWindowInset(mWindowInsetObserver)
        }



        @Override
        public void draw(Canvas canvas) {
                drawBackground(canvas);
                super.draw(canvas);
        }

        @Override
        public void dispatchDraw(Canvas canvas) {
                drawBackground(canvas);
                super.dispatchDraw(canvas);
        }
        private void drawBackground(Canvas canvas) {
                if(canvas==null) return;
                int measureWidth = getMeasuredWidth();
                int measureHeight = getMeasuredHeight();
                if (measureHeight <= 0 || measureWidth <= 0) {
                        return;
                }
                if (mTopBackground != null && mTopBackgroundHeight > 0) {
                        mTopBackground.setBounds(0, 0, measureWidth, mTopBackgroundHeight);
                        drawDrawable(mTopBackground, canvas);
                }

                if (mBottomBackground != null && mBottomBackgroundHeight > 0) {
                        mBottomBackground.setBounds(0, measureHeight - mBottomBackgroundHeight, measureWidth, measureHeight);
                        drawDrawable(mBottomBackground, canvas);
                }
        }

        private void drawDrawable(Drawable drawable,Canvas canvas) {
                int sX = getScrollX();
                int sY = getScrollY();
                if (sX==0 || sY == 0) {
                        drawable.draw(canvas);
                } else {
                        canvas.translate((float) sX, (float)sY);
                        drawable.draw(canvas);
                        canvas.translate((float)(-sX), (float)(-sY));
                }
        }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        }
}
