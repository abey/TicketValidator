package com.abinodh.ddm.ticketvalidator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void sendMessage(View view) {
            Intent intent = new Intent(this, ScanActivity.class);
            EditText eventSrc = (EditText) findViewById(R.id.eventEditTxt);
            EditText sectorSrc = (EditText) findViewById(R.id.sectorEditTxt);
            EditText minticketSrc = (EditText) findViewById(R.id.minticketEditTxt);
            EditText maxticketSrc = (EditText) findViewById(R.id.maxticketEditTxt);
            String eventTxt = eventSrc.getText().toString();
            String sectorTxt = sectorSrc.getText().toString();
            String minticketTxt = minticketSrc.getText().toString();
            String maxticketTxt = maxticketSrc.getText().toString();
            String message = eventTxt + "-" + sectorTxt + "-" + minticketTxt + "-" + maxticketTxt;
            intent.putExtra("EXTRA_MESSAGE", message);
            startActivity(intent);

        }

    }
