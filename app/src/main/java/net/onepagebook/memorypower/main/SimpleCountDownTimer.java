package net.onepagebook.memorypower.main;

import android.os.Handler;
import android.os.Message;

import lombok.Setter;

public abstract class SimpleCountDownTimer {

    private final static int MSG = 1;
    private int countDown;
    private long countDownInterval;
    private boolean mCancelled = false;
    @Setter
    private int index = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            synchronized (SimpleCountDownTimer.this) {
                if (mCancelled) {
                    return;
                }
                if ((countDown - 1) >= index) {
                    onTick(index);
                } else if (countDown == index) {
                    onFinished();
                    cancel();
                }

                index++;

                sendMessageDelayed(Message.obtain(), countDownInterval);
            }
        }
    };

    public SimpleCountDownTimer(int countDown, long countDownInterval) {
        this.countDown = countDown;
        this.countDownInterval = countDownInterval;
    }

    public synchronized final void start() {
        mCancelled = false;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
    }

    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    public abstract void onTick(int index);

    public abstract void onFinished();
}
