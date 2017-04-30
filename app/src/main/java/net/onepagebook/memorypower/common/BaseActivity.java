package net.onepagebook.memorypower.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import net.onepagebook.memorypower.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ovso on 2017. 4. 30..
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnbinder;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        mUnbinder = ButterKnife.bind(this);
        mToolbar.setTitle(getToolbarTitle());
        setSupportActionBar(mToolbar);
    }

    protected abstract int getLayoutRes();
    protected abstract String getToolbarTitle();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
