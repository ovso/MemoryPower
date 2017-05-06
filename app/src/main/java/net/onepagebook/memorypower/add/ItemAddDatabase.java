package net.onepagebook.memorypower.add;

import net.onepagebook.memorypower.db.AbsDatabase;
import net.onepagebook.memorypower.db.KeyPoint;
import net.onepagebook.memorypower.db.KeyPointNote;

import lombok.Getter;
import lombok.Setter;

public class ItemAddDatabase extends AbsDatabase {
    @Setter
    @Getter
    private String noteId;

    public void add(String subject, String content, String noteId) {
        mRealm.beginTransaction();
        KeyPointNote note = mRealm.where(KeyPointNote.class).equalTo("id", noteId).findFirst();
        KeyPoint point = new KeyPoint();
        point.setSubject(subject);
        point.setContent(content);
        note.getKeyPoints().add(point);
        mRealm.commitTransaction();
    }
}