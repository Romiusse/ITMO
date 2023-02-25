package se.ifmo.ru.s367385.SpaceMarineDB.Object;

import java.lang.annotation.Native;
import java.time.ZonedDateTime;

import se.ifmo.ru.s367385.SpaceMarineDB.Logger.ConsoleColors;

public class SpaceMarine {
    private Long id;
    private String name;
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int health; //Значение поля должно быть больше 0
    private AstartesCategory category; //Поле не может быть null
    private Weapon weaponType; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле может быть null
    private Chapter chapter; //Поле может быть null

    public SpaceMarine(){
        setCreationDate(ZonedDateTime.now());
    }

    
    /** 
     * @return Long
     */
    public Long getId() {
        if(id == null) throw new RuntimeException("Поле id не может быть null");
        return id;
    }


    
    /** 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name.length() == 0 || name == null) throw new RuntimeException("Поле name не может быть null, Строка не может быть пустой");
        this.name = name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public java.time.ZonedDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(java.time.ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if(health <= 0) throw new RuntimeException("Поле health должно быть больше 0");
        this.health = health;
    }
    public AstartesCategory getCategory() {
        return category;
    }
    public void setCategory(AstartesCategory category) {
        this.category = category;
    }
    public Weapon getWeaponType() {
        return weaponType;
    }
    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }
    public Chapter getChapter() {
        return chapter;
    }
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
    public String info(){
        return ConsoleColors.BLUE_BOLD + "\nИнформмация об объекте с id = " + ConsoleColors.CYAN_BOLD + Long.toString(getId()) + ConsoleColors.BLUE_BOLD + ":\n" +
        ConsoleColors.GREEN_BOLD + "Имя: " + ConsoleColors.YELLOW_BOLD + getName() +"\n" + 
        ConsoleColors.GREEN_BOLD + "Координаты "+ ConsoleColors.CYAN_BOLD +">>\n" +
        ConsoleColors.GREEN_BOLD + "\tX: " + ConsoleColors.YELLOW_BOLD + getCoordinates().getX() + "\n" +
        ConsoleColors.GREEN_BOLD + "\tY: " + ConsoleColors.YELLOW_BOLD + getCoordinates().getY() + "\n" +
        ConsoleColors.GREEN_BOLD + "Дата создания: " + ConsoleColors.YELLOW_BOLD + getCreationDate().toString() + "\n" +
        ConsoleColors.GREEN_BOLD + "Здоровье: " + ConsoleColors.YELLOW_BOLD + Integer.toString(getHealth()) + "\n" +
        (getCategory()    != null ? (ConsoleColors.GREEN_BOLD + "Категория: " + ConsoleColors.YELLOW_BOLD + getCategory().toString() + "\n")   : "")+
        (getWeaponType()  != null ? (ConsoleColors.GREEN_BOLD + "Оружие 1: " + ConsoleColors.YELLOW_BOLD + getWeaponType().toString() + "\n")  : "")+
        (getMeleeWeapon() != null ? (ConsoleColors.GREEN_BOLD + "Оружие 2: " + ConsoleColors.YELLOW_BOLD + getMeleeWeapon().toString() + "\n") : "")+
        (getChapter() != null ?
        (ConsoleColors.GREEN_BOLD + "Глава "+ ConsoleColors.CYAN_BOLD + ">>\n" +
        ConsoleColors.GREEN_BOLD + "\tИмя: " + ConsoleColors.YELLOW_BOLD + getChapter().getName() + "\n" +
        ConsoleColors.GREEN_BOLD + "\tЛегион: " + ConsoleColors.YELLOW_BOLD + getChapter().getParentLegion() + "\n" +
        ConsoleColors.GREEN_BOLD + "\tКоличество пехотинцев: " + ConsoleColors.YELLOW_BOLD + Long.toString(getChapter().getMarinesCount()) + "\n" +
        (getChapter().getWorld() != null ? (ConsoleColors.GREEN_BOLD + "\tНазвание мира: " + ConsoleColors.YELLOW_BOLD + getChapter().getWorld()) : "")) : "") + 
        ConsoleColors.RESET;

    }
}
