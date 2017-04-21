package net.onepagebook.memorypower.main;

/**
 * Created by ovso on 2017. 4. 20..
 */

public interface MainPresenter extends Presenter {

    boolean onNavigationItemSelected(int itemId);

    interface View extends Presenter.View {
        void replaceHomeFragment();
        void replaceSettingFragment();

        void addEventListener();

        void showHomeFragment();
    }
}
