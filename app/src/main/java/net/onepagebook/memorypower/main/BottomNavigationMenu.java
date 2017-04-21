package net.onepagebook.memorypower.main;

public enum BottomNavigationMenu {
    HOME(0), SETTING(1);

    private int position;

    BottomNavigationMenu(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
