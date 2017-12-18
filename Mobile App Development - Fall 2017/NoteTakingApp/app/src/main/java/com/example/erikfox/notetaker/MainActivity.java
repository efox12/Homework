/**
 * This class contains information and methods for the notes home screen.
 * CPSC 312-01, Fall 2017
 * Programming Assignment #7
 * Icon Sources:
 * Personal icon made by Freepik from www.flaticon.com
 * Work icon made by Freepik from www.flaticon.com
 * Other icon made by Smashicons from www.flaticon.com
 * School icon made by Freepik from www.flaticon.com
 *
 * @author Erik Fox
 * @version v1.0 11/14/17
 */

package com.example.erikfox.notetaker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int NEW_NOTE_REQUEST_CODE = 1;
    final int EXISTING_NOTE_REQUEST_CODE = 2;

    private SimpleCursorAdapter cursorAdapter;
    private NoteDatabaseHelper databaseHelper;
    private List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create listview
        final ListView list = new ListView(this);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        // contextual action mode
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            List<Boolean> isSelected = new ArrayList<Boolean>();
            List<Long> selected = new ArrayList<Long>();
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                // modify the lists when items are selected/deselected
                int numSelected = list.getCheckedItemCount();
                actionMode.setTitle(numSelected + " selected");
                selected.set(i, l);
                isSelected.set(i, b);
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                // inflate the menu
                MenuInflater menuInflater = actionMode.getMenuInflater();
                menuInflater.inflate(R.menu.cam_menu, menu);
                // create the lists to keep track of selected items
                notes = databaseHelper.getSelectAllNotesList();
                for(int i = 0; i<notes.size();i++) {
                    isSelected.add(false);
                    selected.add(Long.valueOf(0));
                }
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                switch(menuId) {
                    case R.id.deleteMenuAction:
                        // delete all selected items
                        for (int k = 0; k < selected.size(); k++) {
                            if(isSelected.get(k))
                                databaseHelper.deleteNoteByID(selected.get(k).intValue());
                        }
                        // clear lists
                        isSelected.clear();
                        selected.clear();
                        actionMode.finish();

                        // create new cursor
                        Cursor cursor = databaseHelper.getSelectAllNotesCursor();
                        cursorAdapter.changeCursor(cursor);
                        notes = databaseHelper.getSelectAllNotesList();
                        return true;
                    default:
                        return true;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        // create database and cursor
        databaseHelper = new NoteDatabaseHelper(this);
        Cursor cursor = databaseHelper.getSelectAllNotesCursor();

        // custom cursor adapter
        cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.note_list,
                cursor,
                new String[] {NoteDatabaseHelper.TITLE},
                new int[] {android.R.id.text1},
                0
        ){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Note currentNote = databaseHelper.getSelectAllNotesList().get(position);
                ImageView textView1= (ImageView) view.findViewById(R.id.imageView1);
                TextView textView2= (TextView) view.findViewById(R.id.textView1);
                if(currentNote.getType().compareTo("Other") == 0) {
                    textView1.setImageResource(R.drawable.other);
                }
                else if(currentNote.getType().compareTo("Work") == 0) {
                    textView1.setImageResource(R.drawable.work);
                }
                else if(currentNote.getType().compareTo("School") == 0) {
                    textView1.setImageResource(R.drawable.school);
                }
                else if(currentNote.getType().compareTo("Personal") == 0) {
                    textView1.setImageResource(R.drawable.personal);
                }
                textView2.setText(currentNote.getTitle());
                return view;
            }
        };

        list.setAdapter(cursorAdapter);
        list.setPadding(30,0,0,0);
        setContentView(list);

        // run this when an item in the listview is clicked
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                // load an array with all of the strings titles
                String[] list = new String[databaseHelper.getSelectAllNotesList().size()];
                for (int j = 0; j < databaseHelper.getSelectAllNotesList().size(); j++) {
                    list[j] = databaseHelper.getSelectAllNotesList().get(j).getTitle();
                }
                // load extras
                notes = databaseHelper.getSelectAllNotesList();
                intent.putExtra("list", list);
                intent.putExtra("content", notes.get(i).getContent());
                intent.putExtra("title", notes.get(i).getTitle());
                intent.putExtra("type", notes.get(i).getType());
                intent.putExtra("note", (int)l);
                startActivityForResult(intent, EXISTING_NOTE_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the options menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuId = item.getItemId();
        switch (menuId){
            case R.id.addMenuItem:
                // add item to the menu
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                // load an array with all of the strings titles
                String[] list = new String[databaseHelper.getSelectAllNotesList().size()];
                for (int j = 0; j < databaseHelper.getSelectAllNotesList().size(); j++) {
                    list[j] = databaseHelper.getSelectAllNotesList().get(j).getTitle();
                }
                // load the extras
                intent.putExtra("list", list);
                startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
                return true;
            case R.id.deleteMenueItem:
                // delete all items in the list
                // set up alert dialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(getString(R.string.alertTitle))
                        .setMessage(getString(R.string.alertDiscription1))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener(){
                            // delete the note if clicked
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                // delete all notes
                                databaseHelper.deleteAllNotes();
                                // create a new cursor
                                Cursor cursor = databaseHelper.getSelectAllNotesCursor();
                                cursorAdapter.changeCursor(cursor);

                            }
                        })
                        .setNegativeButton(getString(R.string.no),
                                new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int j) {

                                    }
                                });
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // the result of closing the activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if returning from creating a note run this
        if (requestCode == NEW_NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get the extras
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String spinner = data.getStringExtra("type");

            // create a note with the values and add it to the list of notes
            Note note = new Note(title, content, spinner);
            databaseHelper.insertNote(note);
            // create a new cursor
            Cursor cursor = databaseHelper.getSelectAllNotesCursor();
            cursorAdapter.changeCursor(cursor);
            notes = databaseHelper.getSelectAllNotesList();

        }
        // if returning from editing a note run this
        if (requestCode == EXISTING_NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get the extras
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String type = data.getStringExtra("type");
            int i = data.getIntExtra("note", 0);

            // update the note with new values
            Note note = new Note(title, content, type);
            databaseHelper.updateNoteById(i, new Note(title, content, type));
            databaseHelper.updateNoteById(i, note);
            // create a new cursor
            Cursor cursor = databaseHelper.getSelectAllNotesCursor();
            cursorAdapter.changeCursor(cursor);
            notes = databaseHelper.getSelectAllNotesList();
        }
    }
}

