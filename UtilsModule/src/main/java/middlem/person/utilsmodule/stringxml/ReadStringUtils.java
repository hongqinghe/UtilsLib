package middlem.person.utilsmodule.stringxml;


import android.util.ArrayMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Hashtable;
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
 * <P> desc:   思路：1：先读取ios和android源文件并进行分类（format和noFormat），
 *                  将各自noFormat文件分别写入ios和android各自对应的文件中，这里使用统一的android文件为基，
 *                  所以同时将format文件写入android对应的文件中
 *              2：比较全部文件ios和android中的不同，写入compare文件中，然后比较format文件，
 *              并将format文件分别写入ios和android各自对应的文件中，
 *              3：
 * <P> Author: gongtong
 * <P> Date: 2017/11/9 10:17
 ***********************************************/

public class ReadStringUtils {
    /**
     * format 类型
     */
    private static final int TYPE_FORMAT = 0x1;
    /**
     * format 类型
     */
    private static final int TYPE_NO_FORMAT = 0x3;
    /**
     * 相同字符串
     */
    private static final int TYPE_SAME = 0x2;
    /**
     * All
     */
    private static final int TYPE_ALL = 0x4;
    /**
     *  android
     */
    private static final int TYPE_COMMON_TEXT = 0x5;

    /**
     *  android没有类型对应的ios文档
     */
    private static final int TYPE_IOS_TEXT = 0x6;

    private static final String CHILD_NODE1="string";

    private static final String CHILD_NODE2="string-array";
    /**
     * android 源文件集合
     */
    private static Map<String, String> androidList;
    /**
     * ios源文件
     */
    private static Map<String, String> iosList;
    private static Map<String, String> iosList2;
    /**
     * 暂存文件集合
     */
    private static Map<String, String> tempList;
    /**
     * 暂存文件集合
     */
    private static Map<String, String> iosTempList;
    /**
     * 比较后的存在相同文件的集合
     */
    private static Map<String, String> androidPareList;
    /**
     * 字符串中带format的集合
     */
    private static Map<String, String> formatList;
    /**
     * 不带format的集合
     */
    private static Map<String, String> noFormatList;
    /**
     * 字符串中带format的集合
     */
    private static Map<String, String> iosFormatList;
    /**
     * 不带format的集合
     */
    private static Map<String, String> iosNoFormatList;
    private static Map<String, String> iosRemoveFormatList;
    /**
     * 相同String文件且不带format的文件集合
     */
    private static final String FILE_SAME_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/sameStrings.xml";
    /**
     * android String文件路径(源文件)
     */
//    private static final String FILE_ANDROID_SOURCE_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/strings.xml";
    private static final String FILE_ANDROID_SOURCE_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/source.xml";
    /**
     * ios String文件路径*（源文件）
     */
    private static final String FILE_IOS_SOURCE_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/ios.txt";
    private static final String FILE_IOS_SOURCE_revmove_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/remove.txt";
    /**
     * 两端不相同的String
     */
    private static final String FILE_COMPARE_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/compare.txt";
    /**
     * 最终生成Ios.txt格式的路径(不带format的String)
     */
    private static final String FILE_IOS_NO_FORMAT_TEXT_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/iosNoFormatString.txt";
    /**
     * 最终生成Ios.txt格式的路径(带format的String)
     */
    private static final String FILE_IOS_FORMAT_TEXT_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/iosFormatString.txt";
    private static final String FILE_IOS_FORMAT_test_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/iosRemove.txt";
    /**
     * android端的format string 文件路径
     */
    private static final String FILE_FORMAT_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/androidFormatString.xml";
    /**
     * android  no format String 文件路径
     */
    private static final String FILE_ANDROID_NO_FORMAT_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/androidNoFormat.xml";
     /**
     * 文件路径
     */
    private static final String FILE_COMMON_FINAL_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/commonFinal.txt";
   /**
     * 文件路径
     */
    private static final String FILE_IOS_FINAL_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/iosFinal.txt";

