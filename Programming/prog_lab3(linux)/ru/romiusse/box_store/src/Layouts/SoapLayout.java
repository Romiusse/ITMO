package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuEntry;
import ru.romiusse.box_store.src.Store;
import ru.romiusse.box_store.src.Boxes.BoxTypes.Soap;
import ru.romiusse.box_store.src.Pictures.Sides;
import ru.romiusse.box_store.src.Pictures.PictureTypes.SoapHead;

public class SoapLayout implements LayoutInterface {

    private Soap createBox(){

        Soap soap = new Soap();
        soap.setSize(10, 10, 10);
        soap.setWeight(34);
        soap.setPicture(createSoapHead());
        return soap;
    }

    private SoapHead createSoapHead(){

        SoapHead soapHead = new SoapHead();
        soapHead.setupSides(Sides.EAST);

        return soapHead;
    }

    @Override
    public void display() {
        
        SoapHead soapHead = createSoapHead();
        
        try{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
            System.out.println();

            System.out.println(" Do u want to order?");
            System.out.println(soapHead);
            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuEntry("Yes") {
                @Override
                public void run() {
                    Store.orders.add(createBox());
                    new StartLayout().display();
                }
            });

            menu.addEntry(new MenuEntry("No") {
                @Override
                public void run() {

                    new StartLayout().display();
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