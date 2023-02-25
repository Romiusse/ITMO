package se.ifmo.ru.s367385.SpaceMarineDB;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import se.ifmo.ru.s367385.SpaceMarineDB.Parser.XmlWriter;
import se.ifmo.ru.s367385.SpaceMarineDB.Console.MenuClient;
import se.ifmo.ru.s367385.SpaceMarineDB.Logger.Log;
import se.ifmo.ru.s367385.SpaceMarineDB.Logger.LogTypes;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.MeleeWeapon;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.SpaceMarine;
import se.ifmo.ru.s367385.SpaceMarineDB.Object.SpaceMarineCollection;
import se.ifmo.ru.s367385.SpaceMarineDB.Parser.Xml;
import se.ifmo.ru.s367385.SpaceMarineDB.Parser.XmlReader;

public class Program {
    
    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        
        try {
            XmlReader xmlReader = new XmlReader();
            Xml xml = xmlReader.readXml(System.getenv("SPACE_MARINE"));
            ArrayList<SpaceMarine> spaceMarineArray = Xml2SpaceMarineArray.xml2ArrayList(xml);
            SpaceMarineCollection spaceMarineCollection = new SpaceMarineCollection(spaceMarineArray);
            MenuClient menu = new MenuClient(spaceMarineCollection);
            menu.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
