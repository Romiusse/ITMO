package se.ifmo.ru.s367385.SpaceMarineDB.Object;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import se.ifmo.ru.s367385.SpaceMarineDB.Logger.ConsoleColors;
import se.ifmo.ru.s367385.SpaceMarineDB.Logger.Log;
import se.ifmo.ru.s367385.SpaceMarineDB.Logger.LogTypes;

public class SpaceMarineCollection implements Comparator<SpaceMarine>{
    
    private ArrayList<SpaceMarine> spaceMarineArray;
    private ZonedDateTime initializationDate;
    private ZonedDateTime updateDate;
    private ZonedDateTime saveDate;

    private boolean isLogging = false;


    public SpaceMarineCollection(ArrayList<SpaceMarine> sma){
        spaceMarineArray = sma;
        initializationDate = ZonedDateTime.now();
        updateDate = ZonedDateTime.now();
        saveDate = ZonedDateTime.now();

    }

    
    /** 
     * @return boolean
     */
    public boolean isLogging() {
        return isLogging;
    }

    
    /** 
     * @param isLogging
     */
    public void setLogging(boolean isLogging) {
        this.isLogging = isLogging;
    }

    
    /** 
     * @param t
     * @param msg
     */
    private void log(LogTypes t, String msg){
        if(isLogging) Log.log(t, msg);
    }

    public int size(){
        return spaceMarineArray.size();
    }

    public ArrayList<SpaceMarine> getSpaceMarineArray() {
        return spaceMarineArray;
    }

    public boolean isIdExist(Long id){
        for(int i = 0;i < size(); i++){
            if(spaceMarineArray.get(i).getId() == id) return true;
        }
        return false;

    }

    public Integer getPosById(Long id) throws Exception{
        for(int i = 0; i < size(); i++){
            SpaceMarine spaceMarine = spaceMarineArray.get(i);
            if(spaceMarine.getId() == id){
                return i;
            }
        }
        throw new Exception("Объект с таким id не существует");
    }

    public SpaceMarine getObjectById(Long id) throws Exception{
        for(int i = 0; i < size(); i++){
            SpaceMarine spaceMarine = spaceMarineArray.get(i);
            if(spaceMarine.getId() == id){
                return spaceMarine;
            }
        }
        throw new Exception("Объект с таким id не существует");
    }

    public void replace(SpaceMarine spaceMarine) throws Exception{
        try{
            spaceMarineArray.remove((int) getPosById(spaceMarine.getId()));
            spaceMarineArray.add(spaceMarine);
        } catch (Exception e){
            throw new Exception("Произошла ошибка при замене элемента");
        }
    }

    public void add(SpaceMarine spaceMarine) throws Exception{
        if(!isIdExist(spaceMarine.getId())){
            spaceMarineArray.add(spaceMarine);
            log(LogTypes.INFO, "Элемент был успешно добавлен");
        }
        else{
            throw new Exception("Элемент с id = " + Long.toString(spaceMarine.getId()) + "уже существует");
        }
    } 

    @Override
    public int compare(SpaceMarine lhs, SpaceMarine rhs) {
        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
        return lhs.getId() > rhs.getId() ? 1 : (lhs.getId() < rhs.getId()) ? -1 : 0;
    }

    public void sort(){
        Collections.sort(spaceMarineArray, new Comparator<SpaceMarine>() {
            @Override
            public int compare(SpaceMarine lhs, SpaceMarine rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getId() > rhs.getId() ? 1 : (lhs.getId() < rhs.getId()) ? -1 : 0;
            }
        });
    }
    
    public void removeGreater(Long id) throws Exception{
        sort();
        int oldSize = size();
        for(int i = 0, j = 0; i < oldSize; i++){
            SpaceMarine spaceMarine = spaceMarineArray.get(j);
            if(spaceMarine.getId() > id){
                remove(spaceMarine.getId());
            }
            else
                j++;
        }
    }

    public SpaceMarine minByCategory() throws Exception{
        for(int i = 0; i < size(); i++){
            SpaceMarine spaceMarine = spaceMarineArray.get(i);
            if(spaceMarine.getCategory() == AstartesCategory.AGGRESSOR){
                return spaceMarine;
            }
        }
        for(int i = 0; i < size(); i++){
            SpaceMarine spaceMarine = spaceMarineArray.get(i);
            if(spaceMarine.getCategory() == AstartesCategory.DREADNOUGHT){
                return spaceMarine;
            }
        }
        for(int i = 0; i < size(); i++){
            SpaceMarine spaceMarine = spaceMarineArray.get(i);
            if(spaceMarine.getCategory() == AstartesCategory.INCEPTOR){
                return spaceMarine;
            }
        }
        throw new Exception("Элемента с минимальной категорией не существует");
    }

    public void remove(Long id) throws Exception{
        Integer pos = getPosById(id);
        spaceMarineArray.remove((int) pos);
        log(LogTypes.INFO, "Элемент с id: " + Long.toString(id) + "был удален");
    }

    public void clear() throws Exception{
        spaceMarineArray.clear();
    }

    public String getInfo(){
        return ConsoleColors.BLUE_BOLD + "\nИнформмация о коллекции:\n" + 
        ConsoleColors.GREEN_BOLD + "Тип:" + ConsoleColors.YELLOW_BOLD + "SpaceMarine\n" + 
        ConsoleColors.GREEN_BOLD + "Колличество элементов:" + ConsoleColors.YELLOW_BOLD + Integer.toString(size()) + "\n" +
        ConsoleColors.GREEN_BOLD + "Дата инициализации:" + ConsoleColors.YELLOW_BOLD + initializationDate.toString() + "\n" +
        ConsoleColors.GREEN_BOLD + "Дата изменения:" + ConsoleColors.YELLOW_BOLD + updateDate.toString() + "\n" +
        ConsoleColors.GREEN_BOLD + "Дата сохранения:" + ConsoleColors.YELLOW_BOLD + saveDate.toString();      
    }


}
