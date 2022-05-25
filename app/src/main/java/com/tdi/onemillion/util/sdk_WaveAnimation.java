package com.tdi.onemillion.util;

//import com.simform.refresh.SSAnimationView;


//public class sdk_WaveAnimation extends SSAnimationView {
//
//    private float amplitude = 0f;
//
//    private float speed = 0f;
//
//    private Path path = new Path();
//
//    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//    private ValueAnimator animator = null;
//
//    private int WAVE_AMOUNT_ON_SCREEN = 350;
//    private float WAVE_SPEED = 0.3f;
//
//    private Context context;
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        canvas.drawPath(path, paint);
//
//    }
//
//    private ValueAnimator createAnimator() {
//
//
//        ValueAnimator valueAnimator = new ValueAnimator();
//
//
//        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        valueAnimator.addUpdateListener(animation -> {
//            speed -= WAVE_SPEED;
//            createPath();
//            invalidate();
//        });
//
//        return valueAnimator.ofFloat(0f, Float.MAX_VALUE);
//
//    }
//
//    private void createPath() {
//        path.reset();
//        paint.setColor(Color.parseColor("#203354"));
//        path.moveTo(0f, getHeight());
//        path.lineTo(0f, amplitude);
//        path.lineTo(0f, amplitude - 10);
//
//        int i = 0;
//        while (i < getWidth() + 10) {
//            float wx = i;
//            float wy = (float) (amplitude * 2 + amplitude * sin((i + 10) * Math.PI / WAVE_AMOUNT_ON_SCREEN + speed));
//
//            path.lineTo(wx, wy);
//            path.close();
//        }
//
//
//    }
//
//
//    //px을 dp로 변환 (px을 입력받아 dp를 리턴)
//    public float dp(float px) {
//        assert context != null;
//        Resources resources = context.getResources();
//        DisplayMetrics metrics = resources.getDisplayMetrics();
//        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
//        return dp;
//    }
//
//
//    public sdk_WaveAnimation(Context context) {
//        super(context);
//        this.context = context;
//
//        amplitude = dp(22f);
//    }
//
//    @Override
//    public void pullProgress(float v, float v1) {
//
//    }
//
//    @Override
//    public void pullToRefresh() {
//        animator = createAnimator();
//        animator.start();
//    }
//
//    @Override
//    public void refreshComplete() {
//        if (animator != null) {
//            animator.cancel();
//            animator = null;
//        }
//    }
//
//    @Override
//    public void refreshing() {
//
//    }
//
//    @Override
//    public void releaseToRefresh() {
//
//    }
//
//    @Override
//    public void reset() {
//
//    }
//
//
//}
