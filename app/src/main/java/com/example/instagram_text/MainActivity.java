package com.example.instagram_text;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ed;
    TextView t;
    SpannableString str;
    String s1;
    int st, inat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.edit1);
        t = findViewById(R.id.textView);
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.e("Before ", start + ", " + count + ", " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.e("ontext ", start + ", " + count + ", " + before);
                s1 = ed.getText().toString();
                str = new SpannableString(s1);
                st = 0;
                while (true) {
                    inat = s1.indexOf('\n', st);
                    if (inat!=-1)
                        str.setSpan(new RelativeSizeSpan(getSize(inat,st)), st, inat, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    else{
                        Log.e("val",st+"");
                        str.setSpan(new RelativeSizeSpan(getSize(s1.length(),st)), 0, s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    }
                    st = inat+1;
                }

                t.setText(str);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Log.e("after ", s.toString().length() + "");

            }
        });
    }

    public float getSize(int a,int b) {
        int x = a-b;
        if (x<5) return 1f;
        else if (x>4 &&x<9) return 0.75f;
        else if (x>8 &&x<13) return 0.5f;
        else return 0.25f;
    }
}
