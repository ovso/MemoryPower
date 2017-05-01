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

    MemoryPowerPlayer() {
        playingStatus = PlayingStatus.STOP;
    }

    private int currentIndex = 0;
    void play() {
        if(playingStatus == PlayingStatus.PAUSE) {
            onPlayerListener.onResume();
        } else {
            onPlayerListener.onPlay();
        }
        playingStatus = PlayingStatus.PLAYING;
        countDownTimer = new SimpleCountDownTimer(playCount, displayInterval) {
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
        countDownTimer.setIndex(currentIndex);
        countDownTimer.start();
    }

    void pause() {
        playingStatus = PlayingStatus.PAUSE;
        countDownTimer.cancel();
        onPlayerListener.onPause();
    }

    void stop() {
        playingStatus = PlayingStatus.STOP;
        currentIndex = 0;
        countDownTimer.cancel();
        onPlayerListener.onStop();
    }

    interface OnPlayerListener {
        void onTick(int index);

        void onPlay();

        void onStop();

        void onResume();

        void onPause();

        void onFinished();
    }
}