package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.videolist;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoParams;
import com.qiniu.qplayer2ext.common.measure.DpUtils;

import java.util.List;

class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<LongVideoParams> mVideoParamsList;
    private LayoutInflater mLayoutInflater;

    private long mSelectedVideoId = -1L;

    private OnItemClickListener mItemClickListener;
    private Context context;
    public VideoListAdapter(Context context, List<LongVideoParams> mVideoParamsList){
        this.mVideoParamsList=mVideoParamsList;
        this.context=context;
        init();
    }

    public void init() {
        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mLayoutInflater == null) {
            throw new AssertionError("LayoutInflater not found.");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        return ViewHolder.createHolder(mLayoutInflater, mItemClickListener, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position) {
        ((ViewHolder)holder).bind(getItem(position), mSelectedVideoId);
    }

    @Override
    public int getItemCount() {
        return mVideoParamsList==null?0:mVideoParamsList.size();

    }

    public LongVideoParams getItem(int position){
        if (position >= 0 && position < mVideoParamsList.size()) {
            return mVideoParamsList.get(position);
        } else return null;
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setItems(List<LongVideoParams> paramsList) {
        mVideoParamsList = paramsList;
    }

    public void setSelectedVideoId(long id) {
        mSelectedVideoId = id;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private OnItemClickListener itemClickListener;
        public ViewHolder(View itemView,OnItemClickListener itemClickListener){
            super(itemView);
            this.itemView=itemView;
            this.itemClickListener=itemClickListener;
            title= itemView.findViewById(R.id.title_TV);
            container= itemView.findViewById(R.id.container_LL);
            init();
        }
        private TextView title;
        private LinearLayout container;

        private long mVideoId = -1;
        public void init() {
            itemView.setOnClickListener(  v ->{
                        itemClickListener.onItemClick(mVideoId);
                    }
            );
        }

        public void bind(LongVideoParams item,long selectedVideoId) {
            if (item == null) {
                return;
            }
            mVideoId = item.getId();
            int maxWidth = DpUtils.INSTANCE.dpToPx(300);
            container.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
            boolean selected = selectedVideoId == mVideoId;
            itemView.setSelected(selected);
            title.setMaxWidth(maxWidth);
            title.setText(item.title);
        }
        public static ViewHolder createHolder(LayoutInflater inflater,OnItemClickListener itemClickListener,ViewGroup parent){
            return new ViewHolder(inflater.inflate(R.layout.holder_common_player_video_list_item, parent, false), itemClickListener);
        }
    }

    interface OnItemClickListener {
        public void onItemClick(long id);
    }
}
