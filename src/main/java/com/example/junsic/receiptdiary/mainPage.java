package com.example.junsic.receiptdiary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.dzaitsev.android.widget.RadarChartView;
import com.example.junsic.receiptdiary.FoldingCellListAdapter;
import com.example.junsic.receiptdiary.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by junsic on 2018-05-12.
 */

public class mainPage extends AppCompatActivity {
    RelativeLayout LoadingView;
    Intent intent;
    boolean isMessageOn = false;
    Button myListBtn;
    TextView myID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        myID = findViewById(R.id.LocalIdName);
        myID.setText(MainActivity.IDNAME);


        // get our list view
        myListBtn = findViewById(R.id.MyListItemBtn);
        myListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MyListActivity.class);
                LoadingView.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingView.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        ListView theListView = findViewById(R.id.mainListView);
        LoadingView = findViewById(R.id.mainLoadingView);
        // prepare elements to display
        final ArrayList<Item> items = Item.getTestingList();

        // add custom btn handler to first list item
        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ReceiptActivity.class);
                intent.putExtra("tableName",items.get(0).getName().toString());
                LoadingView.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingView.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        items.get(1).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ReceiptActivity.class);
                intent.putExtra("tableName",items.get(1).getName().toString());
                LoadingView.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingView.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        items.get(2).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ReceiptActivity.class);
                intent.putExtra("tableName",items.get(2).getName().toString());
                LoadingView.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingView.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        items.get(3).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ReceiptActivity.class);
                intent.putExtra("tableName",items.get(3).getName().toString());
                LoadingView.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingView.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        items.get(4).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ReceiptActivity.class);
                intent.putExtra("tableName",items.get(4).getName().toString());
                LoadingView.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingView.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(this, items);

        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        theListView.setAdapter(adapter);

        // set on click event listener to list view
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });

        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.messageBtn);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isMessageOn){
                    Toast.makeText(getApplicationContext(), "MESSAGE가 OFF 상태로 변경되었습니다.", Toast.LENGTH_LONG).show();
                    isMessageOn = false;
                }
                else{
                    Toast.makeText(getApplicationContext(), "MESSAGE가 ON 상태로 변경되었습니다.", Toast.LENGTH_LONG).show();
                    isMessageOn = true;
                }
            }
        });

        FloatingSearchView mSearchView = findViewById(R.id.floating_search_view);

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Toast.makeText(getApplicationContext(), "SUGGESTION. ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchAction(String currentQuery) {
                intent = new Intent(getApplicationContext(), ReceiptActivity.class);
                intent.putExtra("tableName",currentQuery);
                LoadingView.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingView.setVisibility(View.GONE);
                        startActivity(intent);
                    }
                }, 2000);

            }
        });
    }

}
