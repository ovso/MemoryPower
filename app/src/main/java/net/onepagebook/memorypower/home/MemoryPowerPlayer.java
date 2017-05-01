package net.onepagebook.memorypower.home;

import lombok.Setter;

class MemoryPowerPlayer {

    @Setter
    private OnPlayerListener onPlayerListener;
    @Setter
    private int playCount;
    @Setter
    private int displayInterval;

    void play() {
        int millisInFuture = (playCount * (displayInterval)) + displayInterval;

/*
        SimpleCountDownTimer timer = new SimpleCountDownTimer(millisInFuture, displayInterval) {

            @Override
            public void onTick(long millisUntilFinished) {
                int index = (int) ((millisInFuture - millisUntilFinished) / displayInterval);
                if (onPlayerListener != null) {
                    onPlayerListener.onTick(index);
                    if (isLastIndex(playCount, index)) onPlayerListener.onFinish(index);
                }
            }
        };
        timer.start();
*/
    }

    private boolean isLastIndex(int size, int index) {
        return (size - 1) == index;
    }

    public interface OnPlayerListener {
        void onTick(int index);

        void onFinish(int index);
    }
}