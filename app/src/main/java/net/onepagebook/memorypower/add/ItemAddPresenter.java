package net.onepagebook.memorypower.add;

import net.onepagebook.memorypower.common.InputDialogPresenter;

/**
 * Created by ovso on 2017. 5. 6..
 */

public interface ItemAddPresenter extends InputDialogPresenter {
    void onClickOk(String subject, String content);

    interface View extends InputDialogPresenter.View {

    }
}
