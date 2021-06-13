package com.mds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private mySQLiteDBHandler dbHandler;
    private EditText editText;
    private String selectedDate;
    private EditText nrZile;
    private EditText nrSaptamani;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView=(CalendarView) findViewById(R.id.calendarView);
        editText=findViewById(R.id.editText);
        nrZile = (EditText) findViewById(R.id.nrZile);
        nrSaptamani = (EditText) findViewById(R.id.nrSaptamani);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year)+Integer.toString(month)+Integer.toString(dayOfMonth);
                ReadDatabase(view);

            }
        });
        try {
            dbHandler = new mySQLiteDBHandler(this, "Calendar database",null,1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS EventCalendar(Date TEXT,Event TEXT)");
            ContentValues contentValues= new ContentValues();
            for(int i=2000;i<=2050;i++)
            {
                contentValues.put("Date",i+"1125");
                contentValues.put("Event","Christmas");
                sqLiteDatabase.insert("EventCalendar",null,contentValues);
                contentValues.clear();
                contentValues.put("Date",i+"28");
                contentValues.put("Event","Mother's Day");
                sqLiteDatabase.insert("EventCalendar",null,contentValues);
                contentValues.clear();
                contentValues.put("Date",i+"21");
                contentValues.put("Event","Martisor");
                sqLiteDatabase.insert("EventCalendar",null,contentValues);
                contentValues.clear();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void InsertDatabase(View view){
        ContentValues contentValues= new ContentValues();
        contentValues.put("Date",selectedDate);
        contentValues.put("Event",editText.getText().toString());
        sqLiteDatabase.insert("EventCalendar",null,contentValues);
        contentValues.clear();
    }

    public void ReadDatabase(View view){
        String query = "Select Event from EventCalendar where Date ="+selectedDate+";";
        try{
            @SuppressLint("Recycle") Cursor cursor=sqLiteDatabase.rawQuery(query,null);
            cursor.moveToLast();
            editText.setText(cursor.getString(0));
        }
        catch (Exception e){
            e.printStackTrace();
            editText.setText("");
        }
    }
    public void Validate (View view)
    {
        int x;
        x=Integer.parseInt(selectedDate.substring(5));
        String[] y;
        ContentValues contentValues= new ContentValues();
        for(int i=0;i<Integer.parseInt(nrZile.getText().toString());i++)
        {
            for(int j=0;j<Integer.parseInt(nrSaptamani.getText().toString());j++)
            {
                y=new String[x++];
                contentValues.put("Date",selectedDate.substring(0,4)+y.toString());
                contentValues.put("Event",editText.getText().toString());
                sqLiteDatabase.insert("EventCalendar",null,contentValues);
                contentValues.clear();
            }
            x+=7-Integer.parseInt(nrZile.getText().toString());
        }
    }

}
