package com.gl.eventscountdowntimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class EventsList extends AppCompatActivity {
    private ListView listView;
    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_DELETE = 333;

    private final List<Event> eventList = new ArrayList<Event>();
    private ArrayAdapter<Event> listViewAdapter;
    FloatingActionButton fab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Mes événements");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#282828"));
        actionBar.setBackgroundDrawable(colorDrawable);

        setContentView(R.layout.activity_events_list);

        // Get ListView object from xml
        this.listView = (ListView) findViewById(R.id.listView);

        MyDatabaseHelper db = new MyDatabaseHelper(this);

        List<Event> list=  db.getAllEvents();
        this.eventList.addAll(list);
        // Define a new Adapter
        // 1 - Context
        // 2 - Layout for the row
        // 3 - ID of the TextView to which the data is written
        // 4 - the List of data

        this.listViewAdapter = new ArrayAdapter<Event>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.eventList);

        // Assign adapter to ListView
        this.listView.setAdapter(this.listViewAdapter);

        // Register the ListView for Context menu
        registerForContextMenu(this.listView);

        fab = findViewById(R.id.add_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(EventsList.this, AddEditEventActivity.class);
                launcher.launch(addIntent);

            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo)    {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Choisir une action");

        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_VIEW , 0, "Afficher");
        menu.add(0, MENU_ITEM_EDIT , 1, "Modifier");
        menu.add(0, MENU_ITEM_DELETE, 3, "Supprimer");
    }

    // When AddEditEventActivity completed, it sends feedback.
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    boolean needRefresh = result.getData().getBooleanExtra("needRefresh", true);
                    // Refresh ListView
                    if (needRefresh) {

                        this.eventList.clear();
                        MyDatabaseHelper db = new MyDatabaseHelper(this);
                        List<Event> list = db.getAllEvents();
                        this.eventList.addAll(list);


                        // Notify the data change (To refresh the ListView).
                        this.listViewAdapter.notifyDataSetChanged();
                    }
                }
            });

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Event selectedEvent = (Event) this.listView.getItemAtPosition(info.position);
        if(item.getItemId() == MENU_ITEM_VIEW){
            String title = selectedEvent.getEventTitle();
            String eventDate = selectedEvent.getEventDate().toString();
            Intent intent = new Intent(this, ShowEvent.class);
            intent.putExtra("titre",title);
            intent.putExtra("date",eventDate);
            startActivity(intent); }
        else if(item.getItemId() == MENU_ITEM_EDIT ){
            Intent intent = new Intent(this, AddEditEventActivity.class);
            intent.putExtra("event", selectedEvent);
            launcher.launch(intent); }
        else if(item.getItemId() == MENU_ITEM_DELETE){
            // Ask before deleting.
            new AlertDialog.Builder(this)
                    .setMessage("Vous voulez supprimer "+selectedEvent.getEventTitle()+" ?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteEvent(selectedEvent); }
                    })
                    .setNegativeButton("Non", null)
                    .show();
        }
        else {
            return false;
        }
        return true;
    }

    // Delete a record
    private void deleteEvent(Event event)  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteEvent(event);
        this.eventList.remove(event);
        // Refresh ListView.
        this.listViewAdapter.notifyDataSetChanged();
    }


}