package net.onepagebook.memorypower.open;

class OpenFilePresenterImpl implements OpenFilePresenter {

    private OpenFilePresenter.View mView;
    private OpenFileDatabase mDatabase;

    OpenFilePresenterImpl(OpenFilePresenter.View view) {
        mView = view;
        mDatabase = new OpenFileDatabase();
    }

    @Override
    public void init() {
        mView.setTitleBar();
        mView.addListener();
    }

    @Override
    public void onItemClick(int which) {
        mDatabase.setNowPosition(which);
    }

    @Override
    public void onClick(int which) {
        String fileName = mDatabase.getItems()[mDatabase.getNowPosition()]
                .toString();
        String noteId = mDatabase.getNoteId(fileName);
        mView.setNoteInfo(noteId, fileName);
    }

    @Override
    public void setItems(CharSequence[] items) {
        mDatabase.setItems(items);
    }
}
