package net.onepagebook.memorypower.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
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
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private Unbinder mUnbinder;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate();

        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0, true);
                    break;
                case R.id.navigation_setting:
                    mViewPager.setCurrentItem(1, true);
                    break;
            }
            return false;
        });

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addItems(HomeFragment.newInstance(null));
        adapter.addItems(SettingFragment.newInstance(null));
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
