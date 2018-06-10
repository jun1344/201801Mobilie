package com.example.junsic.receiptdiary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsic on 2018-05-28.
 */

public class ReceiptListAdatper extends ArrayAdapter<Receipt>{

    private Context mContext;
    int mResource;

    public ReceiptListAdatper(@NonNull Context context, int resource, @NonNull ArrayList<Receipt> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        String date = getItem(position).getDate();
        String name = getItem(position).getName();
        String type = getItem(position).getType();
        String money = getItem(position).getMoney();

        Receipt receipt = new Receipt(date, name, type, money);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView textView1 = convertView.findViewById(R.id.receiptDate);
        TextView textView2 = convertView.findViewById(R.id.receiptName);
        TextView textView3 = convertView.findViewById(R.id.receiptType);
        TextView textView4 = convertView.findViewById(R.id.receiptMoney);

        textView1.setText(date);
        textView2.setText(name);
        textView3.setText(type);
        textView4.setText(money);

        return convertView;
    }
}
