package net.onepagebook.memorypower.create;

import android.support.annotation.StringRes;

public interface CreateFilePresenter {

    void init();

    void onClickOk(String fileName);

    interface View {

        void setContentView();

        void setInputFilter();

        void addListener();

        void setHint();

        void setTitle();

        void setInputError(@StringRes int resId);

        void dismiss();

        void setId(String noteId);
    }
}
