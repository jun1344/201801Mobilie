package com.example.junsic.receiptdiary;

/**
 * Created by junsic on 2018-05-16.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

/**
 *
 * @author Muniu Kariuki - muniu@bityarn.co.ke
 *
 */
public class MessageBox extends Activity {

    TextView messageR;
    private String[] knownSenders = { "MPESA", "", "+254713778804" };

    private String[] sentKeywords = { "sent to" };
    private String[] receivedKeywords = { "received" };

    protected final String OUTBOUND_PAYMENT_TYPE = "Outbound";
    protected final String INBOUND_PAYMENT_TYPE = "Inbound";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row);

        messageR = findViewById(R.id.lblNumber);
        sweepSMSInbox();
    }

    private void sweepSMSInbox() {
        ContentResolver contentResolver = getContentResolver();
        final String[] fields = new String[] { "*" };
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor queryCursor = contentResolver.query(uri, fields, null, null,
                "date asc");

        int mesgCount = queryCursor.getCount();

        Log.i("MM_RECEIPT", "Message Count = " + mesgCount);

        if (queryCursor.moveToFirst()) {
            for (int i = 0; i < 1; i++) {

                String fromAddress = queryCursor.getString(
                        queryCursor.getColumnIndexOrThrow("address"))
                        .toString();
                String body = queryCursor.getString(
                        queryCursor.getColumnIndexOrThrow("body")).toString();

                    messageR.setText(body);

                queryCursor.moveToNext();
            }

        }
        queryCursor.close();
    }

    /**
     *
     * Filter the @param messageSource through a list of known @param addresses
     *
     * @return a boolean true is matches correctly or false if it doesn't
     */
    public boolean isFrom(String messageSource, String[] addresses) {
        for (int i = 0; i < addresses.length; i++) {
            if (messageSource.equalsIgnoreCase(addresses[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * Filter the @param message through a list of known @param keywords
     *
     * @return
     */
    public boolean hasKeywords(String message, String[] keywords) {
        for (int i = 0; i < keywords.length; i++) {
            if (message.contains(keywords[i].toLowerCase())) {
                return true;
            }
        }

        return false;
    }

}