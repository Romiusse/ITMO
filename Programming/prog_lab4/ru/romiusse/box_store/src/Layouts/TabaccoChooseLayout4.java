package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuItemListener;
import ru.romiusse.box_store.src.Exceptions.MenuNotFoundItemException;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Humans.Clothes;

public class TabaccoChooseLayout4 implements LayoutInterface {

    public static Clothes boots;

    @Override
    public void display() {

        
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println();

            System.out.println(" Choose human boots");

            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuItemListener("Sandals") {
                @Override
                public void run() {
                    boots = Clothes.SANDALS;
                    new TabaccoChooseLayout5().display();
                }
            });

            menu.addEntry(new MenuItemListener("Sneakers") {
                @Override
                public void run() {
                    boots = Clothes.SNEAKERS;
                    new TabaccoChooseLayout5().display();
                }
            });

            menu.addEntry(new MenuItemListener("Back") {
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
        } catch (MenuNotFoundItemException e){
            e.printStackTrace();
        }

    }
    
}