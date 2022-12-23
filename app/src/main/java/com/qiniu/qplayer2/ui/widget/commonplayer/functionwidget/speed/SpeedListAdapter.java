package com.qiniu.qplayer2.ui.widget.commonplayer.functionwidget.speed;

import android.annotation.SuppressLint;
import android.hardware.lights.LightState;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qiniu.qplayer2.R;

import java.util.ArrayList;
import java.util.List;

class SpeedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private OnItemSelectListener mItemListener;
    private List<SpeedItem> mItemList = new ArrayList<>();
    private float[] PLAYBACK_SPEED = {2.00f, 1.50f, 1.25f, 1.00f, 0.75f, 0.50f};

    public void setData(float currentSpeed) {
        mItemList.clear();
        for(float speed:PLAYBACK_SPEED){
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH && speed >= 1.99f) {
                continue;
            }
            SpeedItem qualityItem = new SpeedItem();
            qualityItem.speed = speed;
            qualityItem.isSelected = Math.abs(currentSpeed - speed) < 0.1;
            mItemList.add(qualityItem);
        }

    }

    @Override
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        return SpeedItemHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SpeedItem speedItem = mItemList.get(position);

        holder.itemView.setTag(speedItem);
        holder.itemView.setOnClickListener(this);
        if(holder instanceof SpeedItemHolder){
            ((SpeedItemHolder)holder).bind(speedItem);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public void onClick(View v) {
        if(mItemListener==null){
            return;
        }
        if(!(v.getTag() instanceof SpeedItem)){
            return;
        }


        SpeedItem speedItem = (SpeedItem)v.getTag();
        int pos = mItemList.indexOf(speedItem);

        if (!speedItem.isSelected) {
            float speed = speedItem.speed;
            mItemListener.onItemSelected(speed);
            for(int i=0;i<mItemList.size();i++){
                mItemList.get(i).isSelected = i == pos;
            }
            notifyDataSetChanged();
        }
    }

    public void setItemSelectListener(OnItemSelectListener l) {
        mItemListener = l;
    }

    public static class SpeedItemHolder  extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private TextView mTextView;
        private SpeedItemHolder(TextView mTextView){
            super(mTextView);
            this.mTextView=mTextView;
        }

        @SuppressLint("SetTextI18n")
        public void bind(SpeedItem speedItem) {
            if(speedItem==null){
                return;
            }
            mTextView.setText(speedItem.speed + "X");
            mTextView.setSelected(speedItem.isSelected);
        }
        public static  SpeedItemHolder create(ViewGroup parent) {
            TextView textView = (TextView)LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_common_player_list_item, parent, false);
            return new SpeedItemHolder(textView);
        }
    }

    public class SpeedItem {
        float speed = 1.0f;
        boolean isSelected = false;
    }

    public interface OnItemSelectListener {
        public void onItemSelected(float speed);
    }
    public static final int TYPE_NORMAL = 0;
}