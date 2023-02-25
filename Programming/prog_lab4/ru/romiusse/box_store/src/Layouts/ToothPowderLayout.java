package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuItemListener;
import ru.romiusse.box_store.src.Store;
import ru.romiusse.box_store.src.Boxes.Box;
import ru.romiusse.box_store.src.Boxes.BoxTypes.ToothPowder;
import ru.romiusse.box_store.src.Exceptions.MenuNotFoundItemException;
import ru.romiusse.box_store.src.Pictures.Sides;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Teeth;

public class ToothPowderLayout implements LayoutInterface, CreateBoxLayoutInterface {

    @Override
    public ToothPowder createBox(){

        ToothPowder toothPowder = new ToothPowder();
        toothPowder.setSize(10, 10, 10);
        toothPowder.setWeight(34);
        toothPowder.setPicture(createTeeth());
        return toothPowder;
    }

    private Teeth createTeeth(){

        Teeth teeth = new Teeth();
        teeth.setupSides(Sides.EAST);

        return teeth;
    }
    

    @Override
    public void display() {
        
        ToothPowder toothPowder = createBox();
        
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println();

            System.out.println(" Do u want to order?");
            System.out.println(toothPowder);
            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuItemListener("Yes") {
                @Override
                public void run() {
                    Store.boxNow = createBox();
                    new AddresseeLayout().display();
                }
            });

            menu.addEntry(new MenuItemListener("No") {
                @Override
                public void run() {

                    new StartLayout().display();
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
    