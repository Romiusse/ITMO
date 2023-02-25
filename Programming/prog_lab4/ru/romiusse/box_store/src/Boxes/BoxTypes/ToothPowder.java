package ru.romiusse.box_store.src.Boxes.BoxTypes;

import ru.romiusse.box_store.src.Boxes.Box;
import ru.romiusse.box_store.src.Boxes.GoodsStuffInterface;

public class ToothPowder extends Box implements GoodsStuffInterface{


    @Override
    public float calcSale() {
        return 0.43f;
    }

    @Override
    public float sell() {
        return 0;
    }

    @Override
    public float calcCost() {
        return 560;
    }
    
}
