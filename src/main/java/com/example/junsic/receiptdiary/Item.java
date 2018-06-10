package com.example.junsic.receiptdiary;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by junsic on 2018-05-12.
 */

public class Item {

    private String name;
    private int totalScore;
    private int people;
    private String targetPrice;
    private String usePrice;
    private String date;
    private String month;
    private int chart1;
    private int chart2;
    private int chart3;

    private View.OnClickListener requestBtnClickListener;

    public Item() {
    }

    public Item(String name, int totalScore, int people, String targetPrice, String usePrice, String date, String month, int chart1, int chart2, int chart3) {
        this.name = name;
        this.totalScore = totalScore;
        this.people = people;
        this.targetPrice = targetPrice;
        this.usePrice = usePrice;
        this.date = date;
        this.month = month;
        this.chart1 = chart1;
        this.chart2 = chart2;
        this.chart3 = chart3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(String targetPrice) {
        this.targetPrice = targetPrice;
    }

    public String getUsePrice() {
        return usePrice;
    }

    public void setUsePrice(String usePrice) {
        this.usePrice = usePrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    //chart
    public  int  getChart1() {
        return chart1;
    }public void  setChart1(int chart1) {
    this.chart1 = chart1;
    }   public int getChart2() {
    return chart2;
    }public void  setChart2(int chart2) {
    this.chart2 = chart2;
    }public int getChart3() {
    return chart3;
    }public void  setChart3(int chart3) {
    this.chart3 = chart3;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (totalScore != item.totalScore) return false;
        if (people != item.people) return false;
        //chart
        if (chart1 != item.chart1) return false;
        if (chart2 != item.chart2) return false;
        if (chart3 != item.chart3) return false;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (targetPrice != null ? !targetPrice.equals(item.targetPrice) : item.targetPrice != null) return false;
        if (usePrice!= null ? !usePrice.equals(item.usePrice) : item.usePrice != null) return false;
        if (date != null ? !date.equals(item.date) : item.date != null) return false;

        return !(month != null ? !month.equals(item.month) : item.month != null);

    }

    @Override
    public int hashCode() {
        int result = (name != null ? name.hashCode() : 0);
        result = 31 * result + totalScore;
        result = 31 * result + people;
        result = 31 * result + (targetPrice != null ? targetPrice.hashCode() : 0);
        result = 31 * result + (usePrice != null ? usePrice.hashCode() : 0);

        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + chart1;
        result = 31 * result + chart2;
        result = 31 * result + chart3;
        return result;
    }

    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("jun1344", 73, 62, "424,150원",  "371,900원","03-31", "03", 30, 20, 50));
        items.add(new Item("test1", 46, 23, "600,000원",  "517,000원","03-31", "03", 60, 20, 20));
        items.add(new Item("testDB12", 25, 62, "600,000원",  "517,000원","03-31", "03", 60, 20, 20));
        items.add(new Item("dusdn1234", 72, 25, "600,000원",  "517,000원","03-31", "03", 60, 20, 20));
        items.add(new Item("tjdgus1234", 100, 25, "600,000원",  "517,000원","03-31", "03", 60, 20, 20));

        return items;

    }
}
