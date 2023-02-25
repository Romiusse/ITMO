package ru.romiusse.box_store.src.Boxes;

import ru.romiusse.box_store.src.Exceptions.SizeCheckException;
import ru.romiusse.box_store.src.Exceptions.WeightCheckException;
import ru.romiusse.box_store.src.Pictures.Picture;

public abstract class Box {
    
    private Picture picture;

    private float sizeX, sizeY, sizeZ;
    private float weight;

    public abstract float calcCost();

    public void setSize(float x, float y, float z){

        if(x < 0 || y < 0 || z < 0) throw new SizeCheckException("Size must be greater or equal 0");

        sizeX = x;
        sizeY = y;
        sizeZ = z;
    }

    public float[] getSize(){
        return new float[]{sizeX, sizeY, sizeZ};
    }

    public void setWeight(float weight){

        if(weight < 0) throw new WeightCheckException("Weigth must be greater or equal 0");

        this.weight = weight;
    }
    public float getWeight(){
        return this.weight;
    }

    public void setPicture(Picture picture){
        this.picture = picture;
    }

    public Picture getPicture(){
        return this.picture;
    }

    @Override
    public String toString() {
        return "Size (XYZ): " + sizeX + "; " + sizeY + "; " + sizeZ + "\t|\tBox cost: " + Float.toString(calcCost());
    }

    @Override
    public boolean equals(Object obj) {
        return calcCost() == ((Box) obj).calcCost();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + "This is BOX HASHCODE!!!".hashCode();
    }
}
