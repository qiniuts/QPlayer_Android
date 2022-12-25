package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.videoquality;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qiniu.qmedia.component.player.QQuality;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2ext.common.measure.DpUtils;

import java.util.List;

public class VideoQualityListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<QQuality> mQualityList;
    private Context context;
    public VideoQualityListAdapter(Context context, List<QQuality> mQualityList ){
        this.context=context;
        this.mQualityList=mQualityList;
        init();
    }
    private LayoutInflater mLayoutInflater;

    private QQuality mSelectedQuality;

    private OnItemClickListener mItemClickListener;


    private void init() {
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
        ((ViewHolder)holder).bind(getItem(position), mSelectedQuality);
    }

    @Override
    public int getItemCount() {
        return mQualityList==null?0:mQualityList.size();

    }

    public QQuality getItem(int position) {
        if (position >= 0 && position < mQualityList.size()) {
            return mQualityList.get(position);
        } else return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setQualities(List<QQuality> qualities) {
        mQualityList = qualities;
    }

    public void setSelectedQuality(QQuality quality) {
        mSelectedQuality = quality;
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private LinearLayout container ;
        private OnItemClickListener itemClickListener;
        public ViewHolder(View itemView,OnItemClickListener itemClickListener){
            super(itemView);
            this.itemClickListener=itemClickListener;
            title  = itemView.findViewById(R.id.title_TV);
            container  = itemView.findViewById(R.id.container_LL);
            init();
        }
        private QQuality mQuality;
        private void init() {
            itemView.setOnClickListener(v->itemClickListener.onItemClick(mQuality));
        }

        public void bind(QQuality quality,QQuality selectedQuality) {
            if (quality == null) {
                return;
            }
            mQuality = quality;
            int maxWidth = DpUtils.INSTANCE.dpToPx(300);
            container.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
            boolean selected =
                    (selectedQuality.getQuality() == mQuality.getQuality());
            itemView.setSelected(selected);
            title.setMaxWidth(maxWidth);
            title.setText(getQualityDesc(mQuality));
        }

        public String getQualityDesc(QQuality quality) {
            switch (quality.getQuality()){
                case 1080:
                    return "1080P";
                case 720:
                    return "720P";
                case 480:
                    return "480P";
                case 360:
                    return "360P";
                case 2400:
                    return "240P";
                default:
                    return "";
            }
        }
        public static ViewHolder createHolder(LayoutInflater inflater, OnItemClickListener itemClickListener, ViewGroup parent
        ) {
            return new ViewHolder(inflater.inflate(R.layout.holder_common_player_video_list_item, parent, false), itemClickListener);
        }
    }

    interface OnItemClickListener {
        public void onItemClick(QQuality quality);
    }
}
