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
    MenuItem mBottomNavigationPrevMenuItem;
    private Unbinder mUnbinder;
    private MainPresenter mPresenter;

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
    public void setViewPager() {
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

    @Override
    public void setBottomNavigation(int position) {
        if (mBottomNavigationPrevMenuItem != null) {
            mBottomNavigationPrevMenuItem.setChecked(false);
        } else {
            mNavigationView.getMenu().getItem(BottomNavigationMenu.HOME.getPosition()).setChecked
                    (false);
        }
        mNavigationView.getMenu().getItem(position).setChecked(true);
        mBottomNavigationPrevMenuItem = mNavigationView.getMenu().getItem(position);
    }
}
