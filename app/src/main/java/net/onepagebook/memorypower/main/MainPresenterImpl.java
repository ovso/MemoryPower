package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.common.Log;

public class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View mView;
    private MemoryPowerPlayer mPlayer;
    private MainDatabase mDatabase;

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mPlayer = new MemoryPowerPlayer();
        mPlayer.setOnPlayerListener(onPlayerListener());
        mDatabase = new MainDatabase();
    }

    private MemoryPowerPlayer.OnPlayerListener onPlayerListener() {
        return new MemoryPowerPlayer
                .OnPlayerListener() {

            @Override
            public void onTick(int index) {
                Log.d("onTick(" + index + ")");
                MainDatabase.SampleMainPointNote note = mDatabase.getItem(index);
                mView.setSubject(note.getSubject());
                mView.setContent(note.getContent());
            }

            @Override
            public void onFinish(int index) {
                Log.d("onFinish(" + index + ")");
            }
        };
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onClickStartButton() {
        mPlayer.setDisplayInterval(500);
        mPlayer.setPlayCount(mDatabase.getCount());
        mPlayer.play();
    }
}
