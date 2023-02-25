package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuItemListener;
import ru.romiusse.box_store.src.Store;
import ru.romiusse.box_store.src.Boxes.BoxTypes.Tobacco;
import ru.romiusse.box_store.src.Exceptions.MenuNotFoundItemException;
import ru.romiusse.box_store.src.Pictures.Sides;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Humans.Action;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Humans.Clothes;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Humans.Human;

public class TabaccoChooseLayout5 implements LayoutInterface, CreateBoxLayoutInterface {

    public static Clothes boots;

    @Override
    public Tobacco createBox(){

        Tobacco tabacco = new Tobacco();
        tabacco.setSize(10, 10, 10);
        tabacco.setWeight(34);
        tabacco.setPicture(createHuman());
        return tabacco;

    }

    private Human createHuman(){

        Human human = new Human();

        human.setClothes(TabaccoChooseLayout1.hat, TabaccoChooseLayout2.outWear, 
            TabaccoChooseLayout3.pants, TabaccoChooseLayout4.boots);
        human.setAction(Action.DIVE);
        human.setupSides(Sides.EAST);

        return human;
    }

    @Override
    public void display() {
        
        Human human = createHuman();
        
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println();

            System.out.println(" Do u want to order?");
            System.out.println(human);
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