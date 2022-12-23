package com.qiniu.qplayer2.ui.page.longvideo;

import com.qiniu.qmedia.component.player.QMediaModel;
import com.qiniu.qplayer2ext.commonplayer.data.CommonPlayableParams;
import com.qiniu.qplayer2ext.commonplayer.data.DisplayOrientation;

public class LongPlayableParams extends CommonPlayableParams {
    public LongPlayableParams(QMediaModel mediaModel, String controlPanelType, DisplayOrientation displayOrientation,String environmentType,long startPos){
        super(mediaModel, controlPanelType, displayOrientation, environmentType, startPos);
    }
}
