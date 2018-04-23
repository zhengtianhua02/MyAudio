package com.example.myaudio;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ComponentName component; // 用于启动服务
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component = new ComponentName(this, MySerivces.class);
    }
    public void myStart(View view){
        Intent intent=new Intent(MySerivces.PLAY_ACTION);
        intent.setComponent(component);
        startService(intent);
        Toast.makeText(this,"1234",Toast.LENGTH_LONG).show();
    }
}
