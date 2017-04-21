package net.onepagebook.memorypower.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.home.HomeFragment;
import net.onepagebook.memorypower.setting.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigationView;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    MenuItem prevMenuItem;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void setViewPagerAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addItem(HomeFragment.newInstance());
        adapter.addItem(SettingFragment.newInstance());
        mViewpager.setAdapter(adapter);
    }

    @Override
    public void addListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> mPresenter
                .onNavigationItemSelected(item.getItemId()));
        mViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mPresenter.onPageSelected(position);
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    mNavigationView.getMenu().getItem(0).setChecked(false);
                }
                mNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mNavigationView.getMenu().getItem(position);

            }
        });
    }

    @Override
    public void gotoHome() {
        mViewpager.setCurrentItem(BottomNavigationMenu.HOME.getPosition(), true);
    }

    @Override
    public void gotoSetting() {
        mViewpager.setCurrentItem(BottomNavigationMenu.SETTING.getPosition(), true);
    }
}
