package com.megahertzlabs.educationdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    ImageView splash_book;
    TextView splash_apptitle;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash_book=findViewById(R.id.splash_book);
        splash_apptitle=findViewById(R.id.splash_apptitle);
        animation= AnimationUtils.loadAnimation(SplashActivity.this,android.R.anim.slide_in_left);
        animation.setDuration(1000);
        splash_book.setAnimation(animation);
        splash_apptitle.setAnimation(animation);
        animation.start();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(SplashActivity.this,TosActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }


}
