package com.example.junsic.receiptdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cc.trity.floatingactionbutton.FloatingActionButton;

/**
 * Created by junsic on 2018-05-27.
 */

public class ReceiptActivity extends AppCompatActivity {

    private int tableList = 0;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_layout);

        ListView mlistView = findViewById(R.id.myReceiptList);

        Intent intent = getIntent();
        String tableKey = intent.getStringExtra("tableName");
        //여기다가 노가다 하자
        //Create Test Table
        Receipt receipt[] = new Receipt[100];
        if(tableKey.equalsIgnoreCase("jun1344")){
            tableList = 64;
            receipt[0] = new Receipt("04-02", "차비", "생활비", "1250");
            receipt[1] = new Receipt("04-02", "식비", "생활비", "5000");
            receipt[2] = new Receipt("04-02", "차비", "생활비", "1250");
            receipt[3] = new Receipt("04-02", "택시이용", "생활비", "4200");
            receipt[4] = new Receipt("04-03", "차비", "생활비", "1250");
            receipt[5] = new Receipt("04-03", "식비", "생활비", "6000");
            receipt[6] = new Receipt("04-03", "차비", "생활비", "1250");
            receipt[7] = new Receipt("04-03", "PC요금", "사치", "5000");
            receipt[8] = new Receipt("04-04", "차비", "생활비", "1250");
            receipt[9] = new Receipt("04-04", "식비", "생활비", "4000");
            receipt[10] = new Receipt("04-04", "차비", "생활비", "1250");
            receipt[11] = new Receipt("04-05", "차비", "생활비",  "1250");
            receipt[12] = new Receipt("04-05", "식비", "저축", "5000");
            receipt[13] = new Receipt("04-05", "차비", "생활비", "1250");
            receipt[14] = new Receipt("04-06", "차비", "생활비", "1250");
            receipt[15] = new Receipt("04-06", "식비", "생활비", "3500");
            receipt[16] = new Receipt("04-06", "차비", "생활비", "1250");
            receipt[17] = new Receipt("04-09", "차비", "생활비", "1250");
            receipt[18] = new Receipt("04-09", "차비", "생활비", "1250");
            receipt[19] = new Receipt("04-10", "휴대폰요금", "고정지출", "54250");
            receipt[20] = new Receipt("04-10", "차비", "생활비", "1250");
            receipt[21] = new Receipt("04-10", "식비", "생활비", "5000");
            receipt[22] = new Receipt("04-10", "차비", "생활비", "1250");
            receipt[23] = new Receipt("04-11", "차비", "생활비", "1250");
            receipt[24] = new Receipt("04-11", "식비", "생활비", "4000");
            receipt[25] = new Receipt("04-11", "차비", "생활비", "1250");
            receipt[26] = new Receipt("04-12", "차비", "생활비", "1250");
            receipt[27] = new Receipt("04-12", "식비", "생활비", "5000");
            receipt[28] = new Receipt("04-12", "차비", "생활비", "1250");
            receipt[29] = new Receipt("04-13", "차비", "생활비", "1250");
            receipt[30] = new Receipt("04-13", "식비", "저축", "5000");
            receipt[31] = new Receipt("04-13", "차비", "생활비", "1250");
            receipt[32] = new Receipt("04-16", "차비", "생활비", "1250");
            receipt[33] = new Receipt("04-16", "식비", "생활비", "7000");
            receipt[34] = new Receipt("04-16", "차비", "생활비", "1250");
            receipt[35] = new Receipt("04-17", "차비", "생활비", "1250");
            receipt[36] = new Receipt("04-17", "간식비", "생활비", "3000");
            receipt[37] = new Receipt("04-17", "차비", "생활비", "1250");
            receipt[38] = new Receipt("04-18", "차비", "생활비", "1250");
            receipt[39] = new Receipt("04-18", "PC요금", "사치", "10000");
            receipt[40] = new Receipt("04-18", "차비", "생활비", "1250");
            receipt[41] = new Receipt("04-19", "차비", "생활비", "1250");
            receipt[42] = new Receipt("04-19", "차비", "생활비", "1250");
            receipt[43] = new Receipt("04-20", "차비", "생활비", "1250");
            receipt[44] = new Receipt("04-20", "술집이용", "사치", "29000");
            receipt[45] = new Receipt("04-20", "택시이용", "생활비", "7900");
            receipt[46] = new Receipt("04-23", "차비", "생활비", "1250");
            receipt[47] = new Receipt("04-02", "식비", "생활비", "4000");
            receipt[48] = new Receipt("04-23", "차비", "생활비", "1250");
            receipt[49] = new Receipt("04-24", "차비", "생활비", "1250");
            receipt[50] = new Receipt("04-24", "식비", "생활비", "5000");
            receipt[51] = new Receipt("04-24", "차비", "생활비", "1250");
            receipt[52] = new Receipt("04-25", "차비", "생활비", "1250");
            receipt[53] = new Receipt("04-25", "차비", "생활비", "1250");
            receipt[54] = new Receipt("04-26", "차비", "생활비", "1250");
            receipt[55] = new Receipt("04-26", "식비", "생활비", "4000");
            receipt[56] = new Receipt("04-26", "차비", "생활비", "1250");
            receipt[57] = new Receipt("04-27", "차비", "생활비", "1250");
            receipt[58] = new Receipt("04-27", "식비", "생활비", "5000");
            receipt[59] = new Receipt("04-27", "차비", "생활비", "1250");
            receipt[60] = new Receipt("04-28", "월급", "월급", "424150");
            receipt[61] = new Receipt("04-30", "차비", "생활비", "1250");
            receipt[62] = new Receipt("04-30", "식비", "생활비", "5000");
            receipt[63] = new Receipt("04-30", "차비", "생활비", "1250");
        }
        else if(tableKey.equalsIgnoreCase("test1")) {
            tableList = 27;
            receipt[0] = new Receipt("05-01", "식비", "생활비", "2000");
            receipt[1] = new Receipt("05-01", "식비", "생활비", "7000");
            receipt[2] = new Receipt("05-02", "차비", "생활비", "9000");
            receipt[3] = new Receipt("05-03", "식비", "생활비", "2000");
            receipt[4] = new Receipt("05-03", "식비", "생활비", "6500");
            receipt[5] = new Receipt("05-04", "음주", "사치", "20000");
            receipt[6] = new Receipt("05-05", "문화생활", "생활비", "21000");
            receipt[7] = new Receipt("05-05", "식비", "생활비", "15000");
            receipt[8] = new Receipt("05-07", "식비", "생활비", "8000");
            receipt[9] = new Receipt("05-07", "식비", "생활비", "2000");
            receipt[10] = new Receipt("05-09", "식비", "생활비", "2000");
            receipt[11] = new Receipt("05-10", "차비", "생활비", "10000");
            receipt[12] = new Receipt("05-10", "식비", "생활비", "7000");
            receipt[13] = new Receipt("05-12", "식비", "생활비", "2000");
            receipt[14] = new Receipt("05-13", "식비", "생활비", "2000");
            receipt[15] = new Receipt("05-15", "월급", "월급", "400000");
            receipt[16] = new Receipt("05-15", "차비", "생활비", "9000");
            receipt[17] = new Receipt("05-18", "식비", "생활비", "7000");
            receipt[18] = new Receipt("05-19", "식비", "생활비", "2000");
            receipt[19] = new Receipt("05-21", "휴대폰요금", "고정지출", "104548");
            receipt[20] = new Receipt("05-21", "식비", "생활비", "2000");
            receipt[21] = new Receipt("05-23", "식비", "생활비", "7000");
            receipt[22] = new Receipt("05-24", "식비", "생활비", "5000");
            receipt[23] = new Receipt("05-25", "식비", "생활비", "2000");
            receipt[24] = new Receipt("05-29", "문화생활", "생활비", "11000");
            receipt[25] = new Receipt("05-29", "식비", "생활비", "20000");
            receipt[26] = new Receipt("05-30", "식비", "생활비", "3500");
        }
        else if(tableKey.equalsIgnoreCase("testDB12")) {
            tableList = 23;
            receipt[0] = new Receipt("05-02", "차비", "생활비", "1250");
            receipt[1] = new Receipt("05-02", "식비", "생활비", "5000");
            receipt[2] = new Receipt("05-02", "차비", "생활비", "1250");
            receipt[3] = new Receipt("05-03", "택시이용", "생활비", "4200");
            receipt[4] = new Receipt("05-03", "차비", "생활비", "1250");
            receipt[5] = new Receipt("05-05", "식비", "생활비", "6000");
            receipt[6] = new Receipt("05-05", "영화요금", "생활비", "16000");
            receipt[7] = new Receipt("05-08", "PC요금", "사치", "5000");
            receipt[8] = new Receipt("05-08", "어버이날선물", "생활비", "15000");
            receipt[9] = new Receipt("05-15", "스승의날선물", "생활비", "10000");
            receipt[10] = new Receipt("05-20", "차비", "생활비", "1250");
            receipt[11] = new Receipt("05-21", "차비", "생활비", "1250");
            receipt[12] = new Receipt("05-21", "식비", "저축", "5000");
            receipt[13] = new Receipt("05-22", "차비", "생활비", "1250");
            receipt[14] = new Receipt("05-23", "차비", "생활비", "1250");
            receipt[15] = new Receipt("05-23", "식비", "생활비", "3500");
            receipt[16] = new Receipt("05-23", "차비", "생활비", "1250");
            receipt[17] = new Receipt("05-25", "차비", "생활비", "1250");
            receipt[18] = new Receipt("05-25", "차비", "생활비", "1250");
            receipt[19] = new Receipt("05-26", "휴대폰요금", "고정지출", "54250");
            receipt[20] = new Receipt("05-27", "차비", "생활비", "1250");
            receipt[21] = new Receipt("05-28", "월급", "월급", "424150");
            receipt[22] = new Receipt("05-30", "식비", "생활비", "5000");
        }
        else if(tableKey.equalsIgnoreCase("yeon123")) {
            tableList = 23;
            receipt[0] = new Receipt("03-02", "차비", "생활비", "1250");
            receipt[1] = new Receipt("03-02", "식비", "생활비", "5000");
            receipt[2] = new Receipt("03-02", "차비", "생활비", "1250");
            receipt[3] = new Receipt("03-03", "택시이용", "생활비", "4200");
            receipt[4] = new Receipt("03-04", "차비", "생활비", "1250");
            receipt[5] = new Receipt("03-05", "식비", "생활비", "6000");
            receipt[6] = new Receipt("03-06", "영화요금", "생활비", "16000");
            receipt[7] = new Receipt("03-08", "PC요금", "사치", "5000");
            receipt[8] = new Receipt("03-08", "식비", "생활비", "15000");
            receipt[9] = new Receipt("03-10", "옷구매", "생활비", "20000");
            receipt[10] = new Receipt("03-15", "차비", "생활비", "1250");
            receipt[11] = new Receipt("03-15", "차비", "생활비", "1250");
            receipt[12] = new Receipt("03-16", "식비", "저축", "5000");
            receipt[13] = new Receipt("03-18", "차비", "생활비", "1250");
            receipt[14] = new Receipt("03-18", "차비", "생활비", "1250");
            receipt[15] = new Receipt("03-23", "식비", "생활비", "3500");
            receipt[16] = new Receipt("03-23", "차비", "생활비", "1250");
            receipt[17] = new Receipt("03-25", "차비", "생활비", "1250");
            receipt[18] = new Receipt("03-25", "차비", "생활비", "1250");
            receipt[19] = new Receipt("03-26", "휴대폰요금", "고정지출", "54250");
            receipt[20] = new Receipt("03-27", "차비", "생활비", "1250");
            receipt[21] = new Receipt("03-28", "월급", "월급", "424150");
            receipt[22] = new Receipt("03-30", "식비", "생활비", "5000");
        }
        else if(tableKey.equalsIgnoreCase("dusdn1234")) {
            tableList = 23;
            receipt[0] = new Receipt("05-01", "차비", "생활비", "1250");
            receipt[1] = new Receipt("05-01", "차비", "생활비", "1250");
            receipt[2] = new Receipt("05-02", "식비", "생활비", "6000");
            receipt[3] = new Receipt("05-04", "식비", "생활비", "4200");
            receipt[4] = new Receipt("05-05", "영화비", "사치", "7000");
            receipt[5] = new Receipt("05-05", "식비", "생활비", "6000");
            receipt[6] = new Receipt("05-07", "차비", "생활비", "5000");
            receipt[7] = new Receipt("05-08", "카네이션구매", "생활비", "5000");
            receipt[8] = new Receipt("05-12", "식비", "생활비", "15000");
            receipt[9] = new Receipt("05-14", "옷구매", "생활비", "20000");
            receipt[10] = new Receipt("05-15", "차비", "생활비", "1250");
            receipt[11] = new Receipt("05-15", "차비", "생활비", "1250");
            receipt[12] = new Receipt("05-15", "식비", "저축", "5000");
            receipt[13] = new Receipt("05-18", "차비", "생활비", "1250");
            receipt[14] = new Receipt("05-19", "식비", "생활비", "5000");
            receipt[15] = new Receipt("05-22", "식비", "저축", "7000");
            receipt[16] = new Receipt("05-23", "옷구매", "생활비", "20000");
            receipt[17] = new Receipt("05-25", "차비", "생활비", "1250");
            receipt[18] = new Receipt("05-25", "차비", "생활비", "1250");
            receipt[19] = new Receipt("05-26", "휴대폰요금", "고정지출", "54250");
            receipt[20] = new Receipt("05-27", "차비", "생활비", "1250");
            receipt[21] = new Receipt("05-28", "월급", "월급", "424150");
            receipt[22] = new Receipt("05-30", "식비", "생활비", "5000");

        }
        else if(tableKey.equalsIgnoreCase("tjdgus1234")) {
            tableList = 23;
            receipt[0] = new Receipt("04-01", "차비", "생활비", "1250");
            receipt[1] = new Receipt("04-01", "식비", "생활비", "5000");
            receipt[2] = new Receipt("04-01", "차비", "생활비", "1250");
            receipt[3] = new Receipt("04-03", "식비", "생활비", "4200");
            receipt[4] = new Receipt("04-04", "차비", "생활비", "1250");
            receipt[5] = new Receipt("04-05", "식비", "생활비", "6000");
            receipt[6] = new Receipt("04-07", "술값", "사치", "16000");
            receipt[7] = new Receipt("04-08", "PC요금", "사치", "5000");
            receipt[8] = new Receipt("04-12", "식비", "생활비", "15000");
            receipt[9] = new Receipt("04-13", "옷구매", "생활비", "20000");
            receipt[10] = new Receipt("04-15", "차비", "생활비", "1250");
            receipt[11] = new Receipt("04-15", "차비", "생활비", "1250");
            receipt[12] = new Receipt("04-15", "식비", "저축", "5000");
            receipt[13] = new Receipt("04-18", "차비", "생활비", "1250");
            receipt[14] = new Receipt("04-18", "차비", "생활비", "1250");
            receipt[15] = new Receipt("04-23", "식비", "생활비", "3500");
            receipt[16] = new Receipt("04-24", "옷구매", "생활비", "30000");
            receipt[17] = new Receipt("04-25", "차비", "생활비", "1250");
            receipt[18] = new Receipt("04-25", "차비", "생활비", "1250");
            receipt[19] = new Receipt("04-26", "휴대폰요금", "고정지출", "54250");
            receipt[20] = new Receipt("04-27", "차비", "생활비", "1250");
            receipt[21] = new Receipt("04-28", "월급", "월급", "424150");
            receipt[22] = new Receipt("04-30", "식비", "생활비", "5000");

        }
        else{
            tableList = 1;
            receipt[0] = new Receipt("00-00", "NULL", "NULL", "데이터 검색 실패");
        }

        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.StarBtn);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "가계부 추천을 했습니다.", Toast.LENGTH_LONG).show();
            }
        });

        final FloatingActionButton actionB = (FloatingActionButton) findViewById(R.id.backBtn);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "홈화면으로 돌아갑니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        });


        ArrayList<Receipt> receiptArrayList = new ArrayList<>();

        for(int i = 0; i < tableList; i++){
            receiptArrayList.add(receipt[i]);
        }
        ReceiptListAdatper adatper = new ReceiptListAdatper(this, R.layout.receipt_context, receiptArrayList);
        mlistView.setAdapter(adatper);

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //adater.notifyDataSetChanged();
    }

    public void dumyDate(){


    }

}
