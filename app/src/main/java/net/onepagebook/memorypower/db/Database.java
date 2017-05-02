package net.onepagebook.memorypower.db;

import io.realm.Realm;

/**
 * Created by ovso on 2017. 5. 1..
 */

public class Database {
    protected Realm mRealm;
    protected Database() {
        mRealm = Realm.getDefaultInstance();
    }
}
