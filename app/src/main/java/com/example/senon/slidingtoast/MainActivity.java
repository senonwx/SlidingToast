package com.example.senon.slidingtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private SlidingRelativeLayout lay;
    private TextView tvToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lay = (SlidingRelativeLayout) findViewById(R.id.lay);
        tvToast = (TextView) findViewById(R.id.tv_toast);

        lay.setOnLayoutSlidingListener(new SlidingRelativeLayout.OnLayoutSlidingListener() {
            @Override
            public void onLayoutSliding() {
                showSlidingToast("一共有123456位秀友和你在一起");
            }
        });
    }


    public void Click1(View view) {
        Toast.makeText(MyApplication.getContext(),"Click1",Toast.LENGTH_SHORT).show();
    }
    public void Click2(View view) {
        Toast.makeText(MyApplication.getContext(),"Click2",Toast.LENGTH_SHORT).show();
    }
    public void Click3(View view) {
        Toast.makeText(MyApplication.getContext(),"Click3",Toast.LENGTH_SHORT).show();
    }

    public void customClick(View view){
        showSlidingToast(null);
    }

    public void showSlidingToast(String tipMsg) {
        if(tvToast.getVisibility() == View.VISIBLE){
            return;
        }
        tvToast.setVisibility(View.VISIBLE);
        //设置SlidingToast的信息
        tvToast.setText(tipMsg == null ? "这是一条提示信息" : tipMsg);

        //创建动画集合
        AnimationSet animationSet = new AnimationSet(true);
        //滑入的动画
        TranslateAnimation inAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1,
                Animation.RELATIVE_TO_SELF, 0);
        inAnimation.setDuration(500);
        inAnimation.setFillAfter(true);
        //将该滑入的位移动画添加到动画集合
        animationSet.addAnimation(inAnimation);

        //滑出的动画
        TranslateAnimation outAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1);
        outAnimation.setDuration(500);
        outAnimation.setFillAfter(true);
        //设置动画的启动时间，滑出动画在滑入动画后的3秒后执行
        outAnimation.setStartOffset(3500);
        //将该滑出的位移动画添加到动画集合
        animationSet.addAnimation(outAnimation);
        //开启动画
        tvToast.startAnimation(animationSet);
        //动画监听事件，动画完成之后重新让SlidingToast消失
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                tvToast.setVisibility(View.GONE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
