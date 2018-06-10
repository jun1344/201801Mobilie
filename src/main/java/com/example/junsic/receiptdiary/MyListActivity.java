package com.example.junsic.receiptdiary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by junsic on 2018-06-05.
 */

public class MyListActivity extends AppCompatActivity {
    //변수르 선언해줍니다.

    GregorianCalendar today = new GregorianCalendar();
    int month = today.get ( today.MONTH ) + 1;
    int day = today.get ( today.DAY_OF_MONTH );
    ArrayList<Receipt> receiptArrayList = new ArrayList<>();

    ListView listView;
    Button addBtn;
    Button deleteBtn;
    Button updateBtn;
    //EditText typeText;
    EditText nameText;
    EditText moneyText;
    Spinner typeSpinner;

    String typeString;

    SQLiteDatabase db;
    MySQLiteOpenHelper helper;
    String listItem[];
    Receipt receipt[] = new Receipt[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myreceipt_layout);

        listView = findViewById(R.id.ListView1);
        addBtn = findViewById(R.id.addBtn1);
        deleteBtn = findViewById(R.id.deleteBtn1);
        updateBtn = findViewById(R.id.updateBtn1);
        //typeText = findViewById(R.id.typeEditText);
        nameText = findViewById(R.id.nameEditText);
        moneyText = findViewById(R.id.moneyEditText);
        typeSpinner = findViewById(R.id.typeEditText);

        helper = new MySQLiteOpenHelper(MyListActivity.this, MainActivity.IDNAME +".db",null,1);
        invalidate();
        //추가 버튼 이벤트입니다.

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameText.getText().toString().length() == 0 || moneyText.getText().toString().length() ==0){
                    Toast.makeText(MyListActivity.this, "모든 항목을 입력해 주십시오",Toast.LENGTH_LONG).show();
                }
                else {
                    insert (String.valueOf(month) + "-" + String.valueOf(day)
                            ,typeString, nameText.getText().toString(), moneyText.getText().toString());
                    invalidate();
                }
            }
        });


        //삭제 버튼 이벤트 입니다.
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameText.getText().toString().length() == 0 || moneyText.getText().toString().length() ==0){
                    Toast.makeText(MyListActivity.this, "모든 항목을 입력해 주십시오",Toast.LENGTH_LONG).show();
                }
                else {
                    delete (String.valueOf(month) + "-" + String.valueOf(day), nameText.getText().toString());
                    invalidate();
                }

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameText.getText().toString().length() == 0 || moneyText.getText().toString().length() ==0){
                    Toast.makeText(MyListActivity.this, "모든 항목을 입력해 주십시오",Toast.LENGTH_LONG).show();
                }
                else {
                    update (String.valueOf(month) + "-" + String.valueOf(day), nameText.getText().toString(),moneyText.getText().toString());
                    invalidate();
                }
            }
        });



    }

    /*String sql = "create table " + MainActivity.IDNAME+ "("
            +"date text,"
            +"type text,"
            +"name text,"
            +"money text);";*/

    private void invalidate(){
        select();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
        ReceiptListAdatper adatper = new ReceiptListAdatper(this, R.layout.receipt_context, receiptArrayList);
        listView.setAdapter(adatper);
    }

    public void insert(String date, String type, String name, String money) {

        db = helper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("date",date);
        values.put("type",type);
        values.put("name",name);
        values.put("money",money);
        db.insert(MainActivity.IDNAME,null,values);

    }

    public void update (String date ,String name, String money) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Money", money);
        db.update(MainActivity.IDNAME, values, "date=? AND name=?", new String[]{date, name});
    }

    //DB에 해당하는 NAME의 내용을 삭제합니다.
    public void delete (String date, String name) {
        db=helper.getWritableDatabase();
        db.delete(MainActivity.IDNAME ,"date=? AND name=?",new String[]{date, name});
    }

    //
    public void select() {
        db = helper.getReadableDatabase();
        Cursor c = db.query(MainActivity.IDNAME, null, null, null, null, null, null);

        listItem = new String[c.getCount()];
        int count = 0;
        while (c.moveToNext()) {
            //receipt[0] = new Receipt("04-02", "차비", "생활비", "1250");
            receipt[count] = new Receipt(c.getString(c.getColumnIndex("date")),
                    c.getString(c.getColumnIndex("type")),
                    c.getString(c.getColumnIndex("name")),
                    c.getString(c.getColumnIndex("money")));
/*            listItem[count] = c.getString(c.getColumnIndex("date"))
                    +  " " +  c.getString(c.getColumnIndex("name"));*/
            count++;
        }

        receiptArrayList = new ArrayList<>();

        for(int i = 0; i < count; i++){
            receiptArrayList.add(receipt[i]);
        }
        c.close();
    }


}
