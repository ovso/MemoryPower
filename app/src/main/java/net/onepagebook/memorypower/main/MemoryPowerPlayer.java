package net.onepagebook.memorypower.main;

import lombok.Getter;
import lombok.Setter;

class MemoryPowerPlayer {
    @Getter
    private PlayingStatus playingStatus;
    @Setter
    private OnPlayerListener onPlayerListener;
    @Setter
    private int totalPlayCount;
    @Setter
    private int remainingPlayCount;
    @Setter
    private int displayInterval;

    private SimpleCountDownTimer countDownTimer;
    private int playIndex;
    MemoryPowerPlayer() {
        playingStatus = PlayingStatus.STOP;
    }

    void play() {
        onPlayerListener.play();
        playingStatus = PlayingStatus.PLAYING;
        int millisInFuture = (totalPlayCount * (displayInterval)) + displayInterval;

        countDownTimer = new SimpleCountDownTimer(millisInFuture, displayInterval) {

            @Override
            public void onTick(long millisUntilFinished) {
                playIndex = (int) ((millisInFuture - millisUntilFinished) / displayInterval);
                if (onPlayerListener != null) {
                    onPlayerListener.onTick(playIndex);
                    if (isLastIndex(totalPlayCount, playIndex)) {
                        onPlayerListener.onFinish(playIndex);
                        playingStatus = PlayingStatus.STOP;
                    }
                }
            }
        };
        countDownTimer.start();
    }
    void pause() {
        playingStatus = PlayingStatus.PAUSE;
        countDownTimer.cancel();
        remainingPlayCount = totalPlayCount - (playIndex + 1);
    }

    private boolean isLastIndex(int size, int index) {
        return (size - 1) == index;
    }

    public interface OnPlayerListener {
        void onTick(int index);

        void onFinish(int index);

        void play();
        void stop();
    }
}