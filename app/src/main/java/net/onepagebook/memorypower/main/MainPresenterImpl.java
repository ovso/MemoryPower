package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.R;

public class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View mView;

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        mView.addListener();
        mView.setViewPagerAdapter();
    }

    @Override
    public boolean onNavigationItemSelected(int itemId) {
        switch (itemId) {
            case R.id.navigation_home:
                mView.gotoHome();
                break;
            case R.id.navigation_setting:
                mView.gotoSetting();
                break;
        }
        return true;
    }

    @Override
    public void onPageSelected(int position) {
        BottomNavigationMenu type = BottomNavigationMenu.values()[position];
        switch (type) {
            case HOME:

                break;
            case SETTING:
                break;
        }
    }
}
