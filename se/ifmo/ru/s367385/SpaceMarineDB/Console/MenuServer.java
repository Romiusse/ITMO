package se.ifmo.ru.s367385.SpaceMarineDB.Console;

import se.ifmo.ru.s367385.SpaceMarineDB.Xml2SpaceMarineArray;
import se.ifmo.ru.s367385.SpaceMarineDB.Logger.Log;
import se.ifmo.ru.s367385.SpaceMarineDB.Logger.LogTypes;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.MeleeWeapon;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.SpaceMarine;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.SpaceMarineCollection;
import se.ifmo.ru.s367385.SpaceMarineDB.Parser.XmlWriter;

public class MenuServer {

    SpaceMarineCollection spaceMarineCollection;

    public MenuServer(SpaceMarineCollection smc){
        spaceMarineCollection = smc;

    }

    
    
    /** 
     * @return String
     */
    public String info(){
        return spaceMarineCollection.getInfo();
    }

    
    /** 
     * @return String
     */
    public String show(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < spaceMarineCollection.size(); i++){
            sb.append(spaceMarineCollection.getSpaceMarineArray().get(i).info());
            sb.append('\n');
        }
        if(spaceMarineCollection.size() == 0) return Log.log2client(LogTypes.INFO, "В коллекции нет элементов");
        return sb.toString();
    }

    
    /** 
     * @param spaceMarine
     * @return String
     */
    public String add(SpaceMarine spaceMarine){
        try{
            spaceMarineCollection.add(spaceMarine);
            return Log.log2client(LogTypes.SERVER, "Элемент был успешно добавлен");
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }
    }

    public String update(SpaceMarine spaceMarine){
        try{
            spaceMarineCollection.getPosById(spaceMarine.getId());
            spaceMarineCollection.replace(spaceMarine);
            return Log.log2client(LogTypes.SERVER, "Элемент был успешно изменен");
        }catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }


    }

    public String removeById(Long id){
        try{
            spaceMarineCollection.remove(id);
            return Log.log2client(LogTypes.SERVER, "Элемент был успешно удален");
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }

    }

    public String clear(){
        try{
            spaceMarineCollection.clear();
            return Log.log2client(LogTypes.SERVER, "Коллекция была успешна удалена");
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }
    }

    public String save(String fileName){
        try{
            XmlWriter xmlWriter = new XmlWriter();
            xmlWriter.writeXml(Xml2SpaceMarineArray.arrayList2Xml(spaceMarineCollection.getSpaceMarineArray()));
            return Log.log2client(LogTypes.SERVER, "Коллекция была успешна сохранена");
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }
    }

    public void executeScript(){

    }

    public void exit(){

    }

    public String removeGreater(Long id){
        try{
            spaceMarineCollection.removeGreater(id);
            return Log.log2client(LogTypes.SERVER, "Все элементы коллекции больше заданного id были удалены");
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }
    }

    public String sort(){
        try{
            spaceMarineCollection.sort();
            return Log.log2client(LogTypes.SERVER, "Все элементы коллекции были отсортированы");
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }
    }

    public void history(){

    }

    public String minByCategory(){
        try{
            return spaceMarineCollection.minByCategory().info();
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }
    }

    public String groupCountingByMeleeWeapon(){
        try{
            StringBuilder out = new StringBuilder();
            out.append("Количество с категорией CHAIN_SWORD: ");
            int cnt = 0;
            for(int i = 0; i < spaceMarineCollection.size(); i++){
                SpaceMarine spaceMarine = spaceMarineCollection.getSpaceMarineArray().get(i);
                if(spaceMarine.getMeleeWeapon() == MeleeWeapon.CHAIN_SWORD){
                    cnt++;
                }
            }
            out.append(Integer.toString(cnt) + "\n");
            out.append("Количество с категорией LIGHTING_CLAW: ");
            cnt = 0;
            for(int i = 0; i < spaceMarineCollection.size(); i++){
                SpaceMarine spaceMarine = spaceMarineCollection.getSpaceMarineArray().get(i);
                if(spaceMarine.getMeleeWeapon() == MeleeWeapon.LIGHTING_CLAW){
                    cnt++;
                }
            }
            out.append(Integer.toString(cnt) + "\n");
            out.append("Количество с категорией MANREAPER: ");
            cnt = 0;
            for(int i = 0; i < spaceMarineCollection.size(); i++){
                SpaceMarine spaceMarine = spaceMarineCollection.getSpaceMarineArray().get(i);
                if(spaceMarine.getMeleeWeapon() == MeleeWeapon.MANREAPER){
                    cnt++;
                }
            }
            out.append(Integer.toString(cnt) + "\n");
            out.append("Количество с категорией POWER_BLADE: ");
            cnt = 0;
            for(int i = 0; i < spaceMarineCollection.size(); i++){
                SpaceMarine spaceMarine = spaceMarineCollection.getSpaceMarineArray().get(i);
                if(spaceMarine.getMeleeWeapon() == MeleeWeapon.POWER_BLADE){
                    cnt++;
                }
            }
            out.append(Integer.toString(cnt) + "\n");
            out.append("Количество без ничего: ");
            cnt = 0;
            for(int i = 0; i < spaceMarineCollection.size(); i++){
                SpaceMarine spaceMarine = spaceMarineCollection.getSpaceMarineArray().get(i);
                if(spaceMarine.getMeleeWeapon() == null){
                    cnt++;
                }
            }
            out.append(Integer.toString(cnt) + "\n");
            return out.toString();
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }
    }

    public String filterByHealth(int hp){
        try{
            StringBuilder out = new StringBuilder();
            out.append("Элементы с колличестовм здоровья равным: " + Integer.toString(hp) + "\n");
            int cnt = 0;
            for(int i = 0; i < spaceMarineCollection.size(); i++){
                SpaceMarine spaceMarine = spaceMarineCollection.getSpaceMarineArray().get(i);
                if(spaceMarine.getHealth() == hp){
                    out.append(spaceMarine.info());
                }
            }
            return out.toString();
        } catch(Exception e){
            return Log.log2client(LogTypes.ERROR, e.getMessage());
        }
    }
    
}
