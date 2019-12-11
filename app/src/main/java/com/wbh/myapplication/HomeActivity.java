package com.wbh.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getCustomerData();
    }


    public void clickAction(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_Historique:
                startActivity(new Intent(HomeActivity.this, HistoricalActivity.class));
                break;
            case R.id.btn_Contact:
                startActivity(new Intent(HomeActivity.this, FriendListActivity.class));
                break;
            case R.id.btn_profile:
                startActivity(new Intent(HomeActivity.this, AccountActivity.class));
                break;
            case R.id.btn_map:
                startActivity(new Intent(HomeActivity.this, MapActivity.class));
                break;
            case  R.id.btn_chats:
                startActivity(new Intent(HomeActivity.this, ChatActivity.class));
                break;
            case  R.id.btn_check:
                startActivity(new Intent(HomeActivity.this, QRActivity.class));
                break;
            case  R.id.btn_Bills:
                startActivity(new Intent(HomeActivity.this, BillActivity.class));

        }

    }


    public void getCustomerData(){
        ArrayList topRatedProductsList = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "http://41.226.11.252:11809/Bank",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                System.out.println(jsonObject.get("runningBalance"));
                                Object aa =  jsonObject.get("runningBalance");
                                if(i==0){
                                    System.out.println(aa);
                                    TextView amount = findViewById(R.id.amount);
                                    amount.setText( jsonObject.getString("runningBalance") + " TND");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", error.getMessage());
                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }

}
