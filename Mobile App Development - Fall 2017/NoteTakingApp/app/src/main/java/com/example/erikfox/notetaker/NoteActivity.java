/**
 * This class contains information and methods for the notes activiy.
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

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private List<String> categories;
    private EditText editText;
    private EditText editText1;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create scroll view
        ScrollView scrollView = new ScrollView(this);

        // set up scroll view
        ViewGroup.LayoutParams scrollLayoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(scrollLayoutParams);
        scrollView.setBackgroundColor(Color.WHITE);
        scrollView.setPadding(25, 0, 25, 0);


        // create grid layout
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(2);

        // set up grid layout
        ViewGroup.LayoutParams gridLayoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        gridLayout.setLayoutParams(gridLayoutParams);


        // create EditText for title
        editText = new EditText(this);
        editText.setHint(getString(R.string.title));
        editText.setText(getIntent().getStringExtra("title"));

        // set up EditText for title
        GridLayout.Spec titleRowSpec = GridLayout.spec(0);
        GridLayout.Spec titleColumnSpec = GridLayout.spec(0, 2);
        GridLayout.LayoutParams titleLayoutParams =
                new GridLayout.LayoutParams(titleRowSpec, titleColumnSpec);
        titleLayoutParams.setGravity(Gravity.FILL_HORIZONTAL);
        editText.setLayoutParams(titleLayoutParams);


        // create spinner
        spinner = new Spinner(this);
        // list for spinner
        categories = new ArrayList<>();
        categories.add(getString(R.string.school));
        categories.add(getString(R.string.work));
        categories.add(getString(R.string.other));
        categories.add(getString(R.string.personal));

        // adapter for spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, android.R.id.text1, categories){
            @NonNull
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);

                ImageView textView1 = (ImageView) view.findViewById(android.R.id.icon);
                TextView textView2 = (TextView) view.findViewById(android.R.id.text1);

                if (categories.get(position).compareTo("Other") == 0) {
                    textView1.setImageResource(R.drawable.other);
                } else if (categories.get(position).compareTo("Work") == 0) {
                    textView1.setImageResource(R.drawable.work);
                } else if (categories.get(position).compareTo("School") == 0) {
                    textView1.setImageResource(R.drawable.school);
                } else if (categories.get(position).compareTo("Personal") == 0) {
                    textView1.setImageResource(R.drawable.personal);
                }
                textView2.setText(categories.get(position));
                return view;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                ImageView textView1 = (ImageView) view.findViewById(android.R.id.icon);
                TextView textView2 = (TextView) view.findViewById(android.R.id.text1);

                if (categories.get(position).compareTo("Other") == 0) {
                    textView1.setImageResource(R.drawable.other);
                } else if (categories.get(position).compareTo("Work") == 0) {
                    textView1.setImageResource(R.drawable.work);
                } else if (categories.get(position).compareTo("School") == 0) {
                    textView1.setImageResource(R.drawable.school);
                } else if (categories.get(position).compareTo("Personal") == 0) {
                    textView1.setImageResource(R.drawable.personal);
                }
                textView2.setText(categories.get(position));
                return view;
            }
        };
        spinner.setAdapter(adapter);

        // set up spinner
        GridLayout.Spec spinnerRowSpec = GridLayout.spec(1);
        GridLayout.Spec spinnerColumnSpec = GridLayout.spec(0, 2);
        GridLayout.LayoutParams spinnerLayoutParams =
                new GridLayout.LayoutParams(spinnerRowSpec, spinnerColumnSpec);
        spinner.setLayoutParams(spinnerLayoutParams);
        spinnerLayoutParams.setGravity(Gravity.FILL_HORIZONTAL);


        // create content EditText
        editText1 = new EditText(this);
        editText1.setHint(getString(R.string.content));
        editText1.setBackgroundResource(R.color.transparent);
        editText1.setBackground(null);

        // set up content EditText
        GridLayout.Spec contentRowSpec = GridLayout.spec(2);
        GridLayout.Spec contentColumnSpec = GridLayout.spec(0, 2);
        GridLayout.LayoutParams contentLayoutParams =
                new GridLayout.LayoutParams(contentRowSpec, contentColumnSpec);
        editText1.setGravity(Gravity.TOP);
        editText1.setLayoutParams(contentLayoutParams);
        contentLayoutParams.setGravity(Gravity.FILL);
        contentLayoutParams.height = (contentLayoutParams.MATCH_PARENT);


        // add all views to the gridlayout and scrollView
        gridLayout.addView(editText);
        gridLayout.addView(spinner);
        gridLayout.addView(editText1);
        scrollView.addView(gridLayout);
        setContentView(scrollView);
    }

    @Override
    public void onStart(){
        // set the EditTexts and type
        super.onStart();
        if(getIntent().getStringExtra("content") != null)
            editText.setText(getIntent().getStringExtra("title"));
        if(getIntent().getStringExtra("title") != null)
            editText1.setText(getIntent().getStringExtra("content"));
        spinner.setSelection(adapter.getPosition(getIntent().getStringExtra("type")));
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    /**
     * check to see if there are duplicate titles
     * @return returns false if this there are copy titles
     */
    public boolean isNotDuplicate() {
        // hande the case if the title hasn't changed while editing a note
        String[] list = getIntent().getStringArrayExtra("list");
        if (getIntent().getStringExtra("title") != null) {
            if (getIntent().getStringExtra("title").compareTo(editText.getText().toString()) == 0) {
                return true;
            }
        }
        // compares all of the strings in the string array to the current title
        for (int i = 0; i < list.length; i++) {
            if (list[i].compareTo(editText.getText().toString()) == 0)
                return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // inflate the options menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        switch (menuId) {
            // use options menu to save note
            case R.id.saveMenuItem:
                if(editText.getText().toString().length() > 0) {
                    if(isNotDuplicate()) {
                        Intent intent = new Intent();
                        // load the extras
                        intent.putExtra("title", editText.getText().toString());
                        intent.putExtra("content", editText1.getText().toString());
                        intent.putExtra("type", (String)spinner.getSelectedItem());
                        intent.putExtra("note", getIntent().getIntExtra("note", 0));
                        setResult(RESULT_OK, intent);
                        NoteActivity.this.finish();
                    }
                    else
                        Toast.makeText(NoteActivity.this, getString(R.string.toastTaken), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(NoteActivity.this, getString(R.string.toastBlank), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
