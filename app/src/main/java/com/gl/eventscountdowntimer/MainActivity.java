package com.gl.eventscountdowntimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        this.buttonStart = (Button)findViewById(R.id.startbtn);

        this.buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent intent = new Intent(MainActivity.this, EventsList.class);
                startActivity(intent);
            }
        });
    }

}
