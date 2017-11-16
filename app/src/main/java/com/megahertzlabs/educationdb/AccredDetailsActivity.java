package com.megahertzlabs.educationdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AccredDetailsActivity extends AppCompatActivity {
    TextView uniname;
    ListView accreddetailslistview;
    List<String> accredtailslist, idaccred, accredbody, surveyyearaccred, score, maxscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accred_details);
        uniname = findViewById(R.id.nameofuniaccred);
        accreddetailslistview = findViewById(R.id.accreddetailslistview);
        accredtailslist = new ArrayList<>();
        initlis();
        String apiaccred = getStringFromJson2();
        makeLists(apiaccred);

    }

    public void initlis() {
        maxscore = score = surveyyearaccred = accredbody = idaccred = accredtailslist;

    }

    public String getStringFromJson2() {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.api_accred)));
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

    public void makeLists(String apiaccred) {
        try {
            JSONArray jsonArray = new JSONArray(apiaccred);
            for (int i = 1; i < jsonArray.length()-1; i++) {
                JSONArray jsonArray1=new JSONArray(jsonArray.getString(i));
                idaccred.add(jsonArray1.getString(0));
                accredbody.add(jsonArray1.getString(5));
                surveyyearaccred.add(jsonArray1.getString(2));
                score.add(jsonArray1.getString(7));
                maxscore.add(jsonArray1.getString(6));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void createFinalList(String uniname)
    {





    }
}
