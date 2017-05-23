package net.onepagebook.memorypower.open;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import net.onepagebook.memorypower.R;

import lombok.Setter;

public class OpenFileDialogBuilder extends AlertDialog.Builder implements OpenFilePresenter.View {
    OpenFilePresenter mPresenter;
    private AlertDialog dialog;
    @Setter
    private OpenFileClickListener positiveButton;

    public OpenFileDialogBuilder(@NonNull Context context) {
        super(context);
        mPresenter = new OpenFilePresenterImpl(this);
        mPresenter.init();
    }

    public AlertDialog.Builder setItems(CharSequence[] items) {
        mPresenter.setItems(items);
        return super.setSingleChoiceItems(items, 0, (dialog1, which) -> mPresenter.onItemClick(which));
    }

    @Override
    public void setTitleBar() {
        setTitle(R.string.file_open);
        setIcon(R.drawable.ic_menu_open);
    }

    @Override
    public void addListener() {
        //Do something
        setNegativeButton(android.R.string.cancel, null);
        setPositiveButton(android.R.string.ok, (dialog1, which) -> {
            mPresenter.onClick(which);
        });
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public void setNoteInfo(String noteId, String fileName) {
        positiveButton.onClick(noteId, fileName);
    }

    @Override
    public AlertDialog show() {
        dialog = super.show();
        return dialog;
    }
}
