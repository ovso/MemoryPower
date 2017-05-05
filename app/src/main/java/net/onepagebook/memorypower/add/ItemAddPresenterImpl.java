package net.onepagebook.memorypower.add;

public class ItemAddPresenterImpl implements ItemAddPresenter {
    private ItemAddPresenter.View mView;

    ItemAddPresenterImpl(ItemAddPresenter.View view) {
        mView = view;
    }

    @Override
    public void init() {
        mView.setContentView();
        mView.setInputFilter();
        mView.addListener();
        mView.setTitle();
    }

    @Override
    public void onClickOk(String subject, String content) {
        // do something
        mView.dismiss();
    }
}
