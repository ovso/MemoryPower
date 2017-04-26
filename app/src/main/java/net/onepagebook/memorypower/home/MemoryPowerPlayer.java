package net.onepagebook.memorypower.home;

import android.os.Handler;

import net.onepagebook.memorypower.common.Log;

import lombok.Setter;

class MemoryPowerPlayer {

    @Setter
    private OnPlayerListener onPlayerListener;
    @Setter
    private int playCount;
    @Setter
    private int displayInterval;
    @Setter
    private int blankInterval;

    void play() {
        int cycleInterval = displayInterval + blankInterval;
        int millisInFuture = (playCount * (cycleInterval)) + cycleInterval;


        Log.d("millisInFuture = " + millisInFuture);
        SimpleCountDownTimer timer = new SimpleCountDownTimer(millisInFuture, cycleInterval) {

            @Override
            public void onTick(long millisUntilFinished) {
                int index = (int) ((millisInFuture - millisUntilFinished) / cycleInterval);
                if (onPlayerListener != null) {
                    onPlayerListener.onTick(index);
                    if (isLasIndex(playCount, index)) onPlayerListener.onFinish(index);
                    new Handler().postDelayed(() -> onPlayerListener.OnEndOfDisplayInterval(),
                            displayInterval);
                }
            }
        };
        timer.start();
    }

    private boolean isLasIndex(int size, int index) {
        return (size - 1) == index ? true : false;
    }

    public interface OnPlayerListener {
        void onTick(int index);

        void onFinish(int index);

        void OnEndOfDisplayInterval();
    }
}