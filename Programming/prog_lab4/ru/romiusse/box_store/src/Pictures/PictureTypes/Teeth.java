package ru.romiusse.box_store.src.Pictures.PictureTypes;

import ru.romiusse.box_store.src.Pictures.Sides;

import ru.romiusse.box_store.src.Pictures.Picture;
import ru.romiusse.box_store.src.Pictures.setupPictureSidesInterface;

public class Teeth extends Picture implements setupPictureSidesInterface{


    private Sides side;
    @Override
    public void setupSides(ru.romiusse.box_store.src.Pictures.Sides sides) {
        this.side = sides;
        
    }

    @Override
    public ru.romiusse.box_store.src.Pictures.Sides getSides() {
        return this.side;
    }


    @Override
    public float calcPicCost() {

        if(isColorize()) return 149.61f * 4f;
        return 149.61f;

    }

    @Override
    public String toString() {
        return "Type: Teeth; Side: " + side.name();
    }


    
}
