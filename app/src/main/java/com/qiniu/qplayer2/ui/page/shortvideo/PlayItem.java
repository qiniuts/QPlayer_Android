package com.qiniu.qplayer2.ui.page.shortvideo;

import com.qiniu.qmedia.component.player.QMediaItemContext;
import com.qiniu.qmedia.component.player.QMediaModel;

public class PlayItem {
    public int id;
    public QMediaModel mediaModel;
    public String coverUrl;
    public PlayItem(int id, QMediaModel mediaModel, String coverUrl){
        this.id=id;
        this.mediaModel=mediaModel;
        this.coverUrl=coverUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMediaModel(QMediaModel mediaModel) {
        this.mediaModel = mediaModel;
    }

    public QMediaModel getMediaModel() {
        return mediaModel;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }
}