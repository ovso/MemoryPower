package net.onepagebook.memorypower.main;

import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

interface MainPresenter extends Presenter {

    void onNavigationItemSelected(int itemId);

    void onStopTrackingTouch(int progress);

    void onCreateFileSuccess(String id);

    void onOpenFileClick(String noteId, String fileName);

    void onClickPlayPause();

    void onClickStop();

    void onClickRemember();

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

        void setScrollTextView();

        void setPlayTypeSpinner(@ArrayRes int resId);
        void setDisplayTypeSpinner(@ArrayRes int resId);
    }
}
