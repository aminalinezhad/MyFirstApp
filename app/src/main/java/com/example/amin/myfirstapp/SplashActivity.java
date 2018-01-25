package com.example.amin.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    public static final String USER_NAME = "UserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView img_Splash = (ImageView) findViewById(R.id.img_Splash);

        ScaleAnimation scaleAnim = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnim.setDuration(2000);
        scaleAnim.setFillAfter(true); //to keep image changes
//        scaleAnim.setAnimationListener(v -> showMainActivity());
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        AlphaAnimation alphaAnim = new AlphaAnimation(0,1);
        alphaAnim.setDuration(2000);

        AnimationSet animSet = new AnimationSet(true);
        animSet.addAnimation(scaleAnim);
        animSet.addAnimation(alphaAnim);

        img_Splash.setAnimation(animSet);

    }

    private void showMainActivity(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        setData(intent);
        finish();
        startActivity(intent);
    }

    private void setData(Intent intent){
        intent.putExtra(USER_NAME, "Alinezhad");
    }


}
