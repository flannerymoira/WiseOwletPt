package com.example.wiseowletproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.wiseowletproject.Entities.Subject;

import java.util.ArrayList;

public class SetupSubjects extends AppCompatActivity {

    public static int subId;
    Spinner subList;
    ArrayList<String> subNames;
    ArrayList<Subject> subjectList;


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_subjects);

        subList = findViewById(R.id.subSpinner);

        //Access to database
        DatabaseOpenHelper conn = new DatabaseOpenHelper(this, "wiseOwlet.db", null, 1);
        db = conn.getWritableDatabase();

        getSubjectList();
        obtainList();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, subNames);
        subList.setAdapter(adapter);
    }

    //method to get array of objects from table
    private void getSubjectList() {

        Subject subject;
        subjectList = new ArrayList<Subject>();

        Cursor ss = db.rawQuery("SELECT * FROM SUBJECT", null);

        while (ss.moveToNext()) {
            subject = new Subject();
            subject.setSubjectId(ss.getInt(0));
            subject.setSubjectName(ss.getString(1));
            subject.setLevel(ss.getString(2));
            subjectList.add(subject);
        }

    }

    //method to populate spinner
    private void obtainList() {
        subNames = new ArrayList<String>();
        subNames.add("(Select Subject)");

        for (int i = 0; i < subjectList.size(); i++) {
            subNames.add(subjectList.get(i).getSubjectName());
        }
    }

}
