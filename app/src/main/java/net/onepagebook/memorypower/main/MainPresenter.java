package net.onepagebook.memorypower.main;

/**
 * Created by ovso on 2017. 4. 20..
 */

public interface MainPresenter extends Presenter {

    boolean onNavigationItemSelected(int itemId);

    void onPageSelected(int position);

    interface View extends Presenter.View {
        void setViewPager();

        void addListener();

        void gotoHome();

        void gotoSetting();

        void setBottomNavigation(int position);
    }
}
