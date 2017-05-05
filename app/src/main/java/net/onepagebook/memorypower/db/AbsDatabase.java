package net.onepagebook.memorypower.db;

import net.onepagebook.memorypower.common.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public abstract class AbsDatabase {
    protected Realm mRealm;

    protected AbsDatabase() {
        mRealm = Realm.getDefaultInstance();
        Log.d("Realm Config = " + mRealm.getConfiguration());
        Log.d("Realm path = " + mRealm.getPath());
    }

    public boolean isExistFileName(String field, String value) {
        RealmResults<KeyPointNote> r = mRealm.where(KeyPointNote.class).equalTo(field, value)
                .findAll();
        return r.size() > 0;
    }

    public void createFile(String fileName) {
        mRealm.beginTransaction();
        KeyPointNote note = mRealm.createObject(KeyPointNote.class, getPrimaryKeyValue());
        note.setName(fileName);
        mRealm.commitTransaction();
    }

    private String getPrimaryKeyValue() {
        return new SimpleDateFormat("yyyyMMddHHmmSS", Locale.KOREA)
                .format(new Date(System.currentTimeMillis()));

    }

    public String getNoteId(String fileName) {
        KeyPointNote note = mRealm.where(KeyPointNote.class).equalTo("name", fileName).findFirst();
        return note.getId();

    }


    public String getNoteName(String id) {
        KeyPointNote note = mRealm.where(KeyPointNote.class).equalTo("id", id).findFirst();
        return note.getName();
    }
}