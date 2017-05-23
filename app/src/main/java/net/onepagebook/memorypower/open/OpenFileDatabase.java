package net.onepagebook.memorypower.open;

import net.onepagebook.memorypower.db.AbsDatabase;

import lombok.Getter;
import lombok.Setter;


public class OpenFileDatabase extends AbsDatabase {
    @Setter
    @Getter
    private CharSequence[] items;

    @Setter
    @Getter
    private int nowPosition;
}
