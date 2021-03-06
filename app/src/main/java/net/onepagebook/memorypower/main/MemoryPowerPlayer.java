package net.onepagebook.memorypower.main;

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
    @Getter
    private int displayInterval;

    private SimpleCountDownTimer countDownTimer;
    @Getter
    @Setter
    private boolean isRandom;
    private SkipNextContDown skipNextContDown;
    @Setter
    @Getter
    private Mode mode = Mode.GENERAL;

    MemoryPowerPlayer() {
        playingStatus = PlayingStatus.STOP;
    }

    void play() {
        if (!isPlayable()) {
            return;
        }
        onPlayerListener.onPlay();

        playingStatus = PlayingStatus.PLAYING;
        SimpleCountDownTimer timer = new SimpleCountDownTimer(playCount, displayInterval) {
            @Override
            public void onTick(int index) {
                onPlayerListener.onTick(index);
            }

            @Override
            public void onFinished() {
                onPlayerListener.onFinished();
                MemoryPowerPlayer.this.stop();
            }
        };
        timer.setRandom(isRandom);
        countDownTimer = timer.start();
    }

    void skipNext() {
        if (!isPlayable()) {
            return;
        }
        onPlayerListener.onSkipNext();
        if (skipNextContDown == null) {
            skipNextContDown = new SkipNextContDown() {
                @Override
                void onTick(int index) {
                    onPlayerListener.onTick(index);
                    playingStatus = PlayingStatus.STOP;
                }

                @Override
                void onFinished() {
                    //onPlayerListener.onFinished();
                    onPlayerListener.onStop();
                    playingStatus = PlayingStatus.STOP;
                }
            };
        }
        skipNextContDown.setPlayCount(playCount);
        skipNextContDown.setRandom(isRandom);
        skipNextContDown.next();
    }

    void resume() {
        playingStatus = PlayingStatus.PLAYING;
        countDownTimer.resume();
        onPlayerListener.onResume();
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
        countDownTimer.decrementIndex();
    }

    void stop() {
        playingStatus = PlayingStatus.STOP;
        if (onPlayerListener != null) {
            onPlayerListener.onStop();
        }
        switch (mode) {
            case GENERAL:
                if (countDownTimer != null) {
                    countDownTimer.stop();
                }
                break;
            case SKIP_NEXT:
                if (skipNextContDown != null) {
                    skipNextContDown.stop();
                }
                break;
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

    public enum Mode {
        GENERAL(0), SKIP_NEXT(1);
        private int value;

        Mode(int value) {
            this.value = value;
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

        void onSkipNext();
    }
}