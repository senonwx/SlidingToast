package com.example.senon.slidingtoast;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Senon on 2018/9/5.
 */

public class SlidingRelativeLayout extends RelativeLayout {

    public SlidingRelativeLayout(Context context) {
        super(context);
    }

    public SlidingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float startX = 0;//初始按下的点
    float startY = 0;

    OnLayoutSlidingListener listener;
    public void setOnLayoutSlidingListener(OnLayoutSlidingListener listener){
        this.listener = listener;
    }
    interface OnLayoutSlidingListener{
        void onLayoutSliding();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE://移动后形成的点
                float endX = event.getX();
                float endY = event.getY();
                //当手势向下滑动并且 竖直方向滑动距离大于横向才算有效
                if(endY - startY > 30 && (endY - startY) > Math.abs(startX - endX)){
//                    Toast.makeText(MyApplication.getContext(),"sliding Toast",Toast.LENGTH_SHORT).show();
                    if(listener != null){
                        listener.onLayoutSliding();
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
//                Toast.makeText(MyApplication.getContext(),"MotionEvent.ACTION_UP",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE://移动后形成的点
                float endX = event.getX();
                float endY = event.getY();
                //当手势向下滑动并且 竖直方向滑动距离大于横向才算有效
                if(endY - startY > 10 && (endY - startY) > Math.abs(startX - endX)){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
//                Toast.makeText(MyApplication.getContext(),"MotionEvent.ACTION_UP",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onInterceptTouchEvent(event);
    }


}
