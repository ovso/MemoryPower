package net.onepagebook.memorypower.main;

import android.support.annotation.StringRes;

import net.onepagebook.memorypower.R;

public enum PlayErrorStatus {
    EMPTY_FILE(R.string.notice_play_open_or_create),
    EMPTY_ITEM(R.string.notice_play_item_add);

    private int resId;

    PlayErrorStatus(@StringRes int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }
}
