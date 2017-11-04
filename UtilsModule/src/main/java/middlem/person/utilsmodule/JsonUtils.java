package middlem.person.utilsmodule;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/***********************************************
 * <P> Json解析类
 * <P> Author: MaBao
 * <P> Date: 2017-04-21 11:21
 * <P> Copyright © 2008 二维火科技
 ***********************************************/
public class JsonUtils {
    public final static Short COMPILETYPE_MOCK_FIRST = (short) 1;

    public final static Short COMPILETYPE_API_FIRT = (short) 2;

    private ObjectMapper objectMapper = null;
    private static JsonUtils jsonUtils = null;

    public static JsonUtils getUtils(){
        if (jsonUtils == null){
            synchronized (JsonUtils.class){
                if (jsonUtils == null){
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.setDateFormat(new SimpleDateFormat(DateUtils.FORMAT_FULL));
                    jsonUtils = new JsonUtils(objectMapper);
                }
            }
        }
        return jsonUtils;
    }

    public JsonUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 获取下个值
     * @param name
     * @param result
     * @return
     */
    public String getNodeText(String name, String result) {
        try {
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            JsonNode node = objectMapper.readTree(result);
            if (node == null) {
                return null;
            }
            node = node.get(name);
            if (node == null) {
                return null;
            }
            return node.toString();
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T fromJSON(String name, String result, Class<T> clazz) {
        try {
            String json = getNodeText(name, result);
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            return objectMapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public <T> List<T> fromJSONList(String name, String result, Class<T> clazz) {
        try {
            String json = getNodeText(name, result);
            if (TextUtils.isEmpty(json)) {
                return null;
            }

            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,clazz);
            List<T> lst =  (List<T>)objectMapper.readValue(json, javaType);
            return lst;
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 非标准的json格式 传回来是一个字符串格式 先转化为标准的字符串格式再转化为对应的对象.
     * @param name
     * @param result
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T fromJSONString(String name, String result, Class<T> clazz) {
        try {
            String json = getNodeText(name, result);
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            String tempjson = objectMapper.readValue(json,String.class);
            return objectMapper.readValue(tempjson, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 非标准的json格式 传回来是一个字符串格式 先转化为标准的字符串格式再转化为对应的 list<T> 对象.
     * @param name
     * @param result
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> fromJSONListString(String name, String result, Class<T> clazz) {

        try {
            String json = getNodeText(name, result);
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            String tempjson = objectMapper.readValue(json,String.class);
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,clazz);
            List<T> lst =  (List<T>)objectMapper.readValue(tempjson, javaType);
            return lst;
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public String toJsonString(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String compile(String jsonstr1, String jsonstr2, Short compielType) {
        JsonNode json1 = null;
        JsonNode json2 = null;
        try {
            json1 = objectMapper.readTree(jsonstr1);
            json2 = objectMapper.readTree(jsonstr2);
            Map<String,String> map = compareJson(json1, json2, "");
            System.out.println(map.keySet().toString());
            if(map!=null && !map.isEmpty()){
                if(COMPILETYPE_MOCK_FIRST.equals(compielType)){
                    return "mock 比 api多的key如下："+ map.keySet().toString();
                }else if(COMPILETYPE_API_FIRT.equals(compielType)){
                    return "api 比 mock多的key如下："+map.keySet().toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return "";

    }

    /**
     * 得到key的正确显示。
     *
     * @param parentKey
     * @param key
     * @return
     */
    private  String getkey(String parentKey, String key) {
        if (parentKey == null || parentKey.trim().length() == 0) {
            return key;
        } else {
            return parentKey + "-" + key;
        }
    }

    /**
     * 对比两个JsonNode对象将不同的key存入到返回的map中.
     * @param json1
     * @param json2
     * @param key
     * @return
     */
    public  Map<String, String> compareJson(JsonNode json1, JsonNode json2, String key){
        Map<String, String> resultMap = new HashMap<>();
        String keyName = key;
        if (json1 == null || json2 == null) {
            return resultMap;
        }
        Iterator<String> paramsIterator = json1.fieldNames();//获得一个迭代器 存储jsonNode的所有key
        while (paramsIterator.hasNext()) {
            String paramName = paramsIterator.next();//得到一个key
            JsonNode jsonSonNode1 = json1.get(paramName);
            JsonNode jsonSonNode2 = json2.get(paramName);
            if (jsonSonNode1 != null && jsonSonNode2 != null) {
                if (jsonSonNode1.getNodeType().equals(jsonSonNode2.getNodeType())) {
                    if (jsonSonNode1.isObject() && jsonSonNode2.isObject()) {
                        Map<String, String> tempResultMap = compareJson(jsonSonNode1, jsonSonNode2, getkey(keyName, paramName));
                        resultMap.putAll(tempResultMap);
                    } else if (jsonSonNode1.isArray() && jsonSonNode2.isArray()) {
                        Iterator<JsonNode> nodeIterator1 = jsonSonNode1.elements();//获取数组里的值
                        Iterator<JsonNode> nodeIterator2 = jsonSonNode2.elements();//获取数组里的值
                        //只取一组数据进行比较
	                    if(nodeIterator1.hasNext() && nodeIterator2.hasNext()) {
		                    JsonNode jsonNode1 = nodeIterator1.next();//
		                    JsonNode jsonNode2 = nodeIterator2.next();//
		                    Map<String, String> tempResultMap = compareJson(jsonNode1, jsonNode2, getkey(keyName, paramName));
		                    resultMap.putAll(tempResultMap);
	                    }
                    }
                } else {
                    resultMap.put(getkey(keyName, paramName), getkey(keyName, paramName));
                }
            } else {
                resultMap.put(getkey(keyName, paramName), getkey(keyName, paramName));
            }
        }
        return resultMap;
    }


    /**
     * 传入指定的Json传转化为Map对象.
     * @param objectMapper
     * @param object
     * @return
     */
    private  Map transJsontoMap(ObjectMapper objectMapper,String object){
        Map<String, Object> resMap = new HashMap<String, Object>();
        // 将json字符串转换成jsonObject
        JsonNode jsonObject = null;
        try {
            jsonObject = objectMapper.readTree(object);
            resMap = JsonToMap(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  resMap;
    }

    private  List<Map<String, Object>> json2List(ObjectMapper objectMapper,Object json) {
        ArrayNode jsonArr = (ArrayNode) json;
        List<Map<String, Object>> arrList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < jsonArr.size(); ++i) {
            arrList.add(transJsontoMap(objectMapper,jsonArr.get(i).toString()));
        }
        return arrList;
    }

    //jsonNode转成map
    public  Map<String, Object> JsonToMap(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }
        Iterator<String> paramsIterator = jsonNode.fieldNames();//获得一个迭代器 存储jsonNode的所有key
        Map<String, Object> map = new HashMap();
        while (paramsIterator.hasNext()) {
            String paramName = paramsIterator.next();//得到一个key
            JsonNode jsonSonNode = jsonNode.get(paramName);
            if (jsonSonNode.isObject()) {//如果是对象
                Map<String, Object> tempMap = JsonToMap(jsonSonNode);//递归调用
                if (tempMap != null) {
                    map.put(paramName, tempMap);
                }
            }  else if (jsonSonNode.isArray()) {//如果是数组
                Iterator<JsonNode> nodeIterator = jsonSonNode.elements();//获取数组里的值
                ArrayList<Map<String, Object>> tempMaplist = new ArrayList<Map<String, Object>>();
                while (nodeIterator.hasNext()) {
                    JsonNode tempJsonNode = nodeIterator.next();//得到数组里的一个对象
                    Map<String, Object> tempM = JsonToMap(tempJsonNode);//递归调用
                    if (tempM != null) {
                        tempMaplist.add(tempM);
                    }
                }
                map.put(paramName, tempMaplist);
            }else {
                map.put(paramName,jsonSonNode);
            }
        }
        return map;
    }
}
