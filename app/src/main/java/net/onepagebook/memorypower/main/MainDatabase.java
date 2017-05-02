package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.db.Database;
import net.onepagebook.memorypower.db.KeyPoint;
import net.onepagebook.memorypower.db.KeyPointNote;

import java.util.ArrayList;

public class MainDatabase extends Database {

    private KeyPointNote mNote = new KeyPointNote();
    MainDatabase() {
        super();
        getKeyPointNote();
    }
    public KeyPointNote getKeyPointNote() {
        mNote.setId(System.currentTimeMillis());
        mNote.setKeyPoints(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            KeyPoint point = new KeyPoint();
            point.setSubject("제목 + " + i);
            point.setContent("핵심내용 + " + i);
            mNote.getKeyPoints().add(point);
        }

        return mNote;
    }

    public int getNoteCount() {
        return mNote.getKeyPoints().size();
    }
}

