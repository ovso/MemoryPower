package net.onepagebook.memorypower.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.home.HomeFragment;
import net.onepagebook.memorypower.setting.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigationView;

    private Unbinder mUnbinder;
    private MainPresenter mPresenter;
    private HomeFragment mHomeFragment;
    private SettingFragment mSettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate();

    }

    private HomeFragment getHomeFragment() {
        if (mHomeFragment == null) {
            mHomeFragment = HomeFragment.newInstance();
        }
        return mHomeFragment;
    }

    private SettingFragment getSettingFragment() {
        if (mSettingFragment == null) {
            mSettingFragment = SettingFragment.newInstance();
        }
        return mSettingFragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void replaceHomeFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, getHomeFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void replaceSettingFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, getSettingFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void addEventListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> mPresenter
                .onNavigationItemSelected(item.getItemId()));
    }

    @Override
    public void showHomeFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, getHomeFragment());
        fragmentTransaction.commit();
    }
}
