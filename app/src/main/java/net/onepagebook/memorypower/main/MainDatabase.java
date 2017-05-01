package net.onepagebook.memorypower.main;

import java.util.ArrayList;

import lombok.Data;

public class MainDatabase {

    private ArrayList<MainDatabase.SampleMainPointNote> mSampleMainPointNoteList = new
            ArrayList<>();

    MainDatabase() {
        generationSampleNote();
    }

    public int getCount() {
        return mSampleMainPointNoteList.size();
    }

    public MainDatabase.SampleMainPointNote getItem(int index) {
        if (index >= getCount()) {
            return null;
        }
        return mSampleMainPointNoteList.size() == 0 ? null : mSampleMainPointNoteList.get(index);
    }

    private void generationSampleNote() {
        MainDatabase.SampleMainPointNote note = new MainDatabase
                .SampleMainPointNote();
        note.subject = "기후의 요소";
        note.content = "습도, 온도, 바람";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "연소의 요소";
        note.content = "산소, 재료, 발화열";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "음의 요소";
        note.content = "음의 높이, 음의 크기, 음색";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "힘의 요소";
        note.content = "힘의 크기, 방향(적용선), 작용점";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "인물";
        note.content = "사건을 만들어 나가는 등장 인물의 성격이나 유형";
        mSampleMainPointNoteList.add(note);

/*
        note = new MainDatabase.SampleMainPointNote();
        note.subject = "사건";
        note.content = "등장 인물들 사이의 관계에 따라 일어나는 여러 종류의 일들";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "배경";
        note.content = "사건이 벌어지는 바탕이 되는 시대나 공간적 상황";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "생산의 요소";
        note.content = "토지, 자본, 노동력";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "음악의 요소";
        note.content = "멜로디, 리듬, 하모니";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "원가의 요소";
        note.content = "① 재료비 ② 노무비 ③ 경비(감가상각비, 이자비, 혼합비 등의 일체)";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "국가의 요소";
        note.content = "국가의 요소";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "교육의 요소";
        note.content = "교사, 과정, 학생";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "리더십 요소";
        note.content = "포용력, 결단력, 통솔력";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "발명의 요소";
        note.content = "편리성, 기능성, 생산성";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "목소리 요소";
        note.content = "발음, 발성, 호흡";
        mSampleMainPointNoteList.add(note);

        note = new MainDatabase.SampleMainPointNote();
        note.subject = "국가의 요소";
        note.content = "주권, 영토, 국민";
        mSampleMainPointNoteList.add(note);
*/
    }

    @Data
    public final static class SampleMainPointNote {
        private String subject;
        private String content;
    }

}
