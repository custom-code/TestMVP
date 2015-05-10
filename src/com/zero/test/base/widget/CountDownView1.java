package com.zero.test.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.TextView;
import com.zero.test.R;
import com.zero.test.base.listener.OnCountDownListener;

/**
 * Created by 哲 on 2015/5/10.
 */
public class CountDownView1 extends TextView {
    private OnCountDownListener listener;
    private CountDownTimer timer;
    private boolean isTick = false;//是否正在倒计时

    public CountDownView1(Context context) {
        super(context);
    }

    public CountDownView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.countdown_view);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.countdown_view_custom_text:
                    CharSequence text = typedArray.getText(attr);
                    CountDownView1.this.setText(text);
                    break;
                case R.styleable.countdown_view_custom_src:
                    Drawable drawable = typedArray.getDrawable(attr);
                    CountDownView1.this.setBackgroundDrawable(drawable);
                    break;
            }
        }
        typedArray.recycle();
    }

    public CountDownView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CountdownView, defStyle, 0);
    }

    /**
     * 开始计时
     */
    public void startCountDown() {
        timer = new MyCountDownTimer(60 * 1000, 1000);
        timer.start();
        setViewState();
    }

    public void setCountDownListener(OnCountDownListener listener) {
        this.listener = listener;
    }

    /**
     * 更改状态
     */
    private void setViewState() {
        if (isTick) {
            CountDownView1.this.setEnabled(true);
            CountDownView1.this.setClickable(true);
            isTick = false;
        } else {
            isTick = true;
            CountDownView1.this.setEnabled(false);
            CountDownView1.this.setClickable(false);
        }
    }

    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountDownView1.this.setText("还剩" + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            CountDownView1.this.setText("倒计时完成");
            setViewState();
            listener.onFinished();
        }
    }

}
