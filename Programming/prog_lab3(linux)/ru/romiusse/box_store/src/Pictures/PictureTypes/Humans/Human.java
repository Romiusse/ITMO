package ru.romiusse.box_store.src.Pictures.PictureTypes.Humans;

import ru.romiusse.box_store.src.Pictures.Picture;
import ru.romiusse.box_store.src.Pictures.setupPictureSidesInterface;

public class Human extends Picture implements setupPictureSidesInterface, HumanLikeInterface{

    private Clothes hat;
    private Clothes outWear;
    private Clothes pants;
    private Clothes boots;

    Action action;


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
    public void setClothes(Clothes hat, Clothes outWear, Clothes pants, Clothes boots) {
        this.hat = hat;
        this.outWear = outWear;
        this.pants = pants;
        this.boots = boots;
        
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
        
    }


    @Override
    public float calcPicCost() {

        float sum = hat.cost + outWear.cost + pants.cost + boots.cost;

        if(isColorize()) return sum * 4;
        return sum;

    }

    @Override
    public String toString() {
        return "Type: Human; Side: " + side.name() + "; Clothes: [" + hat.name() + ", " + outWear.name() + ", " + pants.name() + ", " + boots.name() + "]; Action: " + action.name();
    }
    


}
