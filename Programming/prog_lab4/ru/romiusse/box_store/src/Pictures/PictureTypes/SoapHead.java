package ru.romiusse.box_store.src.Pictures.PictureTypes;

import ru.romiusse.box_store.src.Pictures.Picture;
import ru.romiusse.box_store.src.Pictures.setupPictureSidesInterface;

public class SoapHead extends Picture implements setupPictureSidesInterface{


    private ru.romiusse.box_store.src.Pictures.Sides side;
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
        if(isColorize()) return 204.54f * 4f;
        return 204.54f;
    }

    @Override
    public String toString() {
        return "Type: SoapHead; Side: " + side.name();
    }
    
}
