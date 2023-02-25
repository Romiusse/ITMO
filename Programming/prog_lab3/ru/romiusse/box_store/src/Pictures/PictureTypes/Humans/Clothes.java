package ru.romiusse.box_store.src.Pictures.PictureTypes.Humans;


public enum Clothes {

    //Hats
    PANAM(12.34f),
    CAP(35.23f),

    //Outwear
    JACKET(147.14f),
    TSHIRT(90f),

    //Pants
    SHORTS(67.48f),
    PANTS(95.43f),

    //Boots
    SANDALS(50.25f),
    SNEAKERS(104.12f),

    NOTHING(0f);

    final public float cost;

    Clothes(float cost){
        this.cost = cost;
    }

}
