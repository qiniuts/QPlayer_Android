package com.qiniu.qplayer2.ui.page.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qiniu.qplayer2.R;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoActivity;
import com.qiniu.qplayer2.ui.page.longvideo.LongVideoActivity2;
import com.qiniu.qplayer2.ui.page.setting.SettingActivity;
import com.qiniu.qplayer2.ui.page.shortvideo.ShortVideoActivity;
import com.qiniu.qplayer2.ui.page.simplelongvideo.SimpleLongVideoActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.simple_long_video_player_BTN).setOnClickListener(view ->{
            Intent intent = new Intent(this, SimpleLongVideoActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.long_video_player_BTN).setOnClickListener(view ->{
            Intent intent = new Intent(this, LongVideoActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.setting_BTN).setOnClickListener(view ->{
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.short_video_player_BTN).setOnClickListener(view ->{
            Intent intent = new Intent(this, ShortVideoActivity.class);
            startActivity(intent);
        });
    }
}