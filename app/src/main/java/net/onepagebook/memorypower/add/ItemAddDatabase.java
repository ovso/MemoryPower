package net.onepagebook.memorypower.add;

import net.onepagebook.memorypower.db.AbsDatabase;

import lombok.Getter;
import lombok.Setter;

public class ItemAddDatabase extends AbsDatabase {
    @Setter
    @Getter
    private String noteId;

}