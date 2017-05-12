package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.common.Log;

import lombok.Getter;
import lombok.Setter;

class MemoryPowerPlayer {
    @Getter
    private PlayingStatus playingStatus;
    @Setter
    private OnPlayerListener onPlayerListener;
    @Setter
    private int playCount;
    @Setter
    private int displayInterval;

    private SimpleCountDownTimer countDownTimer;
    @Getter
    private int currentIndex = 0;
    @Getter
    @Setter
    private boolean isRandom;

    MemoryPowerPlayer() {
        playingStatus = PlayingStatus.STOP;
    }

    void play() {
        if (!isPlayable()) {
            return;
        }
        if (playingStatus == PlayingStatus.PAUSE) {
            onPlayerListener.onResume();
        } else {
            onPlayerListener.onPlay();
        }
        playingStatus = PlayingStatus.PLAYING;
        SimpleCountDownTimer timer = new SimpleCountDownTimer(playCount, displayInterval) {
            @Override
            public void onTick(int index) {
                Log.d("onTick = " + index);
                currentIndex = index;
                onPlayerListener.onTick(index);
            }

            @Override
            public void onFinished() {
                Log.d("onFinished()");
                onPlayerListener.onFinished();
                stop();
            }
        };
        timer.setIndex(currentIndex);
        timer.setRandom(isRandom);
        countDownTimer = timer.start();
    }

    private boolean isPlayable() {
        if (playCount < 1) {
            onPlayerListener.onError(PlayErrorStatus.EMPTY_ITEM);
            return false;
        } else {
            return true;
        }
    }

    void pause() {
        playingStatus = PlayingStatus.PAUSE;
        countDownTimer.cancel();
        onPlayerListener.onPause();
    }

    void stop() {
        playingStatus = PlayingStatus.STOP;
        currentIndex = 0;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (onPlayerListener != null) {
            onPlayerListener.onStop();
        }
    }

    public enum PlaybackType {
        SEQUENCIAL(0), RANDOM(1);
        private int value;

        PlaybackType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum DisplayType {
        ALL(0), REMEMBER(1), NOT_REMEMBER(2);

        private int value;

        DisplayType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    interface OnPlayerListener {
        void onTick(int index);

        void onPlay();

        void onStop();

        void onResume();

        void onPause();

        void onFinished();

        void onError(PlayErrorStatus status);
    }
}