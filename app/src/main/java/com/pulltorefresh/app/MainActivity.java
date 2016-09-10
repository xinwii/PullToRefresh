package com.pulltorefresh.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView refreshAnim;//点点动画的ImageView
    private MoveImageView moveImageView;
    private MoveImageHelper moveImageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshAnim = (ImageView) findViewById(R.id.iv_pull_to_refresh_anim);
        moveImageView = (MoveImageView) findViewById(R.id.iv_pull_to_refresh_bg);
        moveImageHelper = new MoveImageHelper(refreshAnim, moveImageView);
        moveImageHelper.start();
    }
}
