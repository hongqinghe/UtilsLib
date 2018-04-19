package middlem.person.utilsmodule.stringxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/***********************************************
 * <P> desc:    读取xml文件
 * <P> Author: gongtong
 * <P> Date: 2017/11/23 14:37
 ***********************************************/

public class ReadHelpString {
    /**
     * 文件路径
     */
    private static final String FILE_HELP_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/help.xml";
    private static final String FILE_HELP_TEXT_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/helpText.txt";
    private static String textContent;

    public static  void main(String [] args){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            File file=new File(FILE_HELP_PATH);
            Document parse = documentBuilder.parse(file);
            Element documentElement = parse.getDocumentElement();
            FileOutputStream fileOutputStream=new FileOutputStream(FILE_HELP_TEXT_PATH,true);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            NodeList childNodes = documentElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                System.out.println("length====================="+childNodes.getLength());
                String nodeName = childNodes.item(i).getNodeName();
                if (nodeName != null && nodeName.equals("#text")||nodeName!=null&&nodeName.equals("#comment")) {
                    continue;
                } else {
                    String name = childNodes.item(i).getAttributes().getNamedItem("name").getNodeValue();
                    bufferedWriter.write(name + "\n");
                    System.out.println("i====================="+i);
                    String textContent = childNodes.item(i).getTextContent();
                    assert textContent != null;
                    String[] value = textContent.trim().split("\n");
                    for (int j = 0; j < value.length; j++) {
                        String textC = value[j];
                        bufferedWriter.write(textC + "\n");
                    }
                }

            bufferedWriter.flush();
            osw.flush();
            fileOutputStream.flush();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
