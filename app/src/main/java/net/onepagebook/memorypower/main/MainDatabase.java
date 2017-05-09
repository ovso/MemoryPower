package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.db.AbsDatabase;
import net.onepagebook.memorypower.db.KeyPoint;
import net.onepagebook.memorypower.db.KeyPointNote;

import lombok.Getter;
import lombok.Setter;

class MainDatabase extends AbsDatabase {
    @Setter
    @Getter
    private String nowNoteId;

    public KeyPointNote getKeyPointNote(String nowNoteId) {
        return mRealm.where(KeyPointNote.class).equalTo("id", nowNoteId).findFirst();
    }

    public void setKeyPointRemember(KeyPointNote note, int currentPlayIndex) {
        mRealm.beginTransaction();
        KeyPoint point = note.getKeyPoints().get(currentPlayIndex);
        point.setRemember(true);
        mRealm.commitTransaction();

    }
}