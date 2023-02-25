package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuEntry;
import ru.romiusse.box_store.src.Store;

public class MyOrdersLayout implements LayoutInterface {

    @Override
    public void display() {
        
        try{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
            System.out.println();
            System.out.println(
            " █▀▄▀█ █▄█   █▀█ █▀█ █▀▄ █▀▀ █▀█ █▀" + "\n" +
            " █░▀░█ ░█░   █▄█ █▀▄ █▄▀ ██▄ █▀▄ ▄█");
            System.out.println();
            System.out.println();

            for(int i = 0;i < Store.orders.size(); i++){
                System.out.println("\t" + (i + 1) + ". " + Store.orders.get(i));
            }
            
            System.out.println();

            Menu menu = new Menu();


            menu.addEntry(new MenuEntry("Back") {
                @Override
                public void run() {
                    new StartLayout().display();
                }
            });
            
            menu.run();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
