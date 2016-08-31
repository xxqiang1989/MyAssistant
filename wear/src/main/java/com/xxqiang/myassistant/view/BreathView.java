package com.xxqiang.myassistant.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.xxqiang.myassistant.R;

/**
 * 自定义的呼吸view
 */
public class BreathView extends View implements View.OnClickListener {
    private static final int DEFAULT_COLOR = Color.BLUE;
    private static final float START_RADIUS = 10;
    private static final float END_RADIUS = 100;
    private static final int START_ALPHA = 100;
    private static final int END_ALPHA = 40;
    private static final int ROUND_COUNT = 4;
    private static final int DURATION = 3000;
    private int mRoundColor;
    private float mStartRadius, mEndRadius;
    private int mRoundCount;
    private int mStartAlpha, mEndAlpha;
    //    private float mCurrentRadius = START_RADIUS;
//    private int mCurrentAlpha = START_ALPHA;
    private float mCurrentPercent = 0;
    private int mDurationIn, mDurationOut;

    private Paint paint;
    private ValueAnimator animator;

    public BreathView(Context context) {
        super(context);
        init(null, 0);
    }

    public BreathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BreathView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.BreathView, defStyle, 0);

        mRoundColor = a.getColor(
                R.styleable.BreathView_roundColor,
                DEFAULT_COLOR);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mStartRadius = a.getDimension(
                R.styleable.BreathView_startRadius,
                START_RADIUS);
        mEndRadius = a.getDimension(
                R.styleable.BreathView_endRadius,
                END_RADIUS);
        mRoundCount = a.getInteger(
                R.styleable.BreathView_roundCount,
                ROUND_COUNT);
        mStartAlpha = a.getInteger(
                R.styleable.BreathView_startAlpha,
                START_ALPHA);
        mEndAlpha = a.getInteger(
                R.styleable.BreathView_endAlpha,
                END_ALPHA);
        mCurrentPercent = a.getFloat(
                R.styleable.BreathView_percent,
                0);
        mDurationIn = a.getInteger(
                R.styleable.BreathView_durationIn,
                DURATION);
        mDurationOut = a.getInteger(
                R.styleable.BreathView_durationOut,
                DURATION);

        a.recycle();

        paint = new Paint();
        paint.setColor(mRoundColor);
        paint.setAntiAlias(true);

        setOnClickListener(this);
    }

    /**
     * @see View#measure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wh = measureWidth(widthMeasureSpec);
        setMeasuredDimension(wh, wh);
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = (int) (mEndRadius * 4
                    + getPaddingLeft()
                    + getPaddingRight());

            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(90 * mCurrentPercent);
        drawRound(canvas);
    }

    private void drawRound(Canvas canvas) {
        float radius = mStartRadius + (mEndRadius - mStartRadius) * mCurrentPercent;
        int alpha = (int) (mStartAlpha + (mEndAlpha - mStartAlpha) * mCurrentPercent);
        for (int i = 0; i < mRoundCount; i++) {
            float angle = 360f / mRoundCount * i;
            int x, y;
            if (angle <= 90) {
                x = (int) (mEndRadius * mCurrentPercent * Math.sin(angle * Math.PI / 180));
                y = (int) (mEndRadius * mCurrentPercent * Math.cos(angle * Math.PI / 180));
            } else if (angle <= 180) {
                x = (int) (mEndRadius * mCurrentPercent * Math.sin((180 - angle) * Math.PI / 180));
                y = -(int) (mEndRadius * mCurrentPercent * Math.cos((180 - angle) * Math.PI / 180));
            } else if (angle <= 270) {
                x = -(int) (mEndRadius * mCurrentPercent * Math.sin((angle - 180) * Math.PI / 180));
                y = -(int) (mEndRadius * mCurrentPercent * Math.cos((angle - 180) * Math.PI / 180));
            } else {
                x = -(int) (mEndRadius * mCurrentPercent * Math.sin((360 - angle) * Math.PI / 180));
                y = (int) (mEndRadius * mCurrentPercent * Math.cos((360 - angle) * Math.PI / 180));
            }
            paint.setAlpha(alpha);
            canvas.drawCircle(x, y, radius, paint);
        }
    }

    public void startAnim() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 100, 100, 0);
            animator.setDuration(mDurationIn + mDurationOut);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.setRepeatCount(ValueAnimator.INFINITE);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPercent = (int) (animation.getAnimatedValue()) / 100f;
                invalidate();
            }
        });
        animator.start();
    }

    public void stopAnim() {
        if (animator != null) {
            animator.end();
            animator.cancel();
        }
    }

    public float getmEndAlpha() {
        return mEndAlpha;
    }

    public float getmEndRadius() {
        return mEndRadius;
    }

    public int getmRoundColor() {
        return mRoundColor;
    }

    public int getmRoundCount() {
        return mRoundCount;
    }

    public float getmStartAlpha() {
        return mStartAlpha;
    }

    public float getmStartRadius() {
        return mStartRadius;
    }

    public void setmEndRadius(float mEndRadius) {
        this.mEndRadius = mEndRadius;
    }

    public void setmRoundColor(int mRoundColor) {
        this.mRoundColor = mRoundColor;
    }

    public void setmRoundCount(int mRoundCount) {
        this.mRoundCount = mRoundCount;
    }

    public void setmStartRadius(float mStartRadius) {
        this.mStartRadius = mStartRadius;
    }

    @Override
    public void onClick(View v) {
        if (animator != null && animator.isRunning()) {
            stopAnim();
        } else {
            startAnim();
        }
    }
}
