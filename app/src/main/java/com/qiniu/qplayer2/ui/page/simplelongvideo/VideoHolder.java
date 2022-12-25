package com.qiniu.qplayer2.ui.page.simplelongvideo;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.qiniu.qmedia.component.player.QMediaModel;
import com.qiniu.qplayer2.R;

public class VideoHolder extends RecyclerView.ViewHolder{
    private QMediaModel mMediaModel;
    private String mName="";
    private TextView mNameTV;
    private IVideoHolderClickListener mClickListener;
    public VideoHolder(View itemView,IVideoHolderClickListener clickListener){
        super(itemView);
        mClickListener = clickListener;
        mNameTV = itemView.findViewById(R.id.name);
        mNameTV.setOnClickListener(View ->mClickListener.onClick(mMediaModel));
    }
    public void bind(String name,QMediaModel media_model){
        mName = name;
        mMediaModel = media_model;
        mNameTV.setText(name);
    }
}
