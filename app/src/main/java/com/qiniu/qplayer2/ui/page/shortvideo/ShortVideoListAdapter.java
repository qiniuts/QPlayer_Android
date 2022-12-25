package com.qiniu.qplayer2.ui.page.shortvideo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.qiniu.qmedia.ui.QSurfacePlayerView;
import com.qiniu.qplayer2.R;
import java.util.*;

public class ShortVideoListAdapter extends RecyclerView.Adapter<ShortVideoHolder> {
    private ArrayList<PlayItem> mPlayItemList;
    private QSurfacePlayerView mVideoPlayerView;
    public ShortVideoListAdapter(ArrayList<PlayItem> mPlayItemList,QSurfacePlayerView mVideoPlayerView){
        super();
        this.mPlayItemList=mPlayItemList;
        this.mVideoPlayerView=mVideoPlayerView;
    }

    @Override
    public ShortVideoHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.holder_short_video, parent, false);
        return new ShortVideoHolder(contactView, mVideoPlayerView);
    }

    @Override
    public void onBindViewHolder(ShortVideoHolder holder,int position) {
        if (mPlayItemList.size() > position) {
            holder.bind(mPlayItemList.get(position), position);
        }
    }

    @Override
    public int getItemCount(){
        return mPlayItemList!=null?mPlayItemList.size():0;
    }
}