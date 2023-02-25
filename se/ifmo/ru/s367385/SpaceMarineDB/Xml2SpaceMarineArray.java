package se.ifmo.ru.s367385.SpaceMarineDB;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import se.ifmo.ru.s367385.SpaceMarineDB.Object.AstartesCategory;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.Chapter;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.Coordinates;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.MeleeWeapon;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.SpaceMarine;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.Weapon;
import se.ifmo.ru.s367385.SpaceMarineDB.Parser.Xml;
import se.ifmo.ru.s367385.SpaceMarineDB.Parser.XmlType;

public class Xml2SpaceMarineArray {
    

    
    /** 
     * @param s
     * @return boolean
     */
    private static boolean isNull(String s){
        if(s.equals("null") || s.equals("NULL") || s.equals("")) return true;
        return false;
    }

    
    /** 
     * @param xml
     * @return ArrayList<SpaceMarine>
     */
    public static ArrayList<SpaceMarine> xml2ArrayList(Xml xml){
    
        ArrayList<SpaceMarine> spaceMarinesArray = new ArrayList<SpaceMarine>();
        ArrayList<Xml> spaceMarinesXmlArray = new ArrayList<Xml>();
        try{
            spaceMarinesXmlArray = xml.dictMap().get("SpaceMarines").dictMap().get("SpaceMarine").array();
        } catch(Exception e){
            Xml x= xml.dictMap().get("SpaceMarines").dictMap().get("SpaceMarine");
            spaceMarinesXmlArray.add(x);
        }
        for(int i = 0;i < spaceMarinesXmlArray.size(); i++){
            
            Xml thisSpaceMarine = spaceMarinesXmlArray.get(i);

            SpaceMarine spaceMarine = new SpaceMarine();
            if(spaceMarinesArray.size() == 0)
                spaceMarine.setId((long)1);
            else
                spaceMarine.setId(spaceMarinesArray.get(spaceMarinesArray.size() - 1).getId() + 1);

            spaceMarine.setName(thisSpaceMarine.dictMap().get("name").getData());

            Coordinates coordinates = new Coordinates();
            coordinates.setX(Integer.parseInt(thisSpaceMarine.dictMap().get("coordinates").dictMap().get("x").getData()));
            coordinates.setY(Integer.parseInt(thisSpaceMarine.dictMap().get("coordinates").dictMap().get("y").getData()));
            spaceMarine.setCoordinates(coordinates);

            spaceMarine.setCreationDate(ZonedDateTime.parse(thisSpaceMarine.dictMap().get("creationDate").getData()));

            spaceMarine.setHealth(Integer.parseInt(thisSpaceMarine.dictMap().get("health").getData()));

            try{
            spaceMarine.setCategory(AstartesCategory.valueOf(thisSpaceMarine.dictMap().get("category").getData()));
            } catch(Exception e){
                spaceMarine.setCategory(null);
            }
            try{
                if(isNull(thisSpaceMarine.dictMap().get("meleeWeapon").getData())) spaceMarine.setMeleeWeapon(null);
                else spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(thisSpaceMarine.dictMap().get("meleeWeapon").getData()));
            }catch(Exception e){
                spaceMarine.setMeleeWeapon(null);
            }

            try{
                if(isNull(thisSpaceMarine.dictMap().get("weaponType").getData()) ) spaceMarine.setWeaponType(null);
                else spaceMarine.setWeaponType(Weapon.valueOf(thisSpaceMarine.dictMap().get("weaponType").getData()));
            }catch(Exception e){
                spaceMarine.setWeaponType(null);
            }

            try{
                if(!thisSpaceMarine.dictMap().containsKey("chapter")) spaceMarine.setChapter(null);
                else{
                    Chapter chapter = new Chapter();
                    chapter.setName(thisSpaceMarine.dictMap().get("chapter").dictMap().get("name").getData());
                    chapter.setMarinesCount(Integer.parseInt(thisSpaceMarine.dictMap().get("chapter").dictMap().get("marinesCount").getData()));
                    chapter.setParentLegion(thisSpaceMarine.dictMap().get("chapter").dictMap().get("parentLegion").getData());
                    chapter.setWorld(thisSpaceMarine.dictMap().get("chapter").dictMap().get("world").getData());
                    spaceMarine.setChapter(chapter);
                }
            }catch(Exception e){
                spaceMarine.setChapter(null);
            }

            spaceMarinesArray.add(spaceMarine);
        }
        return spaceMarinesArray;
    }

    
    /** 
     * @param spaceMarinesArray
     * @return Xml
     */
    public static Xml arrayList2Xml(ArrayList<SpaceMarine> spaceMarinesArray){

        Xml xml = new Xml(XmlType.ARRAY);

        for(int i = 0; i < spaceMarinesArray.size(); i++){

            Xml spaceMarineXml = new Xml(XmlType.DICT_MAP);
            SpaceMarine spaceMarine = spaceMarinesArray.get(i);

            spaceMarineXml.dictMap().put("name", new Xml(XmlType.DATA).setData(spaceMarine.getName()));

            Xml coordinates = new Xml(XmlType.DICT_MAP);
            coordinates.dictMap().put("x", new Xml(XmlType.DATA).setData(Integer.toString(spaceMarine.getCoordinates().getX())));
            coordinates.dictMap().put("y", new Xml(XmlType.DATA).setData(Integer.toString(spaceMarine.getCoordinates().getY())));
            spaceMarineXml.dictMap().put("coordinates", coordinates);

            spaceMarineXml.dictMap().put("creationDate", new Xml(XmlType.DATA).setData(spaceMarine.getCreationDate().toString()));

            spaceMarineXml.dictMap().put("health", new Xml(XmlType.DATA).setData(Integer.toString(spaceMarine.getHealth())));

            try{
            spaceMarineXml.dictMap().put("category", new Xml(XmlType.DATA).setData(spaceMarine.getCategory().name()));
            }catch(Exception e){
                spaceMarineXml.dictMap().put("category", new Xml(XmlType.DATA).setData("null"));
            }

            try{
                spaceMarineXml.dictMap().put("meleeWeapon", new Xml(XmlType.DATA).setData(spaceMarine.getMeleeWeapon().name()));
            }catch(Exception e){
                spaceMarineXml.dictMap().put("meleeWeapon", new Xml(XmlType.DATA).setData("null"));
            }

            try{
                spaceMarineXml.dictMap().put("weaponType", new Xml(XmlType.DATA).setData(spaceMarine.getWeaponType().name()));
            }catch(Exception e){
                spaceMarineXml.dictMap().put("weaponType", new Xml(XmlType.DATA).setData("null"));
            }

            try{
                Xml chapter = new Xml(XmlType.DICT_MAP);
                chapter.dictMap().put("name", new Xml(XmlType.DATA).setData(spaceMarine.getChapter().getName()));
                chapter.dictMap().put("marinesCount", new Xml(XmlType.DATA).setData(Long.toString(spaceMarine.getChapter().getMarinesCount())));
                chapter.dictMap().put("parentLegion", new Xml(XmlType.DATA).setData(spaceMarine.getChapter().getParentLegion()));
                try{
                    chapter.dictMap().put("world", new Xml(XmlType.DATA).setData(spaceMarine.getChapter().getWorld()));
                }catch(Exception e){
                    chapter.dictMap().put("world",new Xml(XmlType.DATA).setData("null"));
                }
                spaceMarineXml.dictMap().put("chapter", chapter);
            }catch(Exception e){
                spaceMarineXml.dictMap().put("chapter", new Xml(XmlType.DATA).setData("null"));
            }

            xml.array().add(spaceMarineXml);
        }

        Xml spaceMarinesXml = new Xml(XmlType.DICT_MAP);
        Xml spaceMarineXml = new Xml(XmlType.DICT_MAP);
        spaceMarineXml.dictMap().put("SpaceMarine", xml);
        spaceMarinesXml.dictMap().put("SpaceMarines", spaceMarineXml);

        return spaceMarinesXml;

    }

}
