package net.onepagebook.memorypower.main;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import net.onepagebook.memorypower.app.MyApplication;
import net.onepagebook.memorypower.db.AbsDatabase;
import net.onepagebook.memorypower.db.KeyPoint;
import net.onepagebook.memorypower.db.KeyPointNote;
import net.onepagebook.memorypower.db.SampleKeyPointNote;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

class MainDatabase extends AbsDatabase {
    @Setter
    @Getter
    private String nowNoteId;
    @Setter
    @Getter
    private MemoryPowerPlayer.DisplayType displayType = MemoryPowerPlayer.DisplayType.ALL;
    @Setter
    @Getter
    private List<KeyPoint> playKeyPointList;
    @Setter
    @Getter
    private KeyPoint playKeyPoint;

    KeyPointNote getKeyPointNote(String nowNoteId) {
        return mRealm.where(KeyPointNote.class).equalTo("id", nowNoteId).findFirst();
    }

    private List<KeyPoint> getKeyPoints(String nowNoteId) {
        return mRealm.where(KeyPointNote.class).equalTo("id", nowNoteId).findFirst().getKeyPoints();
    }

    List<KeyPoint> getKeyPointList(String nowNoteId, MemoryPowerPlayer.DisplayType
            displayType) {

        if (displayType == MemoryPowerPlayer.DisplayType.ALL) {
            return getKeyPoints(nowNoteId);
        } else if (displayType == MemoryPowerPlayer.DisplayType.REMEMBER) {
            List<KeyPoint> keypointOriginList = getKeyPointNote(nowNoteId).getKeyPoints();
            return getRememberKeypointList(keypointOriginList, true);
        } else if (displayType == MemoryPowerPlayer.DisplayType.NOT_REMEMBER) {
            List<KeyPoint> keypointOriginList = getKeyPointNote(nowNoteId).getKeyPoints();
            return getRememberKeypointList(keypointOriginList, false);
        } else {
            return getKeyPoints(nowNoteId);
        }
    }

    private List<KeyPoint> getRememberKeypointList(List<KeyPoint> originList, boolean isRemember) {
        List<KeyPoint> keypointDevList = new ArrayList<>();
        for (int i = 0; i < originList.size(); i++) {
            KeyPoint point = originList.get(i);
            if (point.isRemember() == isRemember) keypointDevList.add(point);
        }
        return keypointDevList;
    }

    boolean setKeyPointRemember(KeyPoint point) {
        mRealm.beginTransaction();
        boolean isRemember = !point.isRemember();
        point.setRemember(isRemember);
        mRealm.commitTransaction();
        return isRemember;
    }

    List<SampleKeyPointNote> loadSampleNote() {
        ArrayList<SampleKeyPointNote> notes = new ArrayList<>();
        AssetManager assetManager = MyApplication.getAppContext().getAssets();
        String[] jsonFileNames;
        try {
            jsonFileNames = assetManager.list("sample");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String json;
        for (String jsonFileName : jsonFileNames) {
            try {
                InputStream is = MyApplication.getAppContext().getAssets().open("sample/" +
                        jsonFileName);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            notes.add(new Gson().fromJson(json, SampleKeyPointNote.class));
        }

        return notes;
    }
}