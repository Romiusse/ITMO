package ru.romiusse.box_store.src.Boxes.BoxTypes;

import ru.romiusse.box_store.src.Boxes.Box;
import ru.romiusse.box_store.src.Boxes.GoodsStuffInterface;
import ru.romiusse.box_store.src.Pictures.PictureTypes.SoapHead;

public class Soap extends Box implements GoodsStuffInterface{


    private SoapHead picture;

    public void setPicture(SoapHead picture){
        this.picture = picture;
    }

    @Override
    public float calcSale() {
        return 0.9f;
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