    public static void main(String[] args) {
        androidList = new LinkedHashMap<>();
        iosList = new LinkedHashMap<>();
        iosList2 = new LinkedHashMap<>();
        tempList = new LinkedHashMap<>();
        iosTempList = new LinkedHashMap<>();
        androidPareList = new LinkedHashMap<>();
        formatList = new LinkedHashMap<>();
        noFormatList = new LinkedHashMap<>();
        iosFormatList = new LinkedHashMap<>();
        iosNoFormatList = new LinkedHashMap<>();
        iosRemoveFormatList = new LinkedHashMap<>();
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
                    String value = subNode.getTextContent();
                    if (value.contains("%")) {
//                        System.out.println("android_format的文件+"+1);
                        formatList.put(key, value);
                    } else {
                        noFormatList.put(key, value);
//                        System.out.println("android_no_format的文件+"+1);
                    }
                    androidList.put(key, value);
                }
            }
            System.out.println("读取android源文件成功，大小为：" + androidList.size());
            System.out.println("读取android源文件成功，并且含有format文件的string有" + formatList.size());
            System.out.println("读取android源文件成功，并且不含有format文件的string有" + noFormatList.size());
            System.out.println("android 源文件读结束"+"\n");
            toSaveAndroidString(noFormatList, TYPE_NO_FORMAT);
            toSaveAndroidString(formatList, TYPE_FORMAT);
            toText(androidList,TYPE_COMMON_TEXT);
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
            File file2 = new File(FILE_IOS_SOURCE_PATH);
            FileReader fileReader = new FileReader(file2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                System.out.println("开始读取ios源文件");
            }
            while (readLine != null) {
                // 去掉引号
                String s3 = readLine.replaceAll("\"", "");
                if (s3.contains("%")) {
                    iosFormatList.put(s3, s3);
                } else {
                    iosNoFormatList.put(s3, s3);
                }
                iosList2.put(s3, s3);
                iosList.put(s3, s3);
                readLine = bufferedReader.readLine();
            }
            System.out.println("读取ios源文件成功，大小为：" + iosList.size());
            System.out.println("读取ios源文件成功，并且含有format文件的string有" + iosFormatList.size());
            System.out.println("读取ios源文件成功，并且不含有format文件的string有" + iosNoFormatList.size());
            System.out.println("ios 源文件读结束"+"\n");
            bufferedReader.close();
            fileReader.close();
