package com.megahertzlabs.educationdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class TosActivity extends AppCompatActivity {
    CheckBox tos_cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tos);
        tos_cb = findViewById(R.id.tos_checkbox);
    }

    public void proceedbtn(View view) {
        if (!tos_cb.isChecked()) {
            Toast.makeText(TosActivity.this, "Please Read All points and Check the Above Checkbox", Toast.LENGTH_SHORT).show();


        } else {

            Intent intent = new Intent(TosActivity.this, StateDisplayActivity.class);
            startActivity(intent);
            finish();


        }

    }
}
