package com.wbh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FriendListActivity extends AppCompatActivity {

    List<Pair<String, String>> characters;


    public Context getApplicationContext() {
        return super.getApplicationContext();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        getCustomerData(this.getApplicationContext());
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Click On The Item To Send Money", Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(FriendListActivity.this, ProductActivity.class));
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                .show();
    }

    public void getCustomerData(final Context applicationContext){
        ArrayList topRatedProductsList = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "http://41.226.11.252:11809/Bank",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            JSONObject jsonObject1 = response.getJSONObject(1);
                            JSONObject jsonObject2 = response.getJSONObject(2);
                            System.out.println(jsonObject.get("runningBalance"));
                            Object aa =  jsonObject.get("runningBalance");
                            characters = Arrays.asList(
                                    Pair.create("Mart Mongi Damdoum", "The last transaction in "+jsonObject.getString("transactionDate")+" with amount "+jsonObject.getString("transactionAmount") +" with prediction : 223.02 TND/Monthly"),
                                    Pair.create("Wild Mongi Damdoum", "The last transaction in "+jsonObject1.getString("transactionDate")+" with amount "+jsonObject1.getString("transactionAmount")+" with prediction : 100.53 TND/Monthly"),
                                    Pair.create("Bent Mongi Damdoum", "The last transaction in "+jsonObject2.getString("transactionDate")+" with amount "+jsonObject2.getString("transactionAmount")+" with prediction : 80.68 TND/Monthly")
                            );

                            final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
                            rv.setLayoutManager(new LinearLayoutManager(applicationContext));
                            rv.setAdapter(new MyAdapter(characters));
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        RequestQueue queue = Volley.newRequestQueue(applicationContext);
        queue.add(jsonArrayRequest);
    }

}
