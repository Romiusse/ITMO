package se.ifmo.ru.s367385.SpaceMarineDB.Parser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class XmlWriter {

    StringBuilder xmlOut;
    
    
    /** 
     * @param xml
     */
    public void writeXml(Xml xml){

        xmlOut = new StringBuilder();
        dictMapWriter(xml, 0);

        try {
            FileOutputStream out = new FileOutputStream("testOut.xml");
            out.write(xmlOut.toString().getBytes(StandardCharsets.UTF_8));
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }



    }

    
    /** 
     * @param xml
     * @param tabsCount
     * @param tagName
     */
    private void dataWrite(Xml xml, int tabsCount, String tagName){

        for(int i = 0; i < tabsCount; i++) xmlOut.append("    ");
        xmlOut.append("<" + tagName + ">");
        xmlOut.append(xml.getData());
        xmlOut.append("</" + tagName + ">\n");

    }

    
    /** 
     * @param xml
     * @param tabsCount
     */
    private void dictMapWriter(Xml xml, int tabsCount){

        for (Map.Entry<String, Xml> e : xml.dictMap().entrySet()){

            if(e.getValue().getXmlType() == XmlType.ARRAY){
                arrayWriter(e.getValue(), tabsCount, e.getKey());
                continue;
            }
            if(e.getValue().getXmlType() == XmlType.DATA){
                dataWrite(e.getValue(), tabsCount, e.getKey());
                continue;
            }

            for(int i = 0; i < tabsCount; i++) xmlOut.append("    ");
            xmlOut.append("<" + e.getKey() + ">\n");
            dictMapWriter(e.getValue(), tabsCount + 1);          
            for(int i = 0; i < tabsCount; i++) xmlOut.append("    ");
            xmlOut.append("</" + e.getKey() + ">\n");
            
        }

    }

    private void arrayWriter(Xml xml, int tabsCount, String tagName){

        for(int j = 0; j < xml.array().size(); j++){
            Xml e = xml.array().get(j);
            for(int i = 0; i < tabsCount; i++) xmlOut.append("    ");
            xmlOut.append("<" + tagName + ">\n");
            dictMapWriter(e, tabsCount + 1);
            for(int i = 0; i < tabsCount; i++) xmlOut.append("    ");
            xmlOut.append("</" + tagName + ">\n");
        }

    }

}
