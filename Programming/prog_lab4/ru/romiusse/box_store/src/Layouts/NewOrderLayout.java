package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuItemListener;
import ru.romiusse.box_store.src.Exceptions.MenuNotFoundItemException;

public class NewOrderLayout implements LayoutInterface {

    @Override
    public void display() {

        
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println();
            System.out.println(
            " █▄░█ █▀▀ █░█░█   █▀█ █▀█ █▀▄ █▀▀ █▀█" + "\n" +
            " █░▀█ ██▄ ▀▄▀▄▀   █▄█ █▀▄ █▄▀ ██▄ █▀▄");
            System.out.println();
            System.out.println();

            System.out.println(" Choose box type");

            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuItemListener("Tabacco") {
                @Override
                public void run() {
                    new TabaccoChooseLayout1().display();
                }
            });

            menu.addEntry(new MenuItemListener("Soap") {
                @Override
                public void run() {
                    new SoapLayout().display();
                }
            });

            menu.addEntry(new MenuItemListener("ToothPowder") {
                @Override
                public void run() {
                    new ToothPowderLayout().display();
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
