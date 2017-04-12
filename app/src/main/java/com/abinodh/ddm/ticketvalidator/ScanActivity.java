package com.abinodh.ddm.ticketvalidator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    String message ;
    String[] messageArray;
    int eventData;
    int sectorData;
    int minticketData;
    int maxticketData;

    private TextView resultTxt, contentTxt;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Button scanBtn = (Button) findViewById(R.id.scan_button);
        resultTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanBtn.setOnClickListener(this);
        intent = getIntent();
        String message = getIntent().getExtras().getString("EXTRA_MESSAGE");
        messageArray = message.split("-");
        eventData = Integer.parseInt(messageArray[0]);
        sectorData = Integer.parseInt(messageArray[1]);
        minticketData = Integer.parseInt(messageArray[2]);
        maxticketData = Integer.parseInt(messageArray[3]);
    }
    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            Boolean validity = true;
            String scanContent = scanningResult.getContents();
            String[] resultArr = scanContent.split("-");
            long event = Long.parseLong(resultArr[0]);
            long sector = Long.parseLong(resultArr[1]);
            long ticket = Long.parseLong(resultArr[2]);
            String validityStr;
            if(event != eventData)
            {
                validity = false;
            }
            if(sector != sectorData)
            {
                validity = false;
            }
            if(ticket < minticketData || ticket > maxticketData)
            {
                validity = false;
            }
            if (validity)
                validityStr = "Valid";
            else
                validityStr = "Not Valid";
            resultTxt.setText("Event: " + event + "\nSector: " + sector + "\nTicket Number: " + ticket + "\nValidity: " + validityStr);
            contentTxt.setText("Scan Result: " + scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
