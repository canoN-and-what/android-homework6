package com.example.android_homework6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {

    private final SurfaceHolder surfaceHolder;

    private volatile boolean running = true;
    private final Paint backgroundPaint = new Paint();
    private final Paint circlePaint = new Paint();
    private int towardPointX;
    private int towardPointY;
    {
        backgroundPaint.setColor(Color.BLUE);
        backgroundPaint.setStyle(Paint.Style.FILL);

        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        float radius = 20;
        float x = 0;
        float y = 0;
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawColor(backgroundPaint.getColor());
                    canvas.drawCircle(x, y, radius, circlePaint);

                    if(x + canvas.getWidth() / 2f < towardPointX) x+=20f;
                    if(x + canvas.getWidth() / 2f > towardPointX) x-=20f;
                    if(y + canvas.getHeight() / 2f < towardPointY) y+=20f;
                    if(y + canvas.getHeight() / 2f > towardPointY) y-=20f;

                }
                finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void requestStop() {
        running = false;
    }

    public void setTowardPoint(int x, int y){
        towardPointX = x;
        towardPointY = y;
    }
}
