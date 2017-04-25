package net.onepagebook.memorypower.main;

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
