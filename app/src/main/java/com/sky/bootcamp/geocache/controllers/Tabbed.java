package com.sky.bootcamp.geocache.controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.sky.bootcamp.geocache.database.DatabaseAccessLayer;
import com.sky.bootcamp.geocache.models.OrderLine;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mgh01 on 28/07/2015.
 */
public class Tabbed {

    public static ArrayList<OrderLine> getOrderline(String filter){
        return DatabaseAccessLayer.getOrderLines(filter);
    }

    public static ArrayList<OrderLine> getOrderline(final ArrayList<OrderLine> orders, final String filter){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                orders.addAll(getOrderline(filter));
                return null;
            }
        }.execute();

        return orders;
    }

    public static void getOrderLineByBarcode(final String barcode) {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String errMsg = "";
                /*
                try {
                    OrderLine orderline = DatabaseAccessLayer.getOrderLineByBarcode(barcode);
                    System.out.println(orderline.getName());
                } catch (SQLException e) {
                    errMsg = "An error has occurred. Please try again later.";
                    Log.e("Database Connection", e.getMessage());
                }
                */
                return errMsg;
            }

        }.execute();
    }

}
