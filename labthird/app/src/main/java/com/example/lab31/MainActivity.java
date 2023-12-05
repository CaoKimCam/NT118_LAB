package com.example.lab31;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.icu.number.Scale;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnFadeInXml, btnFadeInCode,
            btnFadeOutXml, btnFadeOutCode,
            btnBlinkXml, btnBlinkCode,
            btnZoomInXml, btnZoomInCode,
            btnZoomOutXml, btnZoomOutCode,
            btnRotateXml, btnRotateCode,
            btnMoveXml, btnMoveCode,
            btnSlideUpXml, btnSlideUpCode,
            btnBounceXml, btnBounceCode,
            btnCombineXml, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();
        initVariables();

        ivUitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(iNewActivity);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
        //xml
        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml,R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);

        //code
        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
        handleClickAnimationCode(btnMoveCode, initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, initBounceAnimation());
        handleClickAnimationCode(btnCombineCode, initCombineAnimation());
    }

    private void findViewByIds()
    {
        ivUitLogo=(ImageView) findViewById(R.id.iv_uit_logo);
        //1.Fade In
        btnFadeInXml=(Button)  findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode=(Button) findViewById(R.id.btn_fade_in_code);
        //2.Fade Out
        btnFadeOutCode=(Button) findViewById(R.id.btn_fade_out_code);
        btnFadeOutXml=(Button) findViewById(R.id.btn_fade_out_xml);
        //2.Blink
        btnBlinkXml=(Button) findViewById(R.id.btn_blink_xml);
        btnBlinkCode=(Button) findViewById(R.id.btn_blink_code);
        //4.Zoom In
        btnZoomInCode=(Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomInXml=(Button) findViewById(R.id.btn_zoom_in_xml);
        //5.Zoom Out
        btnZoomOutCode=(Button) findViewById(R.id.btn_zoom_out_code);
        btnZoomOutXml=(Button) findViewById(R.id.btn_zoom_out_xml);
        //6. Rotate
        btnRotateCode=(Button) findViewById(R.id.btn_rotate_code);
        btnRotateXml=(Button) findViewById(R.id.btn_rotate_xml);
        //7.Move
        btnMoveCode=(Button) findViewById(R.id.btn_move_code);
        btnMoveXml=(Button) findViewById(R.id.btn_move_xml);
        //8.Slide Up
        btnSlideUpCode=(Button) findViewById(R.id.btn_slide_up_code);
        btnSlideUpXml=(Button) findViewById(R.id.btn_slide_up_xml);
        //9. Bounce
        btnBounceCode=(Button) findViewById(R.id.btn_bounce_code);
        btnBounceXml=(Button) findViewById(R.id.btn_bounce_xml);
        //10.Combine
        btnCombineCode=(Button) findViewById(R.id.btn_combine_code);
        btnCombineXml=(Button) findViewById(R.id.btn_combine_xml);
    }
    private void initVariables()
    {
        animationListener=new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(),"Animation Stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }
    private void handleClickAnimationXml (Button btn, int animId)
    {
        //load the animation
        final Animation animation= AnimationUtils.loadAnimation(MainActivity.this, animId);
        //set animation listener

        animation.setAnimationListener(animationListener);
        //handle onclickListener to start animation
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }
    private void handleClickAnimationCode(Button btn, final Animation animation)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }


    private Animation initFadeInAnimation()
    {
        AlphaAnimation animation = new AlphaAnimation(0f,1f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initFadeOutAnimation()
    {
        AlphaAnimation animation=new AlphaAnimation(1f,0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initBlinkAnimation()
    {
        AlphaAnimation animation= new AlphaAnimation(0f,1f);
        animation.setDuration(300);
        animation.setRepeatMode(animation.REVERSE);
        animation.setRepeatCount(3);
        return animation;
    }
    private Animation initZoomInAnimation()
    {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 3f,
                1f, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        //animationSet.addAnimation(scaleAnimation);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        //animationSet.addAnimation(translateAnimation);

        return animationSet;
    }
    private Animation initZoomOutAnimation()
    {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 0.5f,
                1f, 0.5f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        return scaleAnimation;
    }
    private Animation initRotateAnimation()
    {

        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(600);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setFillAfter(true);
        //rotateAnimation.setInterpolator(new CycleInterpolator(0f));//#
        return rotateAnimation;
    }
    private Animation initMoveAnimation()
    {
        //AnimationSet animationSet= new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 2.75f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(800);
        Interpolator interpolator = new AccelerateInterpolator();
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setFillAfter(true);
        //animationSet.addAnimation(translateAnimation);
        return translateAnimation;
    }
    private Animation initSlideUpAnimation(){
        //AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 1f, 1f, 0f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        //animationSet.addAnimation(scaleAnimation);
        return scaleAnimation;
    };
    private Animation initBounceAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1f, 0f, 1f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }
    private Animation initCombineAnimation()
    {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, 3f, 1f, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(4000);
        scaleAnimation.setFillAfter(true);

        RotateAnimation rotateAnimation = new RotateAnimation(
                0,360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }
}