package com.megahertzlabs.educationdb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UniDetailsDisplayActivity extends AppCompatActivity {
    ListView unidetails;
    List<String> unidetailslist;
    TextView uniname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_details_display);
        Toast.makeText(this, getIntent().getExtras().getString("uniname"), Toast.LENGTH_SHORT).show();
        unidetails = findViewById(R.id.unidetails);
        uniname = findViewById(R.id.nameofuniaccred);
        uniname.setText(getIntent().getExtras().getString("uniname"));
        unidetailslist = new ArrayList<>();
        //createStructureoflist();
        finddetails(getIntent().getExtras().getString("uniname"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UniDetailsDisplayActivity.this, android.R.layout.simple_list_item_1, unidetailslist);
        unidetails.setAdapter(arrayAdapter);

        unidetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info = unidetails.getItemAtPosition(i).toString();


                if (info.indexOf("View on Map") >= 0) {
                    if (checkNA(info))
                        openMap(getIntent().getExtras().getString("uniname"));
                }

                if (info.indexOf("Visit Website") >= 0) {
                    if (checkNA(info))
                        visitWebsite(getIntent().getExtras().getString("uniname"));
                }


            }
        });
    }


    public boolean checkNA(String info) {
        if (info.contains("NA")) {
            Toast.makeText(UniDetailsDisplayActivity.this, "Incomplete Information", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    public void openMap(String uniname) {
        int index = UniListDisplayActivity.university.indexOf(uniname);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //URLEncoder urlEncoder=new URLEncoder();
        String url = "https://www.google.com/maps/search/?api=1&query=" + UniListDisplayActivity.latitude.get(index) + "," + UniListDisplayActivity.longitude.get(index);
        //Uri gmmIntentUri = Uri.parse("geo:"+UniListDisplayActivity.latitude.get(index)+","+UniListDisplayActivity.longitude.get(index)+"?q=" + Uri.encode(uniname));
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void visitWebsite(String uniname) {
        String url = "http://" + UniListDisplayActivity.website.get(UniListDisplayActivity.university.indexOf(uniname));
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }


    public void finddetails(String uniname) {

        int index = UniListDisplayActivity.university.indexOf(uniname);

        //for(int i=0;i<unidetailslist.size();i++)
        {
            unidetailslist.add("Survey Year : " + " " + UniListDisplayActivity.survey_year.get(index));
            unidetailslist.add("Address : " + UniListDisplayActivity.address_line1.get(index) + " " + UniListDisplayActivity.address_line2.get(index) + " " + UniListDisplayActivity.city.get(index) + " " + UniListDisplayActivity.state.get(index) + " " + UniListDisplayActivity.pincode.get(index));
            unidetailslist.add("View on Map : Lattitude " + UniListDisplayActivity.latitude.get(index) + " Longitude " + UniListDisplayActivity.longitude.get(index));
            unidetailslist.add("Visit Website : " + " " + UniListDisplayActivity.website.get(index));
            unidetailslist.add("Area in Square Meters : " + UniListDisplayActivity.area_in_acre.get(index));
            unidetailslist.add("Specialized University? : " + UniListDisplayActivity.specialized.get(index));
            unidetailslist.add("Type of University : " + UniListDisplayActivity.type.get(index));


        }


    }
}
