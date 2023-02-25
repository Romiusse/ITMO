package ru.romiusse.box_store.src;


public abstract class MenuItemListener {
    private String title;

    public String getTitle() {
        return title;
    }

    public MenuItemListener(String title) {
        this.title = title;
    }

    public abstract void run();
}
