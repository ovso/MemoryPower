package net.onepagebook.memorypower.db;

import io.realm.RealmObject;
import lombok.Data;

@Data
public class KeyPoint extends RealmObject{
    private String subject;
    private String content;
    private boolean memory;
}
