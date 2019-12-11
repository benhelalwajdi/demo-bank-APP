package com.wbh.myapplication;

import android.app.AlertDialog;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapterFriendList extends RecyclerView.Adapter<MyAdapterFriendList.MyViewHolder> {


    List<Pair<String, String>> characters;


    public void getCustomerData(){
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
                                    Pair.create("Mart Mongi Damdoum", "The last transaction in "+jsonObject.getString("transactionDate")+" with amount "+jsonObject.getString("transactionAmount") +"with prediction : 212.05 TND/Monthly"),
                                    Pair.create("Wild Mongi Damdoum", "The last transaction in "+jsonObject1.getString("transactionDate")+" with amount "+jsonObject1.getString("transactionAmount")+"with prediction : 103.15 TND/Monthly"),
                                    Pair.create("Bent Mongi Damdoum", "The last transaction in "+jsonObject2.getString("transactionDate")+" with amount "+jsonObject2.getString("transactionAmount")+"with prediction : 80.63 TND/Monthly")
                            );
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
        // RequestQueue queue = Volley.newRequestQueue();
        // queue.add(jsonArrayRequest);
    }
    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> pair = characters.get(position);
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;

        private Pair<String, String> currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentPair.first)
                            .setMessage(currentPair.second)
                            .show();
                }
            });
        }

        public void display(Pair<String, String> pair) {
            currentPair = pair;
            name.setText(pair.first);
            description.setText(pair.second);
        }
    }

}