package net.onepagebook.memorypower.setting;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.common.BaseFragment;

public class SettingFragment extends BaseFragment {
    public static SettingFragment newInstance() {
        SettingFragment f = new SettingFragment();
        return f;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_setting;
    }
}
