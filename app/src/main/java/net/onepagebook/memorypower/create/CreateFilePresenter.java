package net.onepagebook.memorypower.create;

import net.onepagebook.memorypower.common.InputDialogPresenter;

public interface CreateFilePresenter extends InputDialogPresenter {

    void onClickOk(String fileName);

    interface View extends InputDialogPresenter.View {


        void setId(String noteId);


    }
}
