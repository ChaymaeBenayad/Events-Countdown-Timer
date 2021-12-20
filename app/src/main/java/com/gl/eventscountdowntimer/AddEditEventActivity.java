package com.gl.eventscountdowntimer;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import java.text.DateFormat;
import java.util.Calendar;

public class AddEditEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText textTitle;
    private TextView dateContent;

    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;


    private Button buttonSave;
    private Button buttonCancel;
    private Button btnDatePicker;

    private Event event;
    private boolean needRefresh;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_edit_event);

        this.textTitle = (EditText)this.findViewById(R.id.editText_note_title);
        this.dateContent = (TextView)this.findViewById(R.id.date_content);

        this.buttonSave = (Button)findViewById(R.id.button_save);
        this.buttonCancel = (Button)findViewById(R.id.button_cancel);
        this.btnDatePicker = findViewById(R.id.bntDatePicker);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }}
        );

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                buttonSaveClicked();
            }
        });

        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                buttonCancelClicked();
            }
        });

        Intent intent = this.getIntent();
        this.event = (Event) intent.getSerializableExtra("event");
        if(event == null)  {
            this.mode = MODE_CREATE;
        } else  {
            this.mode = MODE_EDIT;
            this.textTitle.setText(event.getEventTitle());
            this.dateContent.setText(event.getEventDate());
        }
    }

    // User Click on the Save button.
    public void buttonSaveClicked()  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);

        String title = this.textTitle.getText().toString();
        String dateContent = this.dateContent.getText().toString();

        if(title.equals("") || dateContent.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Veuillez remplir les deux champs", Toast.LENGTH_LONG).show();
            return;
        }

        if(mode == MODE_CREATE ) {
            this.event = new Event(title,dateContent);
            db.addEvent(event);
        } else if (mode == MODE_EDIT){
            this.event.setEventTitle(title);
            this.event.setEventDate(dateContent);
            db.updateEvent(event);
        }

        this.needRefresh = true;

        // Back to EventsList.
        this.onBackPressed();
    }

    // User Click on the Cancel button.
    public void buttonCancelClicked()  {
        // Do nothing, back EventsList.
        this.onBackPressed();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR , year);
        calendar.set(calendar.MONTH , month);
        calendar.set(calendar.DAY_OF_MONTH , dayOfMonth);

        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView tvDatePicker = findViewById(R.id.date_content);
            tvDatePicker.setText(pickerDateString);

    }

    @Override
    public void finish() {

        // Create Intent
        Intent data = new Intent();

        // Request EventsList refresh its ListView (or not).
        data.putExtra("needRefresh", needRefresh);

        // Set Result
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

}