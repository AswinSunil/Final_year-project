package com.example.detecttelecomconnectivity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.PhoneStateListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import java.io.File;
import java.net.URI;

public class Main3Activity extends AppCompatActivity {

    TelephonyManager tel;
    TextView v1,v2,v3,v4,v5,v6,v7;
    Button submit;
    DatabaseReference rootRef, demoRef;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        v1 = (TextView) findViewById(R.id.tv1);
        v2 = (TextView) findViewById(R.id.tv2);
        v3 = (TextView) findViewById(R.id.tv3);
        v4 = (TextView) findViewById(R.id.tv4);
        v5 = (TextView) findViewById(R.id.tv5);
        v6 = (TextView) findViewById(R.id.tv6);
        v7 = (TextView) findViewById(R.id.tv7);
        submit = (Button) findViewById(R.id.btnSubmit);

        TelephonyManager telephonyManager;


        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        final NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());

        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        v1.setText(" " + telephonyManager.getNetworkOperatorName());

        int networkType = telephonyManager.getNetworkType();


        switch (networkType)
        {
            case 7:
                v2.setText("1xRTT");
                break;
            case 4:
                v2.setText("CDMA");
                break;
            case 2:
                v2.setText("EDGE");
                break;
            case 14:
                v2.setText("eHRPD");
                break;
            case 5:
                v2.setText("EVDO rev. 0");
                break;
            case 6:
                v2.setText("EVDO rev. A");
                break;
            case 12:
                v2.setText("EVDO rev. B");
                break;
            case 1:
                v2.setText("GPRS");
                break;
            case 8:
                v2.setText("HSDPA");
                break;
            case 10:
                v2.setText("HSPA");
                break;
            case 15:
                v2.setText("HSPA+");
                break;
            case 9:
                v2.setText("HSUPA");
                break;
            case 11:
                v2.setText("iDen");
                break;
            case 13:
                v2.setText("LTE");
                break;
            case 3:
                v2.setText("UMTS");
                break;
            case 0:
                v2.setText("Unknown");
                break;
        }


        int phoneType=telephonyManager.getPhoneType();

        switch (phoneType)
        {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                v3.setText("CDMA");
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                v3.setText("GSM");
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                v3.setText("none");
                break;
        }

        v4.setText(" " + telephonyManager.getNetworkOperator());

        v5.setText(" " + telephonyManager.getNetworkCountryIso());

        boolean isRoaming=telephonyManager.isNetworkRoaming();
        v6.setText(" "+isRoaming);

        v7.setText(" "+Build.MODEL);



        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        //database reference pointing to demo node
        demoRef = rootRef.child("demo");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Network_Operator_Name = v1.getText().toString();
                String Network_Type = v2.getText().toString();
                String Phone_Type = v3.getText().toString();
                String Mobile_Network_Code = v4.getText().toString();
                String Country_ISO_Code = v5.getText().toString();
                String Roaming_Status = v6.getText().toString();
                String Build_Model = v7.getText().toString();

                //push creates a unique id in database
                demoRef.child("Network_Operator_Name").setValue(Network_Operator_Name);
                demoRef.child("Network_Type").setValue(Network_Type);
                demoRef.child("Phone_Type").setValue(Phone_Type);
                demoRef.child("Mobile_Network_Code").setValue(Mobile_Network_Code);
                demoRef.child("Country_ISO_Code").setValue(Country_ISO_Code);
                demoRef.child("Roaming_Status").setValue(Roaming_Status);
                demoRef.child("Build_Model").setValue(Build_Model);
            }
        });


    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
        startActivity(intent);
    }


    public void createPDF(View view){


        TextView et1=(TextView) findViewById(R.id.tv1);

        Document doc=new Document();

        String outpath=this.getExternalFilesDir(null)+"/report.pdf";
        try {

            PdfWriter.getInstance(doc, new FileOutputStream(outpath));

            doc.open();

            doc.add(new Paragraph(et1.getText().toString()));

            doc.close();

        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}


