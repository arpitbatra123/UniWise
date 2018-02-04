package com.megahertzlabs.educationdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class StateDisplayActivity extends AppCompatActivity {
    Spinner statesList;
    Button unifind_srchbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_display);
        statesList = findViewById(R.id.unifind_spinner);
        unifind_srchbtn = findViewById(R.id.unifind_searchbtn);


    }


    public void onSearchButtonPressed(View view) {
        if (!statesList.getSelectedItem().toString().equals("Select State from Dropdown")) {
            Intent intent = new Intent(StateDisplayActivity.this, UniListDisplayActivity.class);
            intent.putExtra("NameofState", statesList.getSelectedItem().toString());
            startActivity(intent);


        }

        else {
            Toast.makeText(this, "Please Select a State", Toast.LENGTH_SHORT).show();
        }


    }
}
