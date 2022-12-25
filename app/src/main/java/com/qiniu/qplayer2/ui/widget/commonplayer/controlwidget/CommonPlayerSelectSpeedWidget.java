package com.qiniu.qplayer2.ui.widget.commonplayer.controlwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import com.qiniu.qmedia.component.player.QIPlayerSpeedListener;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.commonplayer.CommonPlayerCore;
import com.qiniu.qplayer2ext.commonplayer.layer.control.IControlWidget;
import com.qiniu.qplayer2ext.commonplayer.layer.function.PlayerFunctionContainer;
import com.qiniu.qplayer2.ui.page.longvideo.LongLogicProvider;
import com.qiniu.qplayer2.ui.page.longvideo.LongPlayableParams;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.speed.SpeedFunctionWidget;
import com.qiniu.qplayer2ext.commonplayer.layer.function.FunctionWidgetLayoutParams;

public class CommonPlayerSelectSpeedWidget extends AppCompatTextView implements
        IControlWidget<LongLogicProvider, LongPlayableParams, LongVideoParams>, View.OnClickListener {

private CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> mPlayerCore;
private PlayerFunctionContainer.FunctionWidgetToken<LongLogicProvider, LongPlayableParams, LongVideoParams> mSpeedToken;
private QIPlayerSpeedListener mSpeedChangeListener=new QIPlayerSpeedListener() {
    @Override
    public void onSpeedChanged(float speed) {
        updateSpeedText(speed);
    }
};

        public CommonPlayerSelectSpeedWidget(Context context) {
            super(context);
        init(context, null);

        }

    public CommonPlayerSelectSpeedWidget(Context context,AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

        }

    public CommonPlayerSelectSpeedWidget(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        }

private void init(Context context,AttributeSet attrs) {
        setText("倍速");
        }

private void updateSpeedText(float speed) {
        if (speed <= 0.51f) {
            setText("0.5X");
        } else if (speed < 0.76f) {
            setText("0.75X");
        } else if (speed < 1.01f) {
            setText(getContext().getString(R.string.common_player_speed));
        } else if (speed < 1.26f) {
            setText("1.25X");
        } else if (speed < 1.51f) {
            setText("1.5X");
        }else {
            setText("2.0X");
        }
        }

        @Override
        public void onWidgetActive() {
        setOnClickListener(this);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().addPlayerSpeedChangeListener(mSpeedChangeListener);
        updateSpeedText(mPlayerCore.getMPlayerContext().getPlayerControlHandler().getSpeed());

        }

        @Override
        public void onWidgetInactive() {
        setOnClickListener(null);
        mPlayerCore.getMPlayerContext().getPlayerControlHandler().removePlayerSpeedChangeListener(mSpeedChangeListener);

        }

        @Override
        public void bindPlayerCore(CommonPlayerCore<LongLogicProvider, LongPlayableParams, LongVideoParams> playerCore) {
        mPlayerCore = playerCore;
        }

        @Override
        public void onClick(View v) {
            FunctionWidgetLayoutParams layoutParams = new FunctionWidgetLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setLayoutType(FunctionWidgetLayoutParams.LayoutAlignType.RIGHT);
        mSpeedToken = mPlayerCore.getPlayerFunctionWidgetContainer().showWidget(SpeedFunctionWidget.class, layoutParams);
        }
        }