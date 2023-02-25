package se.ifmo.ru.s367385.SpaceMarineDB.Parser;

import java.io.FileReader;
import java.util.Scanner;
public class XmlReader {


    private int stPos;
    private String stringXml;
    private boolean isNewBlock;


    
    /**
     * @param fileName
     * @return
     * @throws Exception
     */
    public Xml readXml(String fileName) throws Exception{
        
        Scanner in = new Scanner(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();

        stringXml = sb.toString();
        stPos = 0;
        isNewBlock = false;

        Xml xml = parseInfo();

        return xml;

    }

    
    /** 
     * @return Xml
     * @throws Exception
     */
    private Xml parseTag() throws Exception{

        Xml xml = new Xml(XmlType.DICT_MAP);

        while(true){

            boolean isSuccses = false;
            for(; stPos < stringXml.length(); stPos++){
                if(stringXml.charAt(stPos) == '<'){
                    stPos++;
                    isSuccses = true;
                    break;
                }
            }
            if(!isSuccses) throw new Exception("Xml parse error: Not found start of open tag");

            String tagName = "";
            isSuccses = false;
            for(; stPos < stringXml.length(); stPos++){
                if(stringXml.charAt(stPos) == '>'){
                    stPos++;
                    isSuccses = true;
                    break;
                }
                if(stringXml.charAt(stPos) != ' ' && stringXml.charAt(stPos) != '/') tagName += stringXml.charAt(stPos);
                if(stringXml.charAt(stPos) == ' ') throw new Exception("Xml parse error at " + Integer.toString(stPos) + " : Spase in the tag");
            }
            if(!isSuccses) throw new Exception("Xml parse error: Not found end of open tag");


            Xml newXml = parseInfo();

            if(xml.dictMap().containsKey(tagName)){
                if(xml.dictMap().get(tagName).getXmlType() == XmlType.DICT_MAP){
                    Xml containsXml = xml.dictMap().get(tagName);
                    xml.dictMap().remove(tagName);
                    xml.dictMap().put(tagName, new Xml(XmlType.ARRAY));
                    xml.dictMap().get(tagName).array().add(containsXml);
                }
                xml.dictMap().get(tagName).array().add(newXml);
            }
            else
                xml.dictMap().put(tagName, newXml);

            
            
            if(!isNewBlock){
                String closeTag = "";
                isSuccses = false;
                for(; stPos < stringXml.length(); stPos++){
                    if(stringXml.charAt(stPos) == '>'){
                        isSuccses = true;
                        stPos++;
                        break;
                    }
                    if(stringXml.charAt(stPos) != ' ' && stringXml.charAt(stPos) != '/') closeTag += stringXml.charAt(stPos);
                }
                if(!isSuccses) throw new Exception("Xml parse error: Not found close tag");
                if(!closeTag.equals(tagName)) throw new Exception("Xml parse error: Tags are not same");
            }
            isNewBlock = false;

            isSuccses = false;
            for(; stPos < stringXml.length(); stPos++){
                if(stringXml.charAt(stPos) == '<'){
                    isSuccses = true;
                    break;
                }
            }
            if(stPos >= stringXml.length()) return xml;


            if(!isSuccses) throw new Exception("Xml parse error: Not found close tag");
            if(stPos + 1 >= stringXml.length()) throw new Exception("Xml parse error: Reach end of the file");

            if(stringXml.charAt(stPos + 1) == '/'){
                isSuccses = false;
                for(; stPos < stringXml.length(); stPos++){
                    if(stringXml.charAt(stPos) == '>'){
                        isSuccses = true;
                        stPos++;
                        break;
                    }
                }
                if(!isSuccses) throw new Exception("Xml parse error: Not found close tag");
                isNewBlock = true;
                return xml;
            }
        }

    }

    
    /** 
     * @return Xml
     * @throws Exception
     */
    private Xml parseInfo() throws Exception{
        String info = "";
        boolean isSuccses = false;
        for(; stPos < stringXml.length(); stPos++){
            if(stringXml.charAt(stPos) == '<'){
                isSuccses = true;
                break;
            }
            info += stringXml.charAt(stPos);
        }

        if(!isSuccses) throw new Exception("Xml parse error: Not found close tag");
        if(stPos + 1 >= stringXml.length()) throw new Exception("Xml parse error: Reach end of the file");

        if(stringXml.charAt(stPos + 1) == '/'){

            stPos += 2;
            return new Xml(XmlType.DATA).setData(info);
        }
        //xml.dictMap().put(lastTag, parseTag(stPos, stringXml, lastTag, xml));
        return parseTag();
        //return xml;

    }


    
}
