package se.ifmo.ru.s367385.SpaceMarineDB.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Xml {

    private XmlType xmlType;

    
    /** 
     * @return XmlType
     */
    public XmlType getXmlType() {
        return xmlType;
    }

    private Map<String, Xml> xmlDictMap = new HashMap<>();
    private String xmlData = new String();
    private ArrayList<Xml> xmlArray = new ArrayList<>();

    public Xml(XmlType type) {
        this.xmlType = type;
    }

    
    /** 
     * @return ArrayList<Xml>
     */
    public ArrayList<Xml> array(){
        if(xmlType == XmlType.ARRAY)
            return xmlArray;
        else throw new RuntimeException("Xml object is not ARRAY type");
    }

    
    /** 
     * @return Map<String, Xml>
     */
    public Map<String, Xml> dictMap(){
        if(xmlType == XmlType.DICT_MAP)
            return xmlDictMap;
        else throw new RuntimeException("Xml object is not DICT_MAP type");
    }

    public String getData(){
        if(xmlType == XmlType.DATA)
            return xmlData;
        else throw new RuntimeException("Xml object is not DATA type");
    }

    public Xml setData(String data){
        if(xmlType == XmlType.DATA)
            this.xmlData = data;
        else throw new RuntimeException("Xml object is not DATA type");
        return this;
    }


    

}
