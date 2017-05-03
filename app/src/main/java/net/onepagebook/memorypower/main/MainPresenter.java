package net.onepagebook.memorypower.main;

public interface MainPresenter extends Presenter {

    void onNavigationItemSelected(int itemId);

    void onClickPlayControl(int id);

    void onStopTrackingTouch(int progress);

    interface View extends Presenter.View {
        void setSubject(String subject);
        void setContent(String content);

        void addListener();

        void setPlayPauseIcon(int iconRes);

        void setSeekbarEnable(boolean enable);

        void navigateToCreateFile();

        void showCreateFileDialog();
    }
}
