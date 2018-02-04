package com.megahertzlabs.educationdb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UniListDisplayActivity extends AppCompatActivity {
     public static List<String> finalunilist, id, survey_year, state, university, address_line1, address_line2, city, district, pincode, website, area_in_acre, latitude, longitude, type, specialized;
    TextView test;
     ListView listofuniversities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_list_display);
        test = findViewById(R.id.textView5);
        String APIResponse = getStringFromJson();
        initializeLists();

        makeLists(APIResponse);

        String NameofStateforTheLoveofGodPleaseWork = getIntent().getExtras().getString("NameofState");
        findUnisinState(NameofStateforTheLoveofGodPleaseWork);
        test.setText(NameofStateforTheLoveofGodPleaseWork);
        listofuniversities = findViewById(R.id.unilist);
        if(finalunilist.isEmpty())
            finalunilist.add("No Universities Found");
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(UniListDisplayActivity.this, android.R.layout.simple_list_item_1,finalunilist);
        listofuniversities.setAdapter(stringArrayAdapter);
        //Set List Listener
        listofuniversities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             if (!(finalunilist.get(0).contains("No Universities Found") ) ) {
                 Intent intent = new Intent(UniListDisplayActivity.this, UniDetailsDisplayActivity.class);
                 intent.putExtra("uniname", listofuniversities.getItemAtPosition(i).toString());
                 startActivity(intent);
             }
            }
        });

    }


    public void initializeLists()
    {
        id = new ArrayList<>();
        survey_year = new ArrayList<>();
        state = new ArrayList<>();
        university = new ArrayList<>();
        finalunilist = new ArrayList<>();
        address_line1=new ArrayList<>();
        address_line2=new ArrayList<>();
        city=new ArrayList<>();
        district=new ArrayList<>();
        pincode=new ArrayList<>();
        website=new ArrayList<>();
        specialized=new ArrayList<>();
        type=new ArrayList<>();
        latitude=new ArrayList<>();
        longitude=new ArrayList<>();
        area_in_acre=new ArrayList<>();
        specialized=new ArrayList<>();

    }




    public String getStringFromJson() {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.api_info)));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public void makeLists(String APIresponse) {
        ProgressDialog dialog = new ProgressDialog(UniListDisplayActivity.this);
        dialog.setTitle("Processing....");
        dialog.show();
        try {
            JSONArray jsonArray = new JSONArray(APIresponse);
            int length = jsonArray.length();
            for (int i = 1; i < length - 1; i++) {

                JSONArray jsonArray1 = new JSONArray(jsonArray.getString(i));

                id.add(jsonArray1.getString(0));
                survey_year.add(jsonArray1.getString(1));
                state.add(jsonArray1.getString(2));
                university.add(jsonArray1.getString(3));
                address_line1.add(jsonArray1.getString(4));
                address_line2.add(jsonArray1.getString(5));
                city.add(jsonArray1.getString(6));
                pincode.add(jsonArray1.getString(8));
                website.add(jsonArray1.getString(9));
                specialized.add(jsonArray1.getString(18));
                type.add(jsonArray1.getString(17));
                latitude.add(jsonArray1.getString(15));
                longitude.add(jsonArray1.getString(16));
                area_in_acre.add(jsonArray1.getString(10));



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();


    }

    public void findUnisinState(String nameofstate) {
        for (int i = 0; i < state.size(); i++) {
            if (state.get(i).equalsIgnoreCase(nameofstate)) {
              finalunilist.add(university.get(i));
            }


        }


    }
}

