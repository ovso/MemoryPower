package net.onepagebook.memorypower.main;

/**
 * Created by ovso on 2017. 4. 20..
 */

public class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View mView;
    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {

    }
}
