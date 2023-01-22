package com.example.android_homework6;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        drawThread = new DrawThread(getContext(), getHolder());
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while(retry){
            try {
               drawThread.join();
               retry = false;
            }
            catch (InterruptedException e){
                //
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawThread.setTowardPoint((int) event.getX(), (int) event.getY());
        return false;
    }
}


