package net.onepagebook.memorypower.main;


import net.onepagebook.memorypower.common.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.Setter;

public abstract class SkipNextContDown {

    private AtomicInteger index = new AtomicInteger(-1);
    @Setter
    @Getter
    private int playCount;
    @Setter
    @Getter
    private boolean isRandom;
    private ArrayList<Integer> indexList = new ArrayList<>();

    abstract void onTick(int index);

    abstract void onFinished();

    public void next() {
        index.incrementAndGet();
        if (index.get() == 0) {
            initIndexList();
        }
        if ((playCount - 1) < index.get()) {
            stop();
            return;
        }
        onTick(indexList.get(index.get()));
    }

    private void initIndexList() {
        for (int i = 0; i < playCount; i++) {
            indexList.add(i);
        }
        if (isRandom) {
            Collections.shuffle(indexList);
            for (Integer integer : indexList) {
                Log.d("integer = " + integer.intValue());
            }
        }
    }

    public void stop() {
        onFinished();
        index.set(-1);
        indexList.clear();
    }
}
