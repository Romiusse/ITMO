package ru.romiusse.box_store.src.Boxes.BoxTypes;

import ru.romiusse.box_store.src.Boxes.Box;
import ru.romiusse.box_store.src.Boxes.GoodsStuffInterface;

public class Tobacco extends Box implements GoodsStuffInterface{


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
        return getPicture().calcPicCost() * calcSale();
    }
    
}
