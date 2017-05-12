package net.onepagebook.memorypower.main;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.Collections;

import lombok.Setter;

abstract class SimpleCountDownTimer {

    private final static int MSG = 1;
    private int countDown;
    private long countDownInterval;
    private boolean mCancelled = false;
    @Setter
    private int index = 0;
    @Setter
    private boolean isRandom;
    private ArrayList<Integer> indexList = new ArrayList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            synchronized (SimpleCountDownTimer.this) {
                if (mCancelled) {
                    return;
                }
                if ((countDown - 1) >= index) {
                    onTick(indexList.get(index));
                } else if (countDown == index) {
                    onFinished();
                    cancel();
                }

                index++;

                sendMessageDelayed(Message.obtain(), countDownInterval);
            }
        }
    };

    SimpleCountDownTimer(int countDown, long countDownInterval) {
        this.countDown = countDown;
        this.countDownInterval = countDownInterval;
    }

    synchronized final SimpleCountDownTimer start() {
        mCancelled = false;
        initIndexList();
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }

    private void initIndexList() {
        for (int i = 0; i < countDown; i++) {
            indexList.add(i);
        }
        if(isRandom) {
            Collections.shuffle(indexList);
        }
    }

    synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    abstract void onTick(int index);

    abstract void onFinished();
}
