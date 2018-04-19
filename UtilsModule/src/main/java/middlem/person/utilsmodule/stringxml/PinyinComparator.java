package middlem.person.utilsmodule.stringxml;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.util.Comparator;
/** 
 * 汉字按照拼音排序的比较器 
 * @author KennyLee 2009-2-23 10:08:59 
 *  
 */  
public class PinyinComparator implements Comparator<Object> {  
    public int compare(Object o1, Object o2) {  
        char c1 = ((String) o1).charAt(0);  
        char c2 = ((String) o2).charAt(0);
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        return concatPinyinStringArray(  
                PinyinHelper.toHanyuPinyinStringArray(c1,t3)).compareTo(
                concatPinyinStringArray(PinyinHelper  
                        .toHanyuPinyinStringArray(c2,t3)));
    }  
    private String concatPinyinStringArray(String[] pinyinArray) {  
        StringBuffer pinyinSbf = new StringBuffer();  
        if ((pinyinArray != null) && (pinyinArray.length > 0)) {  
            for (int i = 0; i < pinyinArray.length; i++) {  
                pinyinSbf.append(pinyinArray[i]);  
            }  
        }  
        return pinyinSbf.toString();  
    }  
}  