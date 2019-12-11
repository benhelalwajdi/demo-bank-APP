package com.wbh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

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
import java.util.Arrays;
import java.util.List;

public class HistoricalActivity extends AppCompatActivity {


    List<Pair<String, String>> characters;


    public Context getApplicationContext() {
        return super.getApplicationContext();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        //rv.setLayoutManager(new LinearLayoutManager(this));
       // rv.setAdapter(new MyAdapter(this.getApplicationContext()));
        getCustomerData(this.getApplicationContext());
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
                            ArrayList<Pair<String, String>> arrayList = new ArrayList<>();
                            for (int i = 0 ; i< response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(0);
                                System.out.println(jsonObject.get("runningBalance"));
                                Object aa = jsonObject.get("runningBalance");
                                arrayList.add(Pair.create("the transaction Amount : " + jsonObject.getString("transactionAmount"), "Description : " + jsonObject.getString("description")));
                            }
                            characters = arrayList;
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

