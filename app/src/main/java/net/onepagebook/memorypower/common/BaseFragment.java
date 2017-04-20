package net.onepagebook.memorypower.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    public static Fragment newInstance(Bundle bundle) {
        Fragment f = new Fragment();
        f.setArguments(bundle);
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    private Unbinder mUnbinder;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mUnbinder.unbind();
    }

    @LayoutRes
    protected abstract int getLayoutRes();
}
