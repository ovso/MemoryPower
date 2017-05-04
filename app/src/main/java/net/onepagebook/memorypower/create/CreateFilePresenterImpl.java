package net.onepagebook.memorypower.create;

import android.text.TextUtils;

import net.onepagebook.memorypower.R;

/**
 * Created by ovso on 2017. 5. 4..
 */

public class CreateFilePresenterImpl implements CreateFilePresenter {

    private CreateFilePresenter.View mView;
    private CreateFileDatabase mDatabase;
    CreateFilePresenterImpl(CreateFilePresenter.View view) {
        mView = view;
        mDatabase = new CreateFileDatabase();
    }

    @Override
    public void init() {
        mView.setContentView();
        mView.setInputFilter();
        mView.addListener();
        mView.setHint();
        mView.setTitle();
    }

    @Override
    public void onClickOk(String fileName) {
        if(TextUtils.isEmpty(fileName)) {
            mView.setInputError(R.string.error_empty);
        } else {
            if (!mDatabase.isExistFileName("name", fileName)) {
                mDatabase.createFile(fileName);
                mView.setToolbarName(fileName);
                mView.dismiss();
            } else {

            }
        }
    }
}
