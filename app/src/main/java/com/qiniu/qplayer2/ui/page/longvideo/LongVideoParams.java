package com.qiniu.qplayer2.ui.page.longvideo;

import com.qiniu.qplayer2ext.commonplayer.data.CommonVideoParams;

public class LongVideoParams extends CommonVideoParams {
    public String title;
    public LongVideoParams(String title,long id){
        super(id);
        this.title=title;
    }
}
