package net.onepagebook.memorypower.home;

import net.onepagebook.memorypower.common.FragmentPresenter;

/**
 * Created by ovso on 2017. 4. 24..
 */

public interface HomeFragmentPresenter extends FragmentPresenter {

    void onClickStartButton();

    interface View extends FragmentPresenter.View {

    }
}
