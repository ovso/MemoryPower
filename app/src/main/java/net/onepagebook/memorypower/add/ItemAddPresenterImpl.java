package net.onepagebook.memorypower.add;

import android.text.TextUtils;

import net.onepagebook.memorypower.R;

public class ItemAddPresenterImpl implements ItemAddPresenter {
    private ItemAddPresenter.View mView;
    private ItemAddDatabase mDatabase;

    ItemAddPresenterImpl(ItemAddPresenter.View view) {
        mView = view;
        mDatabase = new ItemAddDatabase();
    }

    @Override
    public void init() {
        mView.setContentView();
        mView.setInputFilter();
        mView.addListener();
        mView.setTitleBar();
    }

    @Override
    public void onClickOk(String subject, String content) {
        // do something
        if (TextUtils.isEmpty(subject)) {
            mView.setInputError(R.string.error_empty);
        } else if (TextUtils.isEmpty(content)) {
            mView.setInputError2(R.string.error_empty);
        } else {
            mDatabase.add(subject, content, mDatabase.getNoteId());
            mView.dismiss();
        }
    }

    @Override
    public void setNoteId(String noteId) {
        mDatabase.setNoteId(noteId);
    }
}
