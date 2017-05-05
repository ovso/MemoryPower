package net.onepagebook.memorypower.main;

import net.onepagebook.memorypower.db.AbsDatabase;

import lombok.Getter;
import lombok.Setter;

class MainDatabase extends AbsDatabase {
    @Setter
    @Getter
    private String noteId;
}

