package middlem.person.utilslib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;

import middlem.person.utilsmodule.JsonUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toJson(View view) {
        String aa="ss\"\\ssss ss";

//        String message = "{\"alert\":\"有新的采购单\",\"extras\":[{\"sourceId\":\"999280115f567566015f7cb61e040d62\",\"messageSubscId\":\"999280075f4d064f015f7cb642940740\",\"sourceFromJPush\":\"JPush\",\"messageType\":\"306\",\"unreadCount\":\"1\",\"entityId\":\"99928007\",\"selfEntityId\":\"99928007\"}],\"title\": \"有新的采购单\"}";
//        String message = "{\"alert\":\"有新的采购单\",\"extras\":{\"sourceId\":\"999280115f567566015f7cb61e040d62\",\"messageSubscId\":\"999280075f4d064f015f7cb642940740\",\"sourceFromJPush\":\"JPush\",\"messageType\":\"306\",\"unreadCount\":\"1\",\"entityId\":\"99928007\",\"selfEntityId\":\"99928007\"},\"title\": \"有新的采购单\"}";
//        String message = "{ \"alert\": \"有新的采购单\" \"extras\": \"{\"sourceId\":\\\"999280115f567566015f7cb61e040d62\\\",\\\"messageSubscId\\\":\\\"999280075f4d064f015f7cb642940740\\\",\\\"sourceFromJPush\\\":\\\"JPush\\\",\\\"messageType\\\":\\\"306\\\",\\\"unreadCount\\\":\\\"1\\\",\\\"entityId\\\":\\\"99928007\\\",\\\"selfEntityId\\\":\\\"99928007\\\"}\", \"title\": \"有新的采购单\" }";
        String message = "{\"extras\":{\"sourceId\":\"999280115f567566015f7cb61e040d62\",\"messageSubscId\":\"999280075f4d064f015f7cb642940740\",\"sourceFromJPush\":\"JPush\",\"messageType\":\"306\",\"unreadCount\":\"1\",\"entityId\":\"99928007\",\"selfEntityId\":\"99928007\"}}";
        Test missileMessage = new Gson().fromJson("{ \"alert\": \"有新的采购单\" \"extras\": \"{\\\"sourceId\\\":\\\"999280115f567566015f7cb61e040d62\\\",\\\"messageSubscId\\\":\\\"999280075f4d064f015f7cb642940740\\\",\\\"sourceFromJPush\\\":\\\"JPush\\\",\\\"messageType\\\":\\\"306\\\",\\\"unreadCount\\\":\\\"1\\\",\\\"entityId\\\":\\\"99928007\\\",\\\"selfEntityId\\\":\\\"99928007\\\"}\", \"title\": \"有新的采购单\"}", Test.class);


//        Test missileMessage =JsonUtils.getUtils().fromJSONString("",message, Test.class);
//        MissileMessage extras = missileMessage.getExtras();
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        String messageType1 = extras.getMessageType();
        try {
            Test messageType = objectMapper.readValue(message, Test.class);
//            MessageVo vo = objectMapper.readValue(messageType1, MessageVo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Log.d("tag",extras.getMessageType());
        Log.i("tag", missileMessage.toString());

    }

    public static void main(String[] args) {
        Test missileMessage = new Gson().fromJson("{ \"alert\": \"有新的采购单\" ,\"extras\": \"{\\\"sourceId\\\":\\\"999280115f567566015f7cb61e040d62\\\",\\\"messageSubscId\\\":\\\"999280075f4d064f015f7cb642940740\\\",\\\"sourceFromJPush\\\":\\\"JPush\\\",\\\"messageType\\\":\\\"306\\\",\\\"unreadCount\\\":\\\"1\\\",\\\"entityId\\\":\\\"99928007\\\",\\\"selfEntityId\\\":\\\"99928007\\\"}\", \"title\": \"有新的采购单\"}", Test.class);
    }
}
