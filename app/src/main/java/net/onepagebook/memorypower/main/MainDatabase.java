package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.common.Log;
import net.onepagebook.memorypower.db.AbsDatabase;
import net.onepagebook.memorypower.db.KeyPointNote;

import lombok.Getter;
import lombok.Setter;

class MainDatabase extends AbsDatabase {
    MainDatabase() {
        int size = mRealm.where(KeyPointNote.class).equalTo("name", "iloveyou2").findFirst().getKeyPoints().size();
        Log.d("size2 = " + size);
        size = mRealm.where(KeyPointNote.class).equalTo("name", "iloveyou").findFirst().getKeyPoints().size();
        Log.d("size = " + size);
    }
    @Setter
    @Getter
    private String nowNoteId;
}

