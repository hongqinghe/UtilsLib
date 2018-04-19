package middlem.person.utilsmodule.stringxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/***********************************************
 * <P> desc:
 * <P> Author: gongtong
 * <P> Date: 2017/11/13 16:29
 ***********************************************/

public class MakeTxtUils {
    private static final String CHILD_NODE1="string";
    /**
     * android String文件路径(源文件)
     */
    private static final String FILE_ANDROID_SOURCE_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/source.xml";
    /**
     * android 源文件集合
     */
    private static Map<String, String> androidList;
    /**
     * 翻译文档
     */
    private static Map<String, String> iosList;
    /**
     * 翻译文档
     */
    private static Map<String, String> tempList;
    /**
     * 相同String文件且不带format的文件集合
     */
    private static final String FILE_SAM_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/string.txt";
    /**
     * android String文件路径(源文件)
     */
    private static final String FILE_ANDROID_XML_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/androidStringFinal.xml";

   public static void main(String[]  args){
       androidList=new LinkedHashMap<>();
       iosList=new LinkedHashMap<>();
       tempList=new LinkedHashMap<>();
       getString();
   }
    public static void getString() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            File file = new File(FILE_ANDROID_SOURCE_PATH);
            Document document = builder.parse(file);
            Element root = document.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            System.out.println("开始读取android源文件");
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node subNode = childNodes.item(j);
                if (CHILD_NODE1.equals(subNode.getNodeName())) {
                    // key
                    String key = subNode.getAttributes().getNamedItem("name")
                            .getNodeValue();
                    // value
                    String value = subNode.getTextContent().trim();
//                 System.out.println("android_no_format的文件+"+1);
                    androidList.put(key, value);
                }
            }
            System.out.println("读取android源文件成功，大小为：" + androidList.size());
            System.out.println("android 源文件读结束");
            iosTextReader();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取ios文件
     */
    public static void iosTextReader() {
        FileReader file = null;
        try {
            File file2 = new File(FILE_SAM_PATH);
            FileReader fileReader = new FileReader(file2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                if (readLine.contains("=")) {
//                    // 去掉引号
                String s3 = readLine.replaceAll("\"", "");
                    String[] split = s3.split("=");
//                    split[0] = split[0].replace("\"", "");
//                    split[1] =split[1].replace("\"", "");
//                    split[1] =split[1].replace(";", "");
//                    split[1] =split[1].substring(2, split[1].length());

//                    String[] split = readLine.split("=");
                    String iosKey = split[0].trim();
//                    iosKey=trimFirstAndLastChar(iosKey,"\"");
                    String iosValue = split[1].trim();
//                    iosValue=trimFirstAndLastChar(iosValue,"\"");
                    for (String key : androidList.keySet()) {
                        String value = androidList.get(key);
                        if (value.equals(iosKey)) {
                            tempList.put(key, iosValue);
                        }
                    }
                    iosList.put(iosKey, iosValue);
                    readLine = bufferedReader.readLine();
                }
                else {
                    readLine = bufferedReader.readLine();
                }
            }
            System.out.println("读取翻译源文件成功，大小为：" + iosList.size());
            System.out.println("翻译源文件读结束");
            bufferedReader.close();
            fileReader.close();
            toAndroidXml();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String trimFirstAndLastChar(String source,String element){
        boolean beginIndexFlag = true;
        boolean endIndexFlag = true;
        do{
            int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
            int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length();
                source = source.substring(beginIndex, endIndex-1);
            beginIndexFlag = (source.indexOf(element) == 0);
            endIndexFlag = (source.lastIndexOf(element) + 1 == source.length());
        } while (beginIndexFlag || endIndexFlag);
        return source;
    }
    public static String trim(String source, String beTrim) {
        if(source==null){
            return "";
        }
        source = source.trim(); // 循环去掉字符串首的beTrim字符
        if(source.isEmpty()){
            return "";
        }
        String beginChar = source.substring(0, 1);
        if (beginChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(1, source.length());
//            beginChar = source.substring(0, 1);
        }
        // 循环去掉字符串尾的beTrim字符
        String endChar = source.substring(source.length() - 1, source.length());
        if (endChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(0, source.length() - 1);
//            endChar = source.substring(source.length() - 1, source.length());
        }
        return source;
    }

    /**
     * 取出指定格式的String文件进行处理
     *
     * @param
     */
    private static void toAndroidXml() {
        try {
            File file;
                file = new File(FILE_ANDROID_XML_PATH);
            System.out.println("开始写入xml文件"+tempList.size());

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();
            //创建根元素
            Element element = document.createElement("resources");
            //将根元素添加到document中去
            document.appendChild(element);

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            for (String key : tempList.keySet()) {
                String value = tempList.get(key);

                //创建一个元素，并把它追加到根元素的子元素
                Element student = document.createElement("string");
                element.appendChild(student);
                student.setAttribute("name", key);
                student.setTextContent(value);
                tf.transform(new DOMSource(document), new StreamResult(file));
            }
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
