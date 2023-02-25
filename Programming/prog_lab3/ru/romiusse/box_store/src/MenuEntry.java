package ru.romiusse.box_store.src;


public abstract class MenuEntry {
    private String title;

    public String getTitle() {
        return title;
    }

    public MenuEntry(String title) {
        this.title = title;
    }

    public abstract void run();
}
