package net.onepagebook.memorypower.db;

import java.util.List;

import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class KeyPointNote{
    @PrimaryKey
    private long id;
    private String name;
    private List<KeyPoint> keyPoints;

}
