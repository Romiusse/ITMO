package ru.romiusse.box_store.src.Layouts;

import java.io.IOException;

import ru.romiusse.box_store.src.Menu;
import ru.romiusse.box_store.src.MenuEntry;

public class StartLayout implements LayoutInterface{

    @Override
    public void display() {

        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        
            System.out.println();
            System.out.println(
            " ██████╗░░█████╗░██╗░░██╗░░░██████╗████████╗░█████╗░██████╗░███████╗"+ "\n" +
            " ██╔══██╗██╔══██╗╚██╗██╔╝░░██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██╔════╝"+ "\n" +
            " ██████╦╝██║░░██║░╚███╔╝░░░╚█████╗░░░░██║░░░██║░░██║██████╔╝█████╗░░"+ "\n" +
            " ██╔══██╗██║░░██║░██╔██╗░░░░╚═══██╗░░░██║░░░██║░░██║██╔══██╗██╔══╝░░"+ "\n" +
            " ██████╦╝╚█████╔╝██╔╝╚██╗░░██████╔╝░░░██║░░░╚█████╔╝██║░░██║███████╗"+ "\n" +
            " ╚═════╝░░╚════╝░╚═╝░░╚═╝░░╚═════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝╚══════╝");
            System.out.println();
            System.out.println();

            Menu menu = new Menu();

            menu.addEntry(new MenuEntry("New Order") {
                @Override
                public void run() {
                    new NewOrderLayout().display();
                }
            });
            menu.addEntry(new MenuEntry("My Orders") {
                @Override
                public void run() {
                    new MyOrdersLayout().display();
                }
            });
            menu.addEntry(new MenuEntry("Exit") {
                @Override
                public void run() {
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
