package net.onepagebook.memorypower.db;


import java.util.List;

import lombok.Data;

@Data
public class SampleKeyPointNote {
    private String name;
    private List<KeyPoint> list;

    @Data
    public final static class KeyPoint {
        private String subject;
        private String content;
    }

    public String toString() {
        return "name = " + name + "\n list = " + list;
    }
}
