package ru.romiusse.box_store.src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.romiusse.box_store.src.Boxes.Box;
import ru.romiusse.box_store.src.Layouts.MyOrdersLayout;
import ru.romiusse.box_store.src.Layouts.StartLayout;

public class Store {
    
    public static List<Box> orders = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, IOException {

        new StartLayout().display();
    

    }

}
