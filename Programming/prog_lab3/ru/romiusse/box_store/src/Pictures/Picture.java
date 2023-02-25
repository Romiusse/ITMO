package ru.romiusse.box_store.src.Pictures;

public abstract class Picture {
    
    private float scale;

    public float getScale() {
        return scale;
    }
    public void setScale(float scale){
        this.scale = scale;
    }


    private boolean isColorize;

    public boolean isColorize() {
        return isColorize;
    }
    public void setColorize(boolean isColorize) {
        this.isColorize = isColorize;
    }
    
    public abstract float calcPicCost();

    @Override
    public String toString() {
        return "Picture; Scale: " + scale;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + "This is PICTURE HASHCODE!!!".hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return calcPicCost() == ((Picture) obj).calcPicCost();
    }

}
