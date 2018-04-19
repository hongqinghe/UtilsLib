package middlem.person.utilsmodule.lint;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/***********************************************
 * <P> desc:  检测lint中提示UnusedResource字段，删除unUsedResource指定的resource文件
 * <P> Author: hehongqing
 * <P> Date: 2018/2/27 上午10:49
 ***********************************************/

public class LintDelete {
    private static final String BASE_PATH = "/Users/hehongqing/Android/middlem/BaseLib/sample/src/main/assets/lint/";
    private static final String LINT_FILE = "lint-results.xml";

    public static void main(String[] args) {
        toReadLint();
    }

    /**
     * 解析lint_xml
     */
    private static void toReadLint() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            File file = new File(BASE_PATH + LINT_FILE);
            Document document = documentBuilder.parse(file);
            NodeList issues = document.getElementsByTagName("issue");
            int matchCount = 0;
            int matchUnused = 0;
            String issueMessage = null, issueId = null;
            for (int i = 0; i < issues.getLength(); i++) {
                Node node = issues.item(i);
                NamedNodeMap attributes = node.getAttributes();

                //读取issue中的 里面元素
                for (int i1 = 0; i1 < attributes.getLength(); i1++) {
                    Node firstNode = attributes.item(i1);
                    String firstNodeName = firstNode.getNodeName();
//                    System.out.println(firstNodeName);
                    if (firstNodeName.equals("message")) {
                        issueMessage = firstNode.getNodeValue();
                    }
                    if (firstNodeName.equals("id")) {
                        issueId = firstNode.getNodeValue();
                    }
                }
                //获取里面节点-->   二级节点(location)
                if (issueId != null && issueId.equals("UnusedResources")) {
                    NodeList childNodes1 = node.getChildNodes();
                    matchCount++;
                    for (int i2 = 0; i2 < childNodes1.getLength(); i2++) {
                        if (childNodes1.item(i2).getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes1 = childNodes1.item(i2).getAttributes();
                            for (int i3 = 0; i3 < attributes1.getLength(); i3++) {
                                String nodeName1 = attributes1.item(i3).getNodeName();
                                String nodePath = attributes1.item(i3).getNodeValue();
                                if (nodeName1.equals("file")) {
                                    matchUnused++;
                                    matchXMLToDelete(issueMessage, nodePath);
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("取出路径" + matchUnused);
            System.out.println("总共的matchCount" + matchCount);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 解析nodePath  是那种资源文件
     *
     * @param issueMessage
     * @param nodePath
     */
    private static void matchXMLToDelete(String issueMessage, String nodePath) {
        if (nodePath.contains(LintConstants.ICONS_FILE_KEY)|| nodePath.contains(LintConstants.OTHER_STRING_KEY)||nodePath.contains(LintConstants.INTEGER_FILE_KEY)){
            return;
        }else if (nodePath.contains(LintConstants.STRING_FILE_KEY) || nodePath.contains(LintConstants.COLOR_FILE_KEY) ||
                nodePath.contains(LintConstants.STYLE_FILE_KEY) || nodePath.contains(LintConstants.DIMENS_FILE_KEY)
                || nodePath.contains(LintConstants.BOOLS_FILE_KEY)) {
            XMLNodeDelete.deleteXmlNode(messageExtract(issueMessage), nodePath);
        } else {
            toDeleteFile(nodePath);
        }
    }

    /**
     * 截取message中的有效信息  resourceID
     *
     * @param issueMessage
     * @return
     */
    private static String messageExtract(String issueMessage) {
        String result = null;
        String[] split = issueMessage.split("`");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("R.")) {
                String[] split1 = split[i].split("\\.");
                result = split1[split1.length - 1];
            }
        }
        return result;
    }

    /**
     * 删除指定目录的文件
     *
     * @param nodePath
     */
    private static void toDeleteFile(String nodePath) {
        File file = new File(nodePath);
        if (!file.exists()) {
            System.out.println("找不到指定的文件 ,请检查文件是不是存在，文件路径为：" + nodePath);
        } else if (file.isFile()) {
            boolean delete = file.delete();
            System.out.println(delete ? "文件删除成功" : "文件删除失败");
        }
    }
}
