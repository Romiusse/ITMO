package se.ifmo.ru.s367385.SpaceMarineDB.Object;

public class Coordinates {
    private Integer x; //Максимальное значение поля: 778, Поле не может быть null
    private int y; //Значение поля должно быть больше -926
    
    /** 
     * @return Integer
     */
    public Integer getX() {
        return x;
    }
    
    /** 
     * @param x
     */
    public void setX(Integer x) {
        if(x > 778) throw new RuntimeException("Максимальное значение поля x: 778");
        this.x = x;
    }
    
    /** 
     * @return int
     */
    public int getY() {
        return y;
    }
    public void setY(int y) {
        if(y <= -926) throw new RuntimeException("Значение поля y должно быть больше -926");
        this.y = y;
    }
}
