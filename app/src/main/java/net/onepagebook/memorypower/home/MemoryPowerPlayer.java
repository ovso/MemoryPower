package net.onepagebook.memorypower.home;

import android.os.CountDownTimer;

import net.onepagebook.memorypower.common.Log;

class MemoryPowerPlayer {

    private OnPlayerListener mOnPlayerListener;

    void play(int size) {

        int millisInFuture = size * (500) + 500;


        Log.d("millisInFuture = " + millisInFuture);
        CountDownTimer timer = new CountDownTimer(millisInFuture, 500) {

            @Override
            public void onTick(long millisUntilFinished) {
                int index = (int) ((millisInFuture - millisUntilFinished) / 500);
                if (mOnPlayerListener != null) {
                    mOnPlayerListener.onTick(index);
                    if (hasLasIndex(size, index)) mOnPlayerListener.onFinish(index);
                }
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }

    private boolean hasLasIndex(int size, int index) {
        return (size - 1) == index ? true : false;
    }

    public void setOnPlayerListener(OnPlayerListener listener) {
        mOnPlayerListener = listener;
    }

    public interface OnPlayerListener {
        void onTick(int index);

        void onFinish(int index);
    }
}