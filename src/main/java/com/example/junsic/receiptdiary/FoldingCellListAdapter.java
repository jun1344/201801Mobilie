package com.example.junsic.receiptdiary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dzaitsev.android.widget.RadarChartView;
import com.example.junsic.receiptdiary.FoldingCellListAdapter;
import com.example.junsic.receiptdiary.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;

    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        Item item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;

        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder

            viewHolder.titleName = cell.findViewById(R.id.titleName);
            viewHolder.titleUpdate = cell.findViewById(R.id.dateText);
            viewHolder.titleScore = cell.findViewById(R.id.ScoreText);
            viewHolder.titlePeople = cell.findViewById(R.id.peopleText);

            viewHolder.contentName = cell.findViewById(R.id.content_userNameText);
            viewHolder.contentTarget = cell.findViewById(R.id.targetText);
            viewHolder.contentUse = cell.findViewById(R.id.useText);


            viewHolder.contentRequestBtn = cell.findViewById(R.id.content_request_btn);
            //chart
            viewHolder.chartView = cell.findViewById(R.id.radar_chart);
            viewHolder.chartValue = cell.findViewById(R.id.content_chartValue);
            viewHolder.chart1 = cell.findViewById(R.id.content_chart1);
            viewHolder.chart2 = cell.findViewById(R.id.content_chart2);
            viewHolder.chart3 = cell.findViewById(R.id.content_chart3);

            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item)
            return cell;

        // bind data from selected element to view through view holder
        viewHolder.titleName.setText(item.getName());
        viewHolder.titleUpdate.setText(item.getDate());
        viewHolder.titleScore.setText(String.valueOf(item.getTotalScore()));
        viewHolder.titlePeople.setText(String.valueOf(item.getPeople()));

        viewHolder.contentName.setText(item.getName());
        viewHolder.contentTarget.setText(item.getTargetPrice());
        viewHolder.contentUse.setText(item.getUsePrice());


        //chart
        final LinkedHashMap<String, Float> axis = new LinkedHashMap<>(3);
        axis.put("고정지출",  Float.valueOf(String.valueOf(item.getChart1())));
        axis.put("변동지출", Float.valueOf(String.valueOf(item.getChart2())));
        axis.put("비소비성 지출",  Float.valueOf(String.valueOf(item.getChart3())));
        viewHolder.chartView.setAxis(axis);
        viewHolder.chartValue.setText(chartValueFunction(item.getChart1(), item.getChart2(), item.getChart3()));
        viewHolder.chart1.setText("고정지출 점수는" + String.valueOf(item.getChart1()) + "점");
        viewHolder.chart2.setText("변동지출 점수는" + String.valueOf(item.getChart2()) + "점");
        viewHolder.chart3.setText("비소비성 점수는" + String.valueOf(item.getChart3()) + "점");

        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.contentRequestBtn.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.contentRequestBtn.setOnClickListener(defaultRequestBtnClickListener);
        }

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public String chartValueFunction(int a, int b, int c){
        if(a > b && a > c){
            return "평법합니다.";
        }
        else if(c > b && c > a){
            return "변동이큽니다.";
        }
        if(b > a && b > c){
            return "훌륭합니다.";
        }
        else{
            return "복합적ㅇ비니다.";
        }
    }


    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {

        TextView titleName;
        TextView titleUpdate;
        TextView titleScore;
        TextView titlePeople;

        TextView contentName;
        TextView contentTarget;
        TextView contentUse;

        TextView contentRequestBtn;
        //chart 부분
        RadarChartView chartView;
        TextView chartValue;
        TextView chart1;
        TextView chart2;
        TextView chart3;


    }
}