package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.R;

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
        mView.addEventListener();
        mView.showHomeFragment();
    }

    @Override
    public boolean onNavigationItemSelected(int itemId) {
        switch (itemId) {
            case R.id.navigation_home :
                mView.replaceHomeFragment();
                break;
            case R.id.navigation_setting :
                mView.replaceSettingFragment();
                break;
        }
        return true;
    }
}
