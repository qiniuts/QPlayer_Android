package com.qiniu.qplayer2.ui.page.shortvideo;

import android.util.Log;
import com.qiniu.qmedia.component.player.QLogLevel;
import com.qiniu.qmedia.component.player.QMediaItemContext;
import com.qiniu.qmedia.component.player.QMediaItemState;
import com.qiniu.qmedia.component.player.QMediaModel;

import java.util.HashMap;
import java.util.Map;

public class MediaItemContextManager {

    private Map<Integer, QMediaItemContext> mMediaItemContextHashMap = new HashMap<Integer, QMediaItemContext>();

    public void load(int id,QMediaModel mediaModel,long startPos,QLogLevel logLevel,String localStorageDir) {
        QMediaItemContext mediaItem = mMediaItemContextHashMap.get(id);
        if (mediaItem != null) {
            if (mediaItem.getPlayMediaControlHandler().getCurrentState() == QMediaItemState.STOPED ||
                    mediaItem.getPlayMediaControlHandler().getCurrentState() == QMediaItemState.ERROR)  {
                mediaItem.getPlayMediaControlHandler().stop();
                mMediaItemContextHashMap.remove(id);
                mediaItem = null;
            }
        }

        if (mediaItem == null) {
            mediaItem =new QMediaItemContext(mediaModel, logLevel, localStorageDir, startPos);
            mediaItem.getPlayMediaControlHandler().start();
            mMediaItemContextHashMap.put(id,mediaItem);
        }
    }

    public QMediaItemContext fetchMediaItemContext(int id) {
        QMediaItemContext mediaItem  = mMediaItemContextHashMap.get(id);
        mMediaItemContextHashMap.remove(id);
        try {
            Log.d("MediaItemContextManager", String.valueOf(mediaItem.getPlayMediaControlHandler().getCurrentState().getValue()));
        }catch (Exception e){e.printStackTrace();}
        return mediaItem;
    }

    public void discardAllMediaItemContexts() {
        for (Map.Entry<Integer, QMediaItemContext> entry : mMediaItemContextHashMap.entrySet()) {
            entry.getValue().getPlayMediaControlHandler().stop();
        }

        mMediaItemContextHashMap.clear();
    }
}