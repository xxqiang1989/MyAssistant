# BreathView
  位于wear/com/xxqiang/myassistant/view/路径下。  
  仿 Apple Watch 上的Breath应用，可以直接编译在运行 Android Wear 的手表上。
  高度可以定制化，包括颜色、初始半径、结束半径、圆圈数量、初始透明度、结束透明度、周期时间等。

  由于时间关系（主要是懒），动画的时间还没完全调好，有兴趣的可以修改下BreathView：
  ```
  public void startAnim() {
        if (animator == null) {
            animator = ValueAnimator.ofInt(0, 100, 100, 0);
            animator.setDuration(mDurationIn + mDurationOut);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentPercent = (int) (animation.getAnimatedValue()) / 100f;
                    invalidate();
                }
            });
        }
        animator.start();
    }
    ```
  
  项目比较简单，就不再多做介绍。