//              iosRemoveReader();
            toCompare(iosNoFormatList, TYPE_NO_FORMAT);
            toCompare(iosList, TYPE_ALL);
            toIosText(iosFormatList,TYPE_FORMAT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符串比较(如果在android集合中，key对应的value可以取到对应的ios  value值，在生成相同的文件)
     *
     * @param iosList
     * @param typeFormat
     */
    private static void toCompare(Map<String, String> iosList, int typeFormat) {
        if (TYPE_NO_FORMAT == typeFormat) {
            System.out.println("string 文件开始比较(noFormat) ，开始写入不同的文件");
            tempList = noFormatList;
        } else if (TYPE_ALL == typeFormat) {
            System.out.println("string 文件开始比较(all) ，开始写入不同的文件");
            tempList = androidList;
        }
        iosTempList = iosList;
        for (String key : tempList.keySet()) {
            String value = tempList.get(key);
            String txtValue = iosList.get(value);
            if (txtValue != null) {
                androidPareList.put(key, value);
                iosTempList.remove(txtValue);
            }
        }
        System.out.println("比较完毕，不同大小为" + iosTempList.size()+"\n");
        try {
            if (TYPE_ALL == typeFormat) {
            File file = new File(FILE_COMPARE_PATH);
            System.out.println("开始写入不同文件---->compare.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            for (String s : iosTempList.keySet()) {
                iosTempList.get(s);
                bufferedWriter.write("\"" + iosTempList.get(s) + "\"");
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            osw.flush();
            fileOutputStream.flush();
            System.out.println("写入不同文件结束，写入大小为"+iosTempList.size()+"\n");
            }
            //写入noFormat字符
            toIosText(noFormatList,typeFormat);
            if (TYPE_ALL == typeFormat) {
                toText(iosTempList,TYPE_IOS_TEXT);
            }
            toSaveAndroidString(androidPareList, TYPE_SAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成ios输出文档
     *
     * @param typeFormat
     * @param iosList
     */
    private static void toIosText( Map<String, String> iosList,int typeFormat) {
        File file = null;
        if (TYPE_NO_FORMAT == typeFormat) {
            file = new File(FILE_IOS_NO_FORMAT_TEXT_PATH);
        } else  if (TYPE_ALL==typeFormat){
            file = new File(FILE_IOS_FORMAT_test_PATH);
            return;
        }else {
            file=new File(FILE_IOS_FORMAT_TEXT_PATH);
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            for (String s : iosList.keySet()) {
                iosList.get(s);
                bufferedWriter.write("\"" + iosList.get(s) + "\"" + "               =               " + "\"" + iosList.get(s) + "\"");
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            osw.flush();
            fileOutputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取出指定格式的String文件进行处理
     *
     * @param androidPareList
     */
    private static void toSaveAndroidString(Map<String, String> androidPareList, int type) {
        try {
            File file;
            if (TYPE_FORMAT == type) {
                file = new File(FILE_FORMAT_PATH);
                System.out.println("开始写入format android字符------>androidFormatString.xml");
            } else if (TYPE_SAME == type) {
                file = new File(FILE_SAME_PATH);
                System.out.println("开始写入相同字符------>sameString.xml");
            } else {
                file = new File(FILE_ANDROID_NO_FORMAT_PATH);
                System.out.println("开始写入no format  android 字符---->androidNoFormat.xml");
            }
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
            for (String key : androidPareList.keySet()) {
                String value = androidPareList.get(key);
                //创建一个元素，并把它追加到根元素的子元素
                Element student = document.createElement("string");
                element.appendChild(student);
                student.setAttribute("name", key);
                student.setTextContent(value);
                tf.transform(new DOMSource(document), new StreamResult(file));
            }
            System.out.println("android 字符写入完毕"+"\n");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 生成输出文档
     *
     * @param typeFormat
     * @param iosList
     */
    private static void toText( Map<String, String> iosList,int typeFormat) {
        File file = null;
        if (TYPE_COMMON_TEXT == typeFormat) {
            file = new File(FILE_COMMON_FINAL_PATH);
            System.out.println("开始写入android对应的所有字符串（最终版），(commonFinal.txt)对应的大小为"+iosList.size());
        } else  if (TYPE_IOS_TEXT==typeFormat){
            file = new File(FILE_IOS_FINAL_PATH);
            System.out.println("开始写入两端不统一字符串(最终版)(iosFinal.txt)，对应的大小为"+iosList.size());
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(osw);
            for (String s : iosList.keySet()) {
                iosList.get(s);
//                bufferedWriter.write("\"" + iosList.get(s) + "\"" + "               =               " + "\"" + iosList.get(s) + "\""+"\n");
                bufferedWriter.write(  iosList.get(s)+"\n");
//                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            osw.flush();
            fileOutputStream.flush();
            System.out.println("最终文件写入成功");
//            iosRemoveReader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取ios文件
     */
    public static void iosRemoveReader() {
        FileReader file = null;
        try {
            Map<String ,String > temp=iosList2;
            File file2 = new File(FILE_IOS_SOURCE_revmove_PATH);
            FileReader fileReader = new FileReader(file2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                System.out.println("开始读取ios源文件");
            }

            while (readLine != null) {
                // 去掉引号
                String s3 = readLine.replaceAll("\"", "");
                for (String key : temp.keySet()) {
                    if (key.equals(s3)){
                        iosList.remove(key);
                    }
                }
//
                readLine = bufferedReader.readLine();
            }
            System.out.println("读取ios源文件成功，大小为：" + iosList2.size());
            System.out.println("读取ios源文件成功，并且含有format文件的string有" + iosFormatList.size());
            System.out.println("读取ios源文件成功，并且不含有format文件的string有" + iosNoFormatList.size());
            System.out.println("ios 源文件读结束"+"\n");
            bufferedReader.close();
            fileReader.close();
//            toCompare(iosNoFormatList, TYPE_NO_FORMAT);
            toIosCompare(iosList, TYPE_ALL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符串比较(如果在android集合中，key对应的value可以取到对应的ios  value值，在生成相同的文件)
     *
     * @param iosList
     * @param typeFormat
     */
    private static void toIosCompare(Map<String, String> iosList, int typeFormat) {

        iosTempList = iosList;
        System.out.println("比较完毕，不同大小为" + iosTempList.size()+"\n");
        try {
            if (TYPE_ALL == typeFormat) {
                File file = new File(FILE_IOS_FORMAT_test_PATH);
                System.out.println("开始写入不同文件---->compare.txt");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "utf-8");
                BufferedWriter bufferedWriter = new BufferedWriter(osw);
                for (String s : iosTempList.keySet()) {
                    iosTempList.get(s);
                    bufferedWriter.write("\"" + iosTempList.get(s) + "\"");
                    bufferedWriter.newLine();
                }
                bufferedWriter.flush();
                osw.flush();
                fileOutputStream.flush();
                System.out.println("写入不同文件结束，写入大小为"+iosTempList.size()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
