package com.xxqiang.myassistant;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;

import com.xxqiang.myassistant.view.BreathView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends WearableActivity implements View.OnClickListener {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private static final int MESSAGE_COUNT = 1;
    private static final int DELAY_COUNT = 1000;

    private BoxInsetLayout mContainerView;
    private BreathView breathView;
//    private ImageButton ibIcon;
//    private TextView mTextView;
//    private TextView mClockView;

//    private AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
//        ibIcon = (ImageButton) findViewById(R.id.ib_icon);
//        ibIcon.setOnClickListener(this);
//        mTextView = (TextView) findViewById(R.id.text);
//        mClockView = (TextView) findViewById(R.id.clock);
        breathView = (BreathView) findViewById(R.id.bv_breath);
        breathView.startAnim();
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
//        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
//        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
//        updateDisplay();
        super.onExitAmbient();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        breathView.stopAnim();
//        stopAnim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.ib_icon:
//                if (animatorSet.isRunning()) {
//                    stopAnim();
//                } else {
//                    startAnim();
//                }
//                break;
        }
    }

//    private void startAnim() {
//        ObjectAnimator animatorX1 = ObjectAnimator.ofFloat(ibIcon, "scaleX", 1f, 4f);
//        animatorX1.setDuration(3000);
//
//        ObjectAnimator animatorX2 = ObjectAnimator.ofFloat(ibIcon, "scaleX", 4f, 1f);
//        animatorX2.setDuration(5000);
//
//        ObjectAnimator animatorY1 = ObjectAnimator.ofFloat(ibIcon, "scaleY", 1f, 4f);
//        animatorY1.setDuration(3000);
//
//        ObjectAnimator animatorY2 = ObjectAnimator.ofFloat(ibIcon, "scaleY", 4f, 1f);
//        animatorY2.setDuration(5000);
//
//        AnimatorSet animatorSetX = new AnimatorSet();
//        animatorSetX.playSequentially(animatorX1, animatorX2);
//        AnimatorSet animatorSetY = new AnimatorSet();
//        animatorSetY.playSequentially(animatorY1, animatorY2);
//
//        animatorY2.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                animatorSet.start();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//
//        animatorSet.playTogether(animatorSetX, animatorSetY);
//        animatorSet.start();
//    }
//
//    private void stopAnim() {
//        animatorSet.cancel();
//    }

//    private void updateDisplay() {
//        if (isAmbient()) {
//            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
//            mTextView.setTextColor(getResources().getColor(android.R.color.white));
//            mClockView.setVisibility(View.VISIBLE);
//
//            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
//        } else {
//            mContainerView.setBackground(null);
//            mTextView.setTextColor(getResources().getColor(android.R.color.black));
//            mClockView.setVisibility(View.GONE);
//        }
//    }
}
