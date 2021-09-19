package com.gaurav.pizzaorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import static com.gaurav.pizzaorder.R.id.top;

public class MainActivity extends AppCompatActivity {
    Spinner spnSize;
    CheckBox cbCorn, cbOnion, cbCapsicum;
    Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnSize = (Spinner) findViewById(R.id.spnSize);
        cbCorn = (CheckBox) findViewById(R.id.cbCorn);
        cbOnion = (CheckBox) findViewById(R.id.cbOnion);
        cbCapsicum = (CheckBox)findViewById(R.id.cbCapsicon);
        btnOrder = (Button) findViewById(R.id.btnOrder);

        final String size[] = {"Small", "medium", "large"};
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, size);
        spnSize.setAdapter(a);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p = spnSize.getSelectedItemPosition();
                String s = size[p];
                String top = "";
                if (cbCorn.isChecked()) top = top + " Corn ";
                if (cbCapsicum.isChecked()) top = top + " Casicum ";
                if (cbOnion.isChecked()) top = top +" Onion ";

                String order = "Size : " + s + " Toppings " +top;
                Intent i = new Intent(MainActivity.this, OrderActivity.class);
                i.putExtra("order", order);
                startActivity(i);

            }
        });

    }
}
