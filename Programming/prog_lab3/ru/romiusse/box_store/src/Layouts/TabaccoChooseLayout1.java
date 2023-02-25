package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuEntry;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Humans.Clothes;

public class TabaccoChooseLayout1 implements LayoutInterface {

    public static Clothes hat;

    @Override
    public void display() {


        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println();

            System.out.println(" Choose human hat");

            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuEntry("Panam") {
                @Override
                public void run() {
                    hat = Clothes.PANAM;
                    new TabaccoChooseLayout2().display();
                }
            });

            menu.addEntry(new MenuEntry("Cap") {
                @Override
                public void run() {
                    hat = Clothes.CAP;
                    new TabaccoChooseLayout2().display();
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