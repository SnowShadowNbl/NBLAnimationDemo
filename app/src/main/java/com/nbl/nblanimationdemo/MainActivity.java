package com.nbl.nblanimationdemo;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Nbl(SnowShadow)
 * email: vsnowshadow@gmail.com
 * created
 */

/**
 * 1.AlphaAnimation(透明度动画)
 * a.fromAlpha：动画起始时透明度
 * b.toAlpha：动画终止时透明度
 * c.duration：变化的时间长短
 * 注：0.0表示完全透明-1.0表示完全不透明
 * <p>
 * 2.ScaleAnimation(缩放动画)
 * a.fromX,toX分别是起始和结束时x坐标上的伸缩尺寸
 * b.fromY,toY分别是起始和结束时y坐标上的伸缩尺寸
 * c.pivotX,pivotY分别为伸缩动画相对于x,y坐标开始的位置
 * d.fillAfter：变化后是否保留状态
 * e.interpolator：动画插入器
 * <p>
 * 3.TranslateAnimation(位移动画)
 * a.fromXDelta,fromYDelta分别是起始时X、Y的坐标
 * b.toXDelta,toYDelta分别是结束时X、Y的坐标
 * <p>
 * 4.RotateAnimation(旋转动画)
 * a.fromDegrees起始的角度
 * b.toDegrees终止的角度
 * c.pivotX,pivotY分别为旋转动画相对于x,y的坐标开始位置
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.iv_test)
    ImageView ivTest;
    @Bind(R.id.iv_frameA)
    ImageView ivFrame;
    @Bind(R.id.iv_frameB1)
    ImageView ivFrameB1;
    @Bind(R.id.iv_frameB2)
    ImageView ivFrameB2;
    @Bind(R.id.btn_scale)
    Button btnScale;
    @Bind(R.id.btn_alpha)
    Button btnAlpha;
    @Bind(R.id.btn_rotate)
    Button btnRotate;
    @Bind(R.id.btn_translate)
    Button btnTranslate;
    @Bind(R.id.btn_groupA)
    Button btnGroupA;
    @Bind(R.id.btn_groupB)
    Button btnGroupB;
    @Bind(R.id.btn_flicker)
    Button btnFlicker;
    @Bind(R.id.btn_shake)
    Button btnShake;
    @Bind(R.id.btn_change)
    Button btnChange;
    @Bind(R.id.btn_layout)
    Button btnLayout;
    @Bind(R.id.btn_frame)
    Button btnFrame;
    @Bind(R.id.start_frameB)
    Button startFrameB;
    @Bind(R.id.stop_frameB)
    Button stopFrameB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    //按键监听器
    @OnClick({R.id.btn_scale, R.id.btn_alpha, R.id.btn_rotate,
            R.id.btn_translate, R.id.btn_groupA, R.id.btn_groupB,
            R.id.btn_flicker, R.id.btn_shake, R.id.btn_change,
            R.id.btn_layout, R.id.btn_frame, R.id.start_frameB, R.id.stop_frameB})
    public void onClick(View v) {
        Animation loadAnimation;
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_scale:
                //缩放动画
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
                ivTest.startAnimation(loadAnimation);
                break;
            case R.id.btn_alpha:
                //透明度动画
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha);
                ivTest.startAnimation(loadAnimation);
                break;
            case R.id.btn_rotate:
                //旋转动画
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                ivTest.startAnimation(loadAnimation);
                break;
            case R.id.btn_translate:
                //位移动画
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                ivTest.startAnimation(loadAnimation);
                break;
            case R.id.btn_groupA:
                //组合动画A
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.translate);
                ivTest.startAnimation(loadAnimation);
                final Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.rotate);
                loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //动画开始
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //动画结束
                        ivTest.startAnimation(loadAnimation2);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //动画重复
                    }
                });
                break;
            case R.id.btn_groupB:
                //组合动画B
                loadAnimation = AnimationUtils.loadAnimation(this, R.anim.group_anim);
                ivTest.startAnimation(loadAnimation);
                break;
            case R.id.btn_flicker:
                //闪烁
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(100);//持续时间
                alphaAnimation.setRepeatCount(10);//重复次数
                alphaAnimation.setRepeatMode(Animation.REVERSE);//倒序重复REVERSE 正序重复RESTART
                ivTest.startAnimation(alphaAnimation);
                break;
            case R.id.btn_shake:
                //抖动
                TranslateAnimation translate = new TranslateAnimation(-50, 50,
                        0, 0);
                translate.setDuration(1000);
                translate.setRepeatCount(Animation.INFINITE);
                translate.setRepeatMode(Animation.REVERSE);
                ivTest.startAnimation(translate);
                break;
            case R.id.btn_change:
                //Activity跳转动画
                intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                break;
            case R.id.btn_layout:
                //布局动画
                intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_frame:
                //逐帧动画A
                ivFrame.setImageResource(R.drawable.anim_list);
                AnimationDrawable animationDrawableA = (AnimationDrawable) ivFrame.getDrawable();
                animationDrawableA.start();
                break;
            case R.id.start_frameB:
                //开始逐帧动画B
                ivFrameB1.setImageResource(R.drawable.loading_list_a);
                AnimationDrawable animationDrawableB1Start = (AnimationDrawable) ivFrameB1.getDrawable();
                animationDrawableB1Start.start();
                ivFrameB2.setImageResource(R.drawable.loading_list_b);
                AnimationDrawable animationDrawableB2Start = (AnimationDrawable) ivFrameB2.getDrawable();
                animationDrawableB2Start.start();
                break;
            case R.id.stop_frameB:
                //停止逐帧动画B
                ivFrameB1.setImageResource(R.drawable.loading_list_a);
                AnimationDrawable animationDrawableB1Stop = (AnimationDrawable) ivFrameB1.getDrawable();
                animationDrawableB1Stop.stop();
                ivFrameB2.setImageResource(R.drawable.loading_list_b);
                AnimationDrawable animationDrawableB2Stop = (AnimationDrawable) ivFrameB2.getDrawable();
                animationDrawableB2Stop.stop();
                break;
            default:
                break;
        }
    }

}
