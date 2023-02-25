package ru.romiusse.box_store.src;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ru.romiusse.box_store.src.Exceptions.MenuNotFoundItemException;

public class Menu {
    
    private List<MenuItemListener> entries = new ArrayList();

    public void addEntry(MenuItemListener entry){
        entries.add(entry);
    }

    private void printMenu(){
        for(int i = 0; i < entries.size(); i++) System.out.println("\t[" + (i + 1) + "] " + entries.get(i).getTitle());
        System.out.println();
    }

    public void run() throws InterruptedException, IOException, MenuNotFoundItemException {
        // Бесконечный цикл, пока не нажали кнопку выход
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printMenu();
        try {
            String line = reader.readLine();
            int choice = Integer.parseInt(line);
            // Выбираем нажатый пункт меню и выполняем его код

            if(entries.size() < choice || choice < 1) throw new MenuNotFoundItemException("Menu item: " + choice + " was not found");

            MenuItemListener entry = entries.get(choice - 1);
            entry.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
