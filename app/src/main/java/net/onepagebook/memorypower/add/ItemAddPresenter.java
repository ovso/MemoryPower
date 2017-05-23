package net.onepagebook.memorypower.add;

import android.support.annotation.StringRes;

import net.onepagebook.memorypower.common.InputDialogPresenter;

/**
 * Created by ovso on 2017. 5. 6..
 */

public interface ItemAddPresenter extends InputDialogPresenter {
    void onClickOk(String subject, String content);

    void setNoteId(String noteId);

    interface View extends InputDialogPresenter.View {
        void setInputError2(@StringRes int resId);
    }
}
