package net.onepagebook.memorypower.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class KeyPointNote extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private RealmList<KeyPoint> keyPoints;
}
