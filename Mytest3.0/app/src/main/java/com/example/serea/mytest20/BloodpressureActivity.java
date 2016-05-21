package com.example.serea.mytest20;

import android.app.Activity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * Created by Serea on 2016/5/8.
 */
public class BloodpressureActivity extends Activity{

    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloodpressure_main);

        button1=(Button)findViewById(R.id.buttonback);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BloodpressureActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}

