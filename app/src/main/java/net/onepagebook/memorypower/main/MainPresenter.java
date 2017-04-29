package net.onepagebook.memorypower.main;

public interface MainPresenter extends Presenter {

    void onClickStartButton();

    interface View extends Presenter.View {
        void setSubject(String subject);
        void setContent(String content);

    }
}
