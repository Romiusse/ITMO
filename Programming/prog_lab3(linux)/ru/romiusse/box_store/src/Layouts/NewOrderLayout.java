package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuEntry;

public class NewOrderLayout implements LayoutInterface {

    @Override
    public void display() {

        
        try{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
            System.out.println();
            System.out.println(
            " █▄░█ █▀▀ █░█░█   █▀█ █▀█ █▀▄ █▀▀ █▀█" + "\n" +
            " █░▀█ ██▄ ▀▄▀▄▀   █▄█ █▀▄ █▄▀ ██▄ █▀▄");
            System.out.println();
            System.out.println();

            System.out.println(" Choose box type");

            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuEntry("Tabacco") {
                @Override
                public void run() {
                    new TabaccoChooseLayout1().display();
                }
            });

            menu.addEntry(new MenuEntry("Soap") {
                @Override
                public void run() {
                    new SoapLayout().display();
                }
            });

            menu.addEntry(new MenuEntry("ToothPowder") {
                @Override
                public void run() {
                    new ToothPowderLayout().display();
                }
            });

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
