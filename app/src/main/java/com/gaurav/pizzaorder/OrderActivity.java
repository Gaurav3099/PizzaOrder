package com.gaurav.pizzaorder;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class OrderActivity extends AppCompatActivity {
    TextView tvOrder;
    Button btnBack, btnEmail, btnSend;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tvOrder = (TextView) findViewById(R.id.tvOrder);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnSend = (Button) findViewById(R.id.btnSend);
        etEmail = (EditText) findViewById(R.id.etEmail);

        Intent i = getIntent();
        final String order = i.getStringExtra("order");
        tvOrder.setText(order);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                if (! Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    etEmail.setError("in valiid Email");
                    etEmail.requestFocus();
                    return;
                }
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mail to"+ email));
                i.putExtra(Intent.EXTRA_SUBJECT, "pizza order");
                i.putExtra(Intent.EXTRA_TEXT, order);
                startActivity(i);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, order);
                startActivity(i);
            }
        });

    }
}
