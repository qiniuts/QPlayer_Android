package com.qiniu.qplayer2.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import com.qiniu.qmedia.component.player.QPlayerControlHandler;
import com.qiniu.qplayer2.logic.PlayerBufferingVM;
import com.qiniu.qplayer2.logic.PlayerProgressVM;

public class PlayerBufferingWidget extends AppCompatTextView {

    private PlayerBufferingVM mPlayerBufferingVM = new PlayerBufferingVM();
    private Observer<Boolean> mObserver  = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean it) {
            setVisibility(it?View.VISIBLE:View.INVISIBLE);
        }
    };


    public PlayerBufferingWidget(Context context)  {
        super(context);
    }

    public PlayerBufferingWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerBufferingWidget(Context context,AttributeSet attrs,int defStyleAttr)  {
        super(context,attrs,defStyleAttr);
    }

    public void setPlayerControlHandler(QPlayerControlHandler controlHandler) {
        mPlayerBufferingVM.setQPlayerControlHandler(controlHandler);
        if (controlHandler != null) {
            init();
        } else {
            uninit();
        }
    }

    private void init() {
        mPlayerBufferingVM.playerBufferingLiveData.observeForever(mObserver);
    }

    private void uninit() {
        mPlayerBufferingVM.playerBufferingLiveData.removeObserver(mObserver);

    }
}