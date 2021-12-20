package com.gl.eventscountdowntimer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class ShowEvent extends AppCompatActivity{

    private TextView eventTitle;
    private TextView eventDate;
    private CountdownView myCvCountdownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mon événement");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#282828"));
        actionBar.setBackgroundDrawable(colorDrawable);
        setContentView(R.layout.activity_show_event);

        this.eventTitle = (TextView)this.findViewById(R.id.title);
        this.eventDate = (TextView)this.findViewById(R.id.date);

        this.myCvCountdownView = (CountdownView) this.findViewById(R.id.countdownView);

        Intent intent = this.getIntent();
        String title = intent.getStringExtra("titre");
        eventTitle.setText(title);

        String date = intent.getStringExtra("date");
        eventDate.setText(date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE d MMM yyyy");

      try {
           Date newdate = null;
           newdate = simpleDateFormat.parse(date);
           Date now = new Date();
            long currentDate = now.getTime();
            long pickerDate = newdate.getTime();
            long countDownToPickerDate = pickerDate - currentDate;
             myCvCountdownView.start(countDownToPickerDate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}