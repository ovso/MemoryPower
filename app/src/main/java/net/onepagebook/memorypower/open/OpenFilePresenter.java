package net.onepagebook.memorypower.open;

public interface OpenFilePresenter {

    void init();

    void onItemClick(int which);

    void onClick(int which);

    void setItems(CharSequence[] items);

    interface View {

        void addListener();

        void dismiss();

        void setNoteInfo(String noteId, String fileName);

        void setTitleBar();
    }
}
