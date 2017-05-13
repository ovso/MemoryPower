package net.onepagebook.memorypower.main;

import android.text.TextUtils;

import net.onepagebook.memorypower.R;
import net.onepagebook.memorypower.common.Log;
import net.onepagebook.memorypower.common.ObjectUtils;
import net.onepagebook.memorypower.db.KeyPoint;
import net.onepagebook.memorypower.db.SampleKeyPointNote;

import java.util.List;

class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View mView;
    private MemoryPowerPlayer mPlayer;
    private MainDatabase mDatabase;
    private MemoryPowerPlayer.OnPlayerListener onPlayerListener = new MemoryPowerPlayer
            .OnPlayerListener() {

        @Override
        public void onPlay() {
            mDatabase.setPlayKeyPointList(mDatabase.getKeyPointList(mDatabase.getNowNoteId(),
                    mDatabase.getDisplayType()));

            mView.setPlayPauseIcon(R.drawable.ic_pause);
            mView.setSeekbarEnable(false);
            mView.setSpinnerEnable(false);
        }

        @Override
        public void onSkipNext() {
            mDatabase.setPlayKeyPointList(mDatabase.getKeyPointList(mDatabase.getNowNoteId(),
                    mDatabase.getDisplayType()));

            mView.setPlayPauseIcon(R.drawable.ic_skip_next_black_24dp);
            mView.setSeekbarEnable(false);
            mView.setSpinnerEnable(false);
        }

        @Override
        public void onTick(int index) {
            Log.d("onTick(" + index + ")");
            KeyPoint point = mDatabase.getPlayKeyPointList().get(index);
            mDatabase.setPlayKeyPoint(point);
            mView.setSubject(point.getSubject());
            mView.setContent(point.getContent());
            int resId = point.isRemember() ? R.string.remember_complete : R.string
                    .remember_not_complete;
            mView.setRemembering(resId);
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
            mView.setRemembering(0);
        }

        @Override
        public void onStop() {
            setEmpty();
            mView.setSeekbarEnable(true);
            mView.setSpinnerEnable(true);
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
        mView.setPlayTypeSpinner(R.array.play_type_spinner_items);
        mView.setDisplayTypeSpinner(R.array.display_type_spinner_items);
        mView.setRemembering(0);
        mView.setSpinnerEnable(false);
        mPlayer.setDisplayInterval(1000);

        if (mDatabase.isFirstRun()) {
            createSampleFile();
        }
    }

    private void createSampleFile() {
        List<SampleKeyPointNote> notes = mDatabase.loadSampleNote();
        if (!ObjectUtils.isEmpty(notes)) {
            int size = notes.size();
            for (int i = 0; i < size; i++) {
                SampleKeyPointNote note = notes.get(i);
                String noteId = mDatabase.createFile(note.getName());
                List<SampleKeyPointNote.KeyPoint> itemList = note.getList();
                if (!ObjectUtils.isEmpty(itemList)) {
                    for (int j = 0; j < itemList.size(); j++) {
                        SampleKeyPointNote.KeyPoint point = itemList.get(j);
                        mDatabase.add(point.getSubject(), point.getContent(), noteId);
                    }
                }
            }
            mDatabase.setFirstRun(false);
        }
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
                mView.showNoticeDialog(R.string.comming_soon);
                break;
            case R.id.nav_setting:
                mView.showNoticeDialog(R.string.comming_soon);
                break;
        }
    }

    @Override
    public void onClickPlayPause() {
        PlayingStatus status = mPlayer.getPlayingStatus();
        Log.d("status = " + status.toString());
        switch (status) {

            case PLAYING:
                mPlayer.pause();
                break;
            case PAUSE:
                mPlayer.resume();
                break;
            case STOP:
                if (!isPlayable()) {
                    onPlayerListener.onError(PlayErrorStatus.EMPTY_FILE);
                    return;
                }
                String nowNoteId = mDatabase.getNowNoteId();
                MemoryPowerPlayer.DisplayType displayType = mDatabase.getDisplayType();
                int count = mDatabase.getKeyPointList(nowNoteId, displayType).size();
                mPlayer.setPlayCount(count);

                if (mPlayer.getMode() == MemoryPowerPlayer.Mode.GENERAL) {
                    mPlayer.play();
                } else {
                    mPlayer.skipNext();
                }
                break;
        }

    }

    @Override
    public void onClickStop() {
        mView.setPlayPauseIcon(R.drawable.ic_play);
        mPlayer.stop();
    }

    @Override
    public void onClickRemember() {
        if (mPlayer.getPlayingStatus() == PlayingStatus.PLAYING) {
            KeyPoint keyPoint = mDatabase.getPlayKeyPoint();
            if (!ObjectUtils.isEmpty(keyPoint)) {
                boolean isRemember = mDatabase.setKeyPointRemember(keyPoint);
                int resId = isRemember ? R.string.remember_complete : R.string
                        .remember_not_complete;
                mView.setRemembering(resId);
            } else {
                mView.showNoticeDialog(R.string.notice_no_items_can_be_memorized);
            }
        } else {
            if (mPlayer.getMode() == MemoryPowerPlayer.Mode.SKIP_NEXT) {
                KeyPoint keyPoint = mDatabase.getPlayKeyPoint();
                if (!ObjectUtils.isEmpty(keyPoint)) {
                    boolean isRemember = mDatabase.setKeyPointRemember(keyPoint);
                    int resId = isRemember ? R.string.remember_complete : R.string
                            .remember_not_complete;
                    mView.setRemembering(resId);
                } else {
                    mView.showNoticeDialog(R.string.notice_no_items_can_be_memorized);
                }
            } else {
                mView.showNoticeDialog(R.string.notice_no_items_can_be_memorized);
            }
        }
    }

    @Override
    public void onPlayTypeSpinnerItemSelected(int position) {
        mPlayer.setRandom(MemoryPowerPlayer.PlaybackType.RANDOM.getValue() == position);
    }

    @Override
    public void onDisplayTypeSpinnerItemSelected(int position) {
        MemoryPowerPlayer.DisplayType type;
        if (MemoryPowerPlayer.DisplayType.ALL.getValue() == position) {
            type = MemoryPowerPlayer.DisplayType.ALL;
        } else if (MemoryPowerPlayer.DisplayType.REMEMBER.getValue() == position) {
            type = MemoryPowerPlayer.DisplayType.REMEMBER;
        } else {
            type = MemoryPowerPlayer.DisplayType.NOT_REMEMBER;
        }
        mDatabase.setDisplayType(type);
    }

    private boolean isPlayable() {
        return mDatabase.getKeyPointNote(mDatabase.getNowNoteId()) != null;
    }

    @Override
    public void onOpenFileClick(String noteId, String fileName) {
        Log.d("noteId = " + noteId + ", fileName = " + fileName);
        mDatabase.setNowNoteId(noteId);
        mView.setToolbarTitle(fileName);
        if (mDatabase.getKeyPointNote(noteId).getKeyPoints().size() > 0) {
            mView.setSpinnerEnable(true);
        } else {
            mView.setSpinnerEnable(false);
        }
    }

    @Override
    public void onStopTrackingTouch(int progress) {
        int interval = progress * 1000;
        mPlayer.setDisplayInterval(interval);
        if (interval == 0) {
            mPlayer.setMode(MemoryPowerPlayer.Mode.SKIP_NEXT);
        } else {
            mPlayer.setMode(MemoryPowerPlayer.Mode.GENERAL);
        }
    }

    @Override
    public void onCreateFileSuccess(String id) {
        mDatabase.setNowNoteId(id);
        mView.setToolbarTitle(mDatabase.getNoteName(id));
        if (mDatabase.getKeyPointNote(id).getKeyPoints().size() > 0) {
            mView.setSpinnerEnable(true);
        } else {
            mView.setSpinnerEnable(false);
        }
    }

    @Override
    public void onItemAddSuccess() {
        mView.setSpinnerEnable(true);
    }
}
