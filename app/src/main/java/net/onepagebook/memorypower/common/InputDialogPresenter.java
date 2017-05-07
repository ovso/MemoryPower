package net.onepagebook.memorypower.common;

import android.support.annotation.StringRes;

public interface InputDialogPresenter {
    void init();

    interface View {
        void setContentView();

        void setInputFilter();

        void addListener();

        void setTitleBar();

        void setInputError(@StringRes int resId);

        void dismiss();

    }
}
