package net.onepagebook.memorypower.main;

import android.text.TextUtils;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.common.Log;
import net.onepagebook.memorypower.db.KeyPoint;
import net.onepagebook.memorypower.db.KeyPointNote;

class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View mView;
    private MemoryPowerPlayer mPlayer;
    private MainDatabase mDatabase;
    private MemoryPowerPlayer.OnPlayerListener onPlayerListener = new MemoryPowerPlayer
            .OnPlayerListener() {

        @Override
        public void onTick(int index) {
            KeyPointNote note = mDatabase.getKeyPointNote(mDatabase.getNowNoteId());
            KeyPoint point = note.getKeyPoints().get(index);
            mView.setSubject(point.getSubject());
            mView.setContent(point.getContent());
        }

        @Override
        public void onFinished() {
            setEmpty();
        }

        @Override
        public void onError(PlayErrorStatus status) {
            mView.showNoticeDialog(status.getResId());
        }

        private void setEmpty() {
            mView.setSubject(null);
            mView.setContent(null);
            mView.setPlayPauseIcon(R.drawable.ic_play);
        }

        @Override
        public void onPlay() {
            mView.setPlayPauseIcon(R.drawable.ic_pause);
            mView.setSeekbarEnable(false);
        }

        @Override
        public void onStop() {
            setEmpty();
            mView.setSeekbarEnable(true);
        }

        @Override
        public void onResume() {
            mView.setPlayPauseIcon(R.drawable.ic_pause);
            mView.setSeekbarEnable(false);
        }

        @Override
        public void onPause() {
            mView.setPlayPauseIcon(R.drawable.ic_play);
            mView.setSeekbarEnable(true);
        }
    };

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mPlayer = new MemoryPowerPlayer();
        mPlayer.setOnPlayerListener(onPlayerListener);
        mDatabase = new MainDatabase();
    }


    @Override
    public void onCreate() {
        mView.addListener();
        mView.setScrollTextView();
    }

    @Override
    public void onNavigationItemSelected(int itemId) {
        switch (itemId) {
            case R.id.nav_open:
                int size = mDatabase.getKeyPointNotes().size();
                if (size > 0) mView.showOpenFileDialog(mDatabase.getNoteNames());
                else mView.showNoticeDialog(R.string.notice_empty_file);
                break;
            case R.id.nav_file_create:
                //mView.navigateToCreateFile();
                mView.showCreateFileDialog();
                break;
            case R.id.nav_item_add:
                String nowNoteId = mDatabase.getNowNoteId();
                if (!TextUtils.isEmpty(nowNoteId)) {
                    mView.showItemAddDialog(mDatabase.getNowNoteId());
                } else {
                    mView.showNoticeDialog(R.string.notice_item_add_open_or_create);
                }
                break;

            case R.id.nav_share:
                break;
        }
    }

    @Override
    public void onClickPlayControl(int id) {
        PlayingStatus status = mPlayer.getPlayingStatus();
        switch (id) {
            case R.id.play_pause_button:
                if (status == PlayingStatus.STOP || status == PlayingStatus.PAUSE) {
                    if (isPlayable()) {
                        mPlayer.setPlayCount(
                                mDatabase.getKeyPointNote(mDatabase.getNowNoteId()).getKeyPoints
                                        ().size()
                        );
                    } else {
                        onPlayerListener.onError(PlayErrorStatus.EMPTY_FILE);
                        return;
                    }

                    mPlayer.play();
                } else if (mPlayer.getPlayingStatus() == PlayingStatus.PLAYING) {
                    mPlayer.pause();
                }
                break;
            case R.id.stop_button:
                mView.setPlayPauseIcon(R.drawable.ic_play);
                mPlayer.stop();
                break;
            case R.id.memory_button:
                break;
        }
    }

    private boolean isPlayable() {
        return mDatabase.getKeyPointNote(mDatabase.getNowNoteId()) != null;
    }

    @Override
    public void onOpenFileClick(String noteId, String fileName) {
        Log.d("noteId = " + noteId + ", fileName = " + fileName);
        mDatabase.setNowNoteId(noteId);
        mView.setToolbarTitle(fileName);
    }

    @Override
    public void onStopTrackingTouch(int progress) {
        mPlayer.setDisplayInterval(progress * 1000);
    }

    @Override
    public void onCreateFileSuccess(String id) {
        mDatabase.setNowNoteId(id);
        mView.setToolbarTitle(mDatabase.getNoteName(id));
    }

}
