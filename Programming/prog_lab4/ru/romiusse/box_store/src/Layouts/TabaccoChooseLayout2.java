package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuItemListener;
import ru.romiusse.box_store.src.Exceptions.MenuNotFoundItemException;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Humans.Clothes;

public class TabaccoChooseLayout2 implements LayoutInterface {

    public static Clothes outWear;

    @Override
    public void display() {

        
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println();

            System.out.println(" Choose human outwear");

            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuItemListener("Jacket") {
                @Override
                public void run() {
                    outWear = Clothes.JACKET;
                    new TabaccoChooseLayout3().display();
                }
            });

            menu.addEntry(new MenuItemListener("Tshirt") {
                @Override
                public void run() {
                    outWear = Clothes.TSHIRT;
                    new TabaccoChooseLayout3().display();
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