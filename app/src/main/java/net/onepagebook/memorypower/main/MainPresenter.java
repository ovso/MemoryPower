package net.onepagebook.memorypower.main;

public interface MainPresenter extends Presenter {

    void onNavigationItemSelected(int itemId);

    void onClickPlayControl(int id);

    interface View extends Presenter.View {
        void setSubject(String subject);
        void setContent(String content);

        void addListener();

        void setPlayPauseIcon(int iconRes);
    }
}
