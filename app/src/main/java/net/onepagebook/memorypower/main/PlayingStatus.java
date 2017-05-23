package net.onepagebook.memorypower.main;

import lombok.Getter;

public enum PlayingStatus {
    PLAYING(0), PAUSE(1), STOP(2);
    @Getter
    private int value;

    private PlayingStatus(int status) {
        this.value = status;
    }
}
