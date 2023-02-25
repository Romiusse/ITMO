package ru.romiusse.box_store.src;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuEntry> entries = new ArrayList();

    public void addEntry(MenuEntry entry){
        entries.add(entry);
    }

    private void printMenu(){
        
        for(int i = 0; i < entries.size(); i++) System.out.println("\t[" + (i + 1) + "] " + entries.get(i).getTitle());
        System.out.println();
    }

    public void run() throws InterruptedException, IOException {
        // Бесконечный цикл, пока не нажали кнопку выход
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        printMenu();
        try {
            String line = reader.readLine();
            int choice = Integer.parseInt(line);
            // Выбираем нажатый пункт меню и выполняем его код
            MenuEntry entry = entries.get(choice - 1);
            entry.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
