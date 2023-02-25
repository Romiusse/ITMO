package ru.romiusse.box_store.src.Boxes.BoxTypes;

import ru.romiusse.box_store.src.Boxes.Box;
import ru.romiusse.box_store.src.Boxes.GoodsStuffInterface;
import ru.romiusse.box_store.src.Pictures.PictureTypes.Humans.Human;

public class Tobacco extends Box implements GoodsStuffInterface{



    private Human picture;

    public void setPicture(Human picture){
        this.picture = picture;
    }

    @Override
    public float calcSale() {
        return 0.75f;
    }

    @Override
    public float sell() {
        return 0;
    }


    @Override
    public float calcCost() {
        return picture.calcPicCost() * calcSale();
    }
    
}
