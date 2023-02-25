package ru.romiusse.box_store.src.Layouts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuItemListener;
import ru.romiusse.box_store.src.Store;
import ru.romiusse.box_store.src.Addressee.Addressee;
import ru.romiusse.box_store.src.Boxes.Box;
import ru.romiusse.box_store.src.Deliver.RoadType;
import ru.romiusse.box_store.src.Exceptions.MenuNotFoundItemException;

public class AddresseeLayout implements LayoutInterface{

    RoadType roadType;
    String street, name, ageStr, house;
    Box box;

    @Override
    public void display() {
        
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println();
            System.out.println("CONTACT INFO:");
            System.out.println();
            System.out.println(" Enter ur street");
            street = reader.readLine();
            System.out.println(" Enter ur house num");
            house = reader.readLine();
            System.out.println(" Enter ur name");
            name = reader.readLine();
            System.out.println(" Enter ur age");
            ageStr = reader.readLine();
            int age = Integer.parseInt(ageStr);

            System.out.println("Choose road type");
            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuItemListener("Forest") {
                @Override
                public void run() {
                    roadType = new RoadType.Forest(2);
                }
            });

            menu.addEntry(new MenuItemListener("Swamp") {
                @Override
                public void run() {
                    roadType = new RoadType.Forest(5);
                }
            });

            menu.addEntry(new MenuItemListener("Bumps") {
                @Override
                public void run() {
                    roadType = new RoadType.Forest(1);
                }
            });

            menu.addEntry(new MenuItemListener("Ravines") {
                @Override
                public void run() {
                    roadType = new RoadType.Forest(3);
                }
            });
            
            menu.run();

            box = Store.boxNow;

            Addressee addressee = new Addressee(street, house);
            addressee.setBox(box);
            addressee.setPerson(name, age);
            addressee.setRoadType(roadType);

            Store.orders.add(addressee);

            new StartLayout().display();

            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MenuNotFoundItemException e){
            e.printStackTrace();
        }

    }
    
}
