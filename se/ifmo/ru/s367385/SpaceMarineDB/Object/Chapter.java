package se.ifmo.ru.s367385.SpaceMarineDB.Object;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String parentLegion;
    private long marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private String world; //Поле может быть null
    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /** 
     * @param name
     */
    public void setName(String name) {
        if(name.length() == 0 || name == null) throw new RuntimeException("Поле name не может быть null, Строка не может быть пустой");
        this.name = name;
    }
    
    /** 
     * @return String
     */
    public String getParentLegion() {
        return parentLegion;
    }
    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }
    public long getMarinesCount() {
        return marinesCount;
    }
    public void setMarinesCount(long marinesCount) {
        if(marinesCount <= 0 || marinesCount > 1000) throw new RuntimeException("Значение поля должно быть больше 0, Максимальное значение поля: 1000");
        this.marinesCount = marinesCount;
    }
    public String getWorld() {
        return world;
    }
    public void setWorld(String world) {
        this.world = world;
    }
}