package com.zero.test.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.TextView;
import com.zero.test.R;

/**
 * Created by 哲 on 2015/5/10.
 */
public class CountdownView extends TextView {
    private boolean isTick = false;//是否正在倒计时

    public CountdownView(Context context) {
        super(context);
    }

    public CountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.countdown_view);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.countdown_view_custom_text:
                    CharSequence text = typedArray.getText(attr);
                    CountdownView.this.setText(text);
                    break;
                case R.styleable.countdown_view_custom_src:
                    Drawable drawable = typedArray.getDrawable(attr);
                    CountdownView.this.setBackgroundDrawable(drawable);
                    break;
            }
        }
        typedArray.recycle();
    }

    public CountdownView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CountdownView, defStyle, 0);
    }

    /**
     * 开始计时
     */
    public void startCountDown() {
        timer.start();
        setViewState();
    }

    /**
     * 更改状态
     */
    private void setViewState() {
        if (isTick) {
            CountdownView.this.setEnabled(true);
            CountdownView.this.setClickable(true);
            isTick = false;
        } else {
            isTick = true;
            CountdownView.this.setEnabled(false);
            CountdownView.this.setClickable(false);
        }
    }

    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            CountdownView.this.setText("还剩" + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            CountdownView.this.setText("倒计时完成");
            setViewState();
        }
    };
}
