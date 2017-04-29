package net.onepagebook.memorypower.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.common.BaseFragment;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeFragmentPresenter.View {

    @BindView(R.id.subject_textview)
    TextView mSubjectTextView;
    @BindView(R.id.content_textview)
    TextView mContentTextView;
    @BindView(R.id.speed_seekbar)
    DiscreteSeekBar mSpeedSeekbar;

    private HomeFragmentPresenter mHomeFragmentPresenter;

    public static HomeFragment newInstance() {
        HomeFragment f = new HomeFragment();
        return f;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomeFragmentPresenter = new HomeFragmentPresenterImpl(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @OnClick(R.id.start_button)
    void onClickStartButton() {
        mHomeFragmentPresenter.onClickStartButton();
    }

    @Override
    public void setSubject(String subject) {
        mSubjectTextView.setText(subject);
    }

    @Override
    public void setContent(String content) {
        mContentTextView.setText(content);
    }
}
