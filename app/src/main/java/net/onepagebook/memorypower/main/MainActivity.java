package net.onepagebook.memorypower.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.add.ItemAddDialogBuilder;
import net.onepagebook.memorypower.common.AbsBaseActivity;
import net.onepagebook.memorypower.create.CreateFileDialogBuilder;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AbsBaseActivity implements MainPresenter.View, NavigationView
        .OnNavigationItemSelectedListener {

    @BindView(R.id.subject_textview)
    TextView mSubjectTextView;
    @BindView(R.id.content_textview)
    TextView mContentTextView;
    @BindView(R.id.speed_seekbar)
    DiscreteSeekBar mSpeedSeekbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.play_pause_button)
    ImageButton mPlayPauseButton;
    @BindView(R.id.stop_button)
    ImageButton mStopButton;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected String getToolbarTitle() {
        return "파일명";
    }

    @Override
    public void setToolbarTitle(String fileName) {
        mToolbar.setTitle(fileName);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mPresenter.onNavigationItemSelected(item.getItemId());
        mDrawer.closeDrawer(GravityCompat.START);
        //return true;
        return false;

    }

    @Override
    public void setSubject(String subject) {
        mSubjectTextView.setText(subject);
    }

    @Override
    public void setContent(String content) {
        mContentTextView.setText(content);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void addListener() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        mSpeedSeekbar.setOnProgressChangeListener(mOnSimpleProgressChangeListener());
    }

    private DiscreteSeekBar.OnProgressChangeListener mOnSimpleProgressChangeListener() {
        return new OnSimpleProgressChangeListener() {
            @Override
            void onStopTrackingTouch(int progress) {
                mPresenter.onStopTrackingTouch(progress);
            }
        };
    }

    @Override
    public void setPlayPauseIcon(int iconRes) {
        mPlayPauseButton.setImageResource(iconRes);
    }

    @Override
    public void setSeekbarEnable(boolean enable) {
        mSpeedSeekbar.setEnabled(enable);
    }

    @Override
    public void showCreateFileDialog() {
        CreateFileDialogBuilder builder = new CreateFileDialogBuilder(this);
        builder.setOnDialogListener(id -> mPresenter.onCreateFileSuccess(id));
        builder.create();
        builder.showAlertDialog();
    }

    @Override
    public void showItemAddDialog(String noteId) {
        ItemAddDialogBuilder builder = new ItemAddDialogBuilder(this);
        builder.create();
        builder.showAlertDialog();
    }

    @OnClick({R.id.play_pause_button, R.id.stop_button, R.id.memory_button})
    void onClickPlayControl(View v) {
        mPresenter.onClickPlayControl(v.getId());
    }
}