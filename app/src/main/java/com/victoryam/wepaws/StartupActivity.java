package com.victoryam.wepaws;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class StartupActivity extends Activity {
    ImageView startupLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4200);

        startupLogo = (ImageView) findViewById(R.id.startup_image);
        startupLogo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_logo));

    }
}
