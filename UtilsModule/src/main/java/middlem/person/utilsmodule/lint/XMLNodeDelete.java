package middlem.person.utilsmodule.lint;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/***********************************************
 * <P> desc:   删除指定xml文件的具体节点
 * <P> Author: hehongqing
 * <P> Date: 2018/3/5 下午1:59
 ***********************************************/

public class XMLNodeDelete {
    /**
     *
     * @param nodeName  需要删除的节点名称
     * @param filePath  删除文件的路径
     */
    public static void deleteXmlNode(String nodeName,String filePath){
        //解析xml
        DocumentBuilderFactory  builderFactory=DocumentBuilderFactory.newInstance();
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();


            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            File file=new File(filePath);
            Document document = documentBuilder.parse(file);
            Element documentElement = document.getDocumentElement();
            NodeList childNodes = documentElement.getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {
                String nodeName1 = childNodes.item(i).getNodeName();
                if (nodeName1.equals( getFileType(filePath))){
                String name = childNodes.item(i).getAttributes().getNamedItem("name")
                        .getNodeValue();
                    if (nodeName.equals(name)) {
                        //节点中删除指定节点
                        childNodes.item(i).getParentNode().removeChild(childNodes.item(i));
                    }
                }
            }
            //删除节点后输出剩余内容
            for (int i1 = 0; i1 < childNodes.getLength(); i1++) {
                Node node=childNodes.item(i1);
                DOMSource source = new DOMSource(node.getParentNode());
                StreamResult result = new StreamResult(new File(filePath));
                transformer.transform(source, result);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static String getFileType(String filePath) {
        String type;
        if (filePath.contains(LintConstants.STRING_FILE_KEY)) {
            type=LintConstants.STRING_FILE_VALUE;
        }else if (filePath.contains(LintConstants.COLOR_FILE_KEY)) {
            type = LintConstants.COLOR_FILE_VALUE;
        }else if (filePath.contains(LintConstants.STYLE_FILE_KEY)){
            type=LintConstants.STYLE_FILE_VALUE;
        }else if (filePath.contains(LintConstants.BOOLS_FILE_KEY)){
            type=LintConstants.BOOLS_FILE_VALUE;
        }else {
            type="";
        }
        return type;
    }
}
