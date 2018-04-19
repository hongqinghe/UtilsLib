package middlem.person.utilsmodule.lint;

/***********************************************
 * <P> desc:
 * <P> Author: hehongqing
 * <P> Date: 2018/3/7 上午9:32
 ***********************************************/

public class Test {
    static String test="The resource `R.string.umeng_socialize_text_tencent_key` appears to be unused";
    public static void main(String[] args) {
        String[] split = test.split("`");
        for (int i = 0; i < split.length; i++) {
        if (split[i].contains("R.")){
            String[] split1 = split[i].split("\\.");
            System.out.println(split1);
        }
        }
        String s = split[1];
        System.out.println("s字符串为："+s+"\n");
        String substring = s.substring(9);
        System.out.println("s1字符串为："+substring+"\n");
    }
}
