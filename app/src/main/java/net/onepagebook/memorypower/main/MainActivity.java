package net.onepagebook.memorypower.main;

import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.add.ItemAddDialogBuilder;
import net.onepagebook.memorypower.common.AbsBaseActivity;
import net.onepagebook.memorypower.create.CreateFileDialogBuilder;
import net.onepagebook.memorypower.open.OpenFileClickListener;
import net.onepagebook.memorypower.open.OpenFileDialogBuilder;

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
    @BindView(R.id.play_type_spinner)
    Spinner mPlayTypeSpinner;
    @BindView(R.id.display_type_spinner)
    Spinner mDisplayTypeSpinner;
    @BindView(R.id.remember_button)
    ImageButton mRememberButton;
    @BindView(R.id.remembering_value_textview)
    TextView mRememberingValueTextView;
    @BindView(R.id.remembering_textview)
    TextView mRememberingTextView;
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

    @Override
    public void setScrollTextView() {
        mContentTextView.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void setPlayTypeSpinner(@ArrayRes int resId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array
                .play_type_spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPlayTypeSpinner.setAdapter(adapter);
    }

    @Override
    public void setDisplayTypeSpinner(@ArrayRes int resId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array
                .display_type_spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDisplayTypeSpinner.setAdapter(adapter);

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
        mPlayTypeSpinner.setOnItemSelectedListener(new OnSimpleItemSelctedListener() {
            @Override
            public void onItemSelected(int position) {
                mPresenter.onPlayTypeSpinnerItemSelected(position);
            }
        });
        mDisplayTypeSpinner.setOnItemSelectedListener(new OnSimpleItemSelctedListener() {
            @Override
            public void onItemSelected(int position) {
                mPresenter.onDisplayTypeSpinnerItemSelected(position);
            }
        });
    }

    private DiscreteSeekBar.OnProgressChangeListener mOnSimpleProgressChangeListener() {
        return new OnSimpleProgressChangeListener() {
            @Override
            public void onStopTrackingTouch(int progress) {
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
    public void setRemembering(int resId) {
        String text = resId == 0 ? null : getString(resId);
        mRememberingValueTextView.setText(text);
        mRememberingTextView.setVisibility(
                TextUtils.isEmpty(text) ? View.INVISIBLE : View.VISIBLE
        );
    }

    @Override
    public void setSpinnerEnable(boolean enable) {
        mPlayTypeSpinner.setEnabled(enable);
        mDisplayTypeSpinner.setEnabled(enable);
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
        builder.setNoteId(noteId);
        builder.create();
        builder.showAlertDialog();
    }

    @Override
    public void showNoticeDialog(@StringRes int resId) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(resId)
                .setPositiveButton(android.R.string.ok, null)
                .create();
        dialog.setIcon(R.drawable.ic_warning);
        dialog.setTitle(" ");
        dialog.show();
    }

    @Override
    public void showOpenFileDialog(String[] items) {
        OpenFileDialogBuilder builder = new OpenFileDialogBuilder(this);
        builder.setItems(items);
        builder.setPositiveButton(new OpenFileClickListener() {
            @Override
            public void onClick(String noteId, String fileName) {
                mPresenter.onOpenFileClick(noteId, fileName);
            }
        });
        builder.show();
    }

    @OnClick(R.id.play_pause_button)
    void onClickPlayPause() {
        mPresenter.onClickPlayPause();
    }

    @OnClick(R.id.stop_button)
    void onClickStop() {
        mPresenter.onClickStop();
    }

    @OnClick(R.id.remember_button)
    void onClickRemember() {
        mPresenter.onClickRemember();
    }
}