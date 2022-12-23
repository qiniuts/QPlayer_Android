package com.qiniu.qplayer2.ui.page.simplelongvideo;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.qiniu.qmedia.component.player.QMediaModel;
import com.qiniu.qplayer2.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VideoListAdapter extends Adapter<VideoHolder>{
    private ArrayList<Pair<String, QMediaModel>> mVideoList;
    private IVideoHolderClickListener mVideoHolderClickListener;
    public void setData(ArrayList<Pair<String, QMediaModel>> videoList){
        mVideoList = videoList;
    }
    public void setVideoHolderClickListener(IVideoHolderClickListener videoHolderClickListener){
        mVideoHolderClickListener = videoHolderClickListener;
    }
    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.holder_video_item, null, false
                ),
                mVideoHolderClickListener
        );
    }

    @Override
    public int getItemCount() {
        return mVideoList!=null?mVideoList.size():0;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.bind(mVideoList.get(position).first, mVideoList.get(position).second);
    }


}
