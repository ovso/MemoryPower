package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.R;
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
        mView.addListener();
    }

    @Override
    public void onNavigationItemSelected(int itemId) {
        // Handle navigation view item clicks here.
        int id = itemId;
        if (id == R.id.nav_open) {

        } else if (id == R.id.nav_file_add) {
            // Handle the camera action
        } else if (id == R.id.nav_file_add) {

        } else if (id == R.id.nav_share) {

        }

    }

    @Override
    public void onClickPlayControl(int id) {
        switch (id) {
            case R.id.play_pause_button:
                mPlayer.setDisplayInterval(500);
                mPlayer.setPlayCount(mDatabase.getCount());
                mPlayer.play();
                break;
            case R.id.stop_button:
                break;
            case R.id.memory_button:
                break;
        }
    }
}
