package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuEntry;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Humans.Clothes;

public class TabaccoChooseLayout4 implements LayoutInterface {

    public static Clothes boots;

    @Override
    public void display() {

        
        try{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
            System.out.println();

            System.out.println(" Choose human boots");

            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuEntry("Sandals") {
                @Override
                public void run() {
                    boots = Clothes.SANDALS;
                    new TabaccoChooseLayout5().display();
                }
            });

            menu.addEntry(new MenuEntry("Sneakers") {
                @Override
                public void run() {
                    boots = Clothes.SNEAKERS;
                    new TabaccoChooseLayout5().display();
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