package net.onepagebook.memorypower.home;

import net.onepagebook.memorypower.common.FragmentPresenter;

public interface HomeFragmentPresenter extends FragmentPresenter {

    void onClickStartButton();

    interface View extends FragmentPresenter.View {
        void setSubject(String subject);
        void setContent(String content);
    }
}
