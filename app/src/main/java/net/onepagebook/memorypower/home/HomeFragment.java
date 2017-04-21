package net.onepagebook.memorypower.home;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.common.BaseFragment;

public class HomeFragment extends BaseFragment {
    public static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();
        return f;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }
}
