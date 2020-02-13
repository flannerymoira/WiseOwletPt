package com.example.wiseowletproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wiseowletproject.Entities.Subject;

import java.util.ArrayList;

public class SetupSubjects extends AppCompatActivity {

    public static int subId;
    Spinner subList, yearList;
    ArrayList<String> subNames, yearNames;
    ArrayList<Subject> subjectList;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_subjects);

        subList = findViewById(R.id.subSpinner);
        yearList = findViewById(R.id.yearSpinner);

        //Access to database
        DatabaseOpenHelper conn = new DatabaseOpenHelper(this, "wiseOwlet.db", null, 1);
        db = conn.getWritableDatabase();

        getSubjectList();
        obtainLists();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subNames);
        subList.setAdapter(adapter);

        //method to select an option from spinner
        subList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    subId = subjectList.get(position-1).getSubjectId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, yearNames);
        yearList.setAdapter(adapter2);

    }

            //method to get array of objects from table
            private void getSubjectList() {

                Subject subject;
                subjectList = new ArrayList<Subject>();

                Cursor ss = db.rawQuery("SELECT * FROM SUBJECT", null);

                while (ss.moveToNext()) {
                    subject = new Subject();
                    subject.setSubjectId(ss.getInt(0));
                    subject.setSubjectName(ss.getString(1) );
                    subject.setLevel(ss.getString(2));
                    subjectList.add(subject);
                }

            }

            //method to populate spinners
            private void obtainLists() {
                subNames = new ArrayList<String>();
                subNames.add("(Select Subject / Level)");

                for (int i = 0; i < subjectList.size(); i++) {
                    subNames.add(subjectList.get(i).getSubjectName() + " / " + subjectList.get(i).getLevel());
                }

                yearNames = new ArrayList<String>();
                yearNames.add("(Select Year)");
                yearNames.add("Year 1");
                yearNames.add("Year 2");
                yearNames.add("Year 3");
                yearNames.add("Transition Year");
                yearNames.add("Year 5");
                yearNames.add("Year 6");
            }

}