package net.onepagebook.memorypower.home;

import net.onepagebook.memorypower.common.Log;

class HomeFragmentPresenterImpl implements HomeFragmentPresenter {
    private HomeFragmentPresenter.View mView;
    private MemoryPowerPlayer mPlayer;
    private HomeFragmentDatabase mDatabase;

    HomeFragmentPresenterImpl(HomeFragmentPresenter.View view) {
        mView = view;
        mPlayer = new MemoryPowerPlayer();
        mPlayer.setOnPlayerListener(onPlayerListener());
        mDatabase = new HomeFragmentDatabase();
    }

    private MemoryPowerPlayer.OnPlayerListener onPlayerListener() {
        return new MemoryPowerPlayer
                .OnPlayerListener() {

            @Override
            public void onTick(int index) {
                Log.d("onTick(" + index + ")");
                HomeFragmentDatabase.SampleMainPointNote note = mDatabase.getItem(index);
                mView.setSubject(note.getSubject());
                mView.setContent(note.getContent());
            }

            @Override
            public void onFinish(int index) {
                Log.d("onFinish(" + index + ")");
            }

            @Override
            public void OnEndOfDisplayInterval() {
                mView.setSubject(null);
                mView.setContent(null);
            }
        };
    }

    @Override
    public void onClickStartButton() {
        int count = mDatabase.getCount();
        int displayInterval = 500;
        int blankInterval = 1000;

        mPlayer.setDisplayInterval(displayInterval);
        mPlayer.setBlankInterval(blankInterval);
        mPlayer.setPlayCount(count);
        mPlayer.play();
    }

}
