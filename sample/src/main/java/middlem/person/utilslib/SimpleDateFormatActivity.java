package middlem.person.utilslib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class SimpleDateFormatActivity extends AppCompatActivity {

    private TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format);
        textContent = findViewById(R.id.content_tv);
    }
    //simpleDateFormat
    public void format1(View view) {

    }
    public void format2(View view) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat()
    }
    public void setTextContent(String content){
        textContent.setText(content);
    }
}
