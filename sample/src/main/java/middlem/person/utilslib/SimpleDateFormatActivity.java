package middlem.person.utilslib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimpleDateFormatActivity extends AppCompatActivity {

    private TextView textContent;
    private EditText pattern,formatContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format);
        textContent = findViewById(R.id.content_tv);
        pattern = findViewById(R.id.pattern);
        formatContent = findViewById(R.id.format);
    }
    public void dateFormat(View view) {

        Editable text = pattern.getText();
        String s = "\"" + text.toString() + "\"";
        SimpleDateFormat simpleDateFormat1= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat1.applyPattern(s);
        String format = simpleDateFormat1.format(new Date());
        setTextContent(format);
    }
    public void dateTimeFormat(View view) {

        Editable text = pattern.getText();
        SimpleDateFormat simpleDateFormat2= (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat();
        simpleDateFormat3.applyPattern("yyyy-mm-dd");
        String format = simpleDateFormat3.format(new Date());
        setTextContent(format);
    }

    public void setTextContent(String content) {
        textContent.setText(content);
    }



        public static void main(String[] args) {
            SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒", Locale.getDefault());
            SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm", Locale.getDefault());
            SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());//等价于now.toLocaleString()
            SimpleDateFormat myFmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ", Locale.getDefault());
            SimpleDateFormat myFmt4=new SimpleDateFormat(
                    "一年中的第 D 天 一年中第w个星期 一月中第W个星期 在一天中k时 z时区", Locale.getDefault());
            Date now=new Date();
            System.out.println(myFmt.format(now));
            System.out.println(myFmt1.format(now));
            System.out.println(myFmt2.format(now));
            System.out.println(myFmt3.format(now));
            System.out.println(myFmt4.format(now));
            System.out.println(now.toGMTString());
            System.out.println(now.toLocaleString());
            System.out.println(now.toString());
        }


}
