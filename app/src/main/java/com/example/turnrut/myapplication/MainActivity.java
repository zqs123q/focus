package com.example.turnrut.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button bt;
    private EditText et;
    private String lastStr;
    private boolean pi = true;
    private int maxFail = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setUpEvents();
    }

    private void initView() {
        tv = findViewById(R.id.my_text);
        bt = findViewById(R.id.my_button);
        et = findViewById(R.id.my_input);
    }

    private void setUpEvents() {
        et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (KeyEvent.KEYCODE_ENTER == i && KeyEvent.ACTION_DOWN == keyEvent.getAction()){
                    checkData();
                    return true;
                } else if (et.getText().toString().length() >= lastStr.length()){
                    checkData();
                    return true;
                }
                return false;
            }
        });
    }

    private void initData() {
        lastStr = randomIntStr();
        tv.setText(lastStr);
        tv.setTextColor(Color.RED);
    }

    int currentFail = 0;
    private void checkData() {
        if (!et.getText().toString().equals(lastStr)) {
            fail();
            currentFail++;
            if (currentFail >= maxFail){
                currentFail = 0;
                initData();
            }
            et.setText("");
            return;
        }
        et.setText("");
        updateData();
    }

    long time = System.currentTimeMillis();
    private void fail() {
        if (System.currentTimeMillis() - time < 2000)
            return;
        time = System.currentTimeMillis();
        Toast.makeText(getApplicationContext(), "fail",
                Toast.LENGTH_SHORT).show();
    }

    private void updateData() {
        lastStr = tv.getText().toString();
        tv.setTextColor(Color.BLACK);
        tv.setText(randomIntStr());
    }

    private int randomInt(int begin, int end){
        return  (int)(Math.random() * (end - begin)) + begin;
    }

    private String randomIntStr() {
        return String.format("%d", randomInt(10000, 99999));
    }
}
