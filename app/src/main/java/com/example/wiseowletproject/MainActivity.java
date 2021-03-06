package com.example.wiseowletproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    TextView txtQuote;
    Date today = Calendar.getInstance().getTime();
    EditText txtemail, txtpass;
    TextView txtlogin, txtno_acc;
    Button btn_login, btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Access to database
        DatabaseOpenHelper conn = new DatabaseOpenHelper(this, "wiseOwlet1.db", null, 1);
        db = conn.getWritableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtQuote = findViewById(R.id.txtQuote);
        txtQuote.setText(getQuote());

        btn_reg = findViewById(R.id.btn_login);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        btn_reg = findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, StudentRegistration.class));
            }
        });

    }

    private String getQuote() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String todaysDate = df.format(today);

        //select quote from table quotes
        Cursor tcursor = db.rawQuery("SELECT QUOTE,AUTHOR FROM QUOTES", new String[]{});
        StringBuffer buffer = new StringBuffer();

        while (tcursor.moveToNext()) {
            String quote = tcursor.getString(0);
            String author = tcursor.getString(1);
            buffer.append(quote + "\n" + author);
        }

        return buffer.toString();
    }

}