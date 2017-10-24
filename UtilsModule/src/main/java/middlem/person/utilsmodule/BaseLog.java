package middlem.person.utilsmodule;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/***********************************************
 *
 * <P> desc:    log
 * <P> Author: gongtong
 * <P> Date: 2017-10-24 21:03
 ***********************************************/

public class BaseLog {
    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int WTF = 0x6;
    public static final int JSON = 0x7;
    public static final int XML = 0x8;

    private final int JSON_INDENT = 4;
    private final String LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * 显示默认Log
     * @param type
     * @param tag
     * @param msg
     */
    public void printDefault(int type, String tag, String msg) {

        int index = 0;
        int maxLength = 2000;
        int countOfSub = msg.length() / maxLength;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                printSub(type, tag,sub);
                index += maxLength;
            }
            printSub(type, tag, msg.substring(index, msg.length()));
        } else {
            printSub(type, tag, msg);
        }
    }

    public synchronized void printJson(String tag, String msg, String... headers) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(D, tag, true);
        if (headers != null) {
            for (String header : headers) {
                if (!StringUtils.isStrEmpty(header)) {
                    message = header + LINE_SEPARATOR + message;
                }
            }
        }

        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            printDefault(D, tag, "║ " + line);
        }
        printLine(D, tag, false);
    }

    public void printParams(String tag, HashMap<String,String> params, String... headers) {
        printLine(I, tag, true);
        if (headers != null) {
            for (String header : headers) {
                if (!StringUtils.isStrEmpty(header)) {
                    printDefault(I, tag, "║ " + header);
                }
            }
        }

        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            if (StringUtils.isStrEmpty(val)) {
                printDefault(BaseLog.E, tag, "║ " + key + "-->" + val);
            } else {
                printDefault(BaseLog.I, tag, "║ " + key + "-->" + val);
            }
        }

        printLine(I, tag, false);
    }

    private void printSub(int type, String tag, String sub) {
        switch (type) {
            case V:
                Log.v(tag, sub);
                break;
            case D:
                Log.d(tag, sub);
                break;
            case I:
                Log.i(tag, sub);
                break;
            case W:
                Log.w(tag, sub);
                break;
            case E:
                Log.e(tag, sub);
                break;
            case WTF:
                Log.wtf(tag, sub);
                break;
        }
    }

    public void printLine(int type, String tag, boolean isTop) {
        if (isTop) {
            printDefault(type, tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            printDefault(type, tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
