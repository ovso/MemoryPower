package net.onepagebook.memorypower.main;

import android.support.annotation.StringRes;

interface MainPresenter extends Presenter {

    void onNavigationItemSelected(int itemId);

    void onClickPlayControl(int id);

    void onStopTrackingTouch(int progress);

    void onCreateFileSuccess(String id);

    void onOpenFileClick(String noteId, String fileName);

    interface View extends Presenter.View {
        void setSubject(String subject);
        void setContent(String content);

        void addListener();

        void setPlayPauseIcon(int iconRes);

        void setSeekbarEnable(boolean enable);

        //void navigateToCreateFile();

        void showCreateFileDialog();

        void setToolbarTitle(String fileName);

        void showItemAddDialog(String noteId);

        void showNoticeDialog(@StringRes int resId);

        void showOpenFileDialog(String[] items);
    }
}
